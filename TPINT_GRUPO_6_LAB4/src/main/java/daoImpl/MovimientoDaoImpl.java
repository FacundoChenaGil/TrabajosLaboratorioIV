package daoImpl;

import dao.IMovimientoDao;
import entidad.Movimiento;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovimientoDaoImpl implements IMovimientoDao {

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

    @Override
    public List<Movimiento> obtenerMovimientosPorCBU(String cbu) {
        List<Movimiento> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConexion();
            ps = conn.prepareStatement(
                 "SELECT * FROM Movimientos WHERE CBU = ? ORDER BY Fecha_Movimiento DESC");

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
}