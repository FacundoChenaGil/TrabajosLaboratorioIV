package daoImpl;

import dao.IMovimientoDao;
import entidad.Movimiento;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class MovimientoDaoImpl implements IMovimientoDao {

	@Override
    public List<Movimiento> obtenerPorCBU(String cbu) {
        List<Movimiento> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT m.ID_Movimiento, m.CBU, m.Fecha_Movimiento, m.Detalle, m.Importe, " +
                       "m.ID_Tipo_Movimiento, t.Descripcion " +
                       "FROM Movimientos m " +
                       "JOIN Tipos_Movimiento t ON m.ID_Tipo_Movimiento = t.ID_Tipo_Movimiento " +
                       "WHERE m.CBU = ? ORDER BY m.Fecha_Movimiento DESC";

        try {
            conn = Conexion.getConexion();
            ps = conn.prepareStatement(query);
            ps.setString(1, cbu);
            rs = ps.executeQuery();

            while (rs.next()) {
                Movimiento m = new Movimiento();
                m.setIdMovimiento(rs.getInt("ID_Movimiento"));
                m.setCbu(rs.getString("CBU"));
                m.setFechaMovimiento(rs.getTimestamp("Fecha_Movimiento"));
                m.setDetalle(rs.getString("Detalle"));
                m.setImporte(rs.getBigDecimal("Importe"));
                m.setIdTipoMovimiento(rs.getInt("ID_Tipo_Movimiento"));
                lista.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            Conexion.cerrarConexion(conn);
        }

        return lista;
    }
	public List<Movimiento> obtenerFiltrados(String cbu, Date desde, Date hasta, int idTipo) {
	    List<Movimiento> lista = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    String query = "SELECT m.ID_Movimiento, m.CBU, m.Fecha_Movimiento, m.Detalle, m.Importe, " +
	                   "m.ID_Tipo_Movimiento, t.Descripcion " +
	                   "FROM Movimientos m " +
	                   "JOIN Tipos_Movimiento t ON m.ID_Tipo_Movimiento = t.ID_Tipo_Movimiento " +
	                   "WHERE m.CBU = ? AND m.Fecha_Movimiento >= ? AND m.Fecha_Movimiento < ?";
	    if (idTipo > 0) {
	        query += " AND m.ID_Tipo_Movimiento = ?";
	    }
	    query += " ORDER BY m.Fecha_Movimiento DESC";

	    try {
	        conn = Conexion.getConexion();
	        ps = conn.prepareStatement(query);

	        ps.setString(1, cbu);
	        ps.setTimestamp(2, new Timestamp(desde.getTime()));
	        ps.setTimestamp(3, new Timestamp(hasta.getTime()));

	        if (idTipo > 0) {
	            ps.setInt(4, idTipo);
	        }

	        rs = ps.executeQuery();
	        while (rs.next()) {
	            Movimiento m = new Movimiento();
	            m.setIdMovimiento(rs.getInt("ID_Movimiento"));
	            m.setCbu(rs.getString("CBU"));
	            m.setFechaMovimiento(rs.getTimestamp("Fecha_Movimiento"));
	            m.setDetalle(rs.getString("Detalle"));
	            m.setImporte(rs.getBigDecimal("Importe"));
	            m.setIdTipoMovimiento(rs.getInt("ID_Tipo_Movimiento"));
	            lista.add(m);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
	        try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
	        Conexion.cerrarConexion(conn);
	    }

	    return lista;
	}
	
	  @Override
	    public boolean insertarMovimiento(String cbu, BigDecimal importe, String detalle, int tipoMovimientoId) {
	        boolean resultado = false;
	        Connection conn = null;
	        PreparedStatement ps = null;
	        try {
	            conn = Conexion.getConexion();
	            ps = conn.prepareStatement(
	                "INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento) VALUES (?, ?, ?, ?, ?)");
	            ps.setString(1, cbu);
	            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
	            ps.setString(3, detalle);
	            ps.setBigDecimal(4, importe);
	            ps.setInt(5, tipoMovimientoId);
	            if (ps.executeUpdate() > 0) {
	                conn.commit();
	                resultado = true;
	            } else {
	                conn.rollback();
	            }
	        } catch (SQLException e) {
	            try {
	                if (conn != null) conn.rollback();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	            e.printStackTrace();
	        } finally {
	            try {
	                if (ps != null) ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            Conexion.cerrarConexion(conn);
	        }

	        return resultado;
	  }
   
   
}
