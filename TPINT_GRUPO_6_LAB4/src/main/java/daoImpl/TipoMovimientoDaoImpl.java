package daoImpl;

import dao.ITipoMovimientoDao;
import entidad.TipoMovimiento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoMovimientoDaoImpl implements ITipoMovimientoDao {

    @Override
    public TipoMovimiento obtenerPorId(int id) {
        TipoMovimiento tipo = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConexion();
            ps = conn.prepareStatement("SELECT * FROM Tipos_Movimiento WHERE ID_Tipo_Movimiento = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                tipo = new TipoMovimiento();
                tipo.setId(rs.getInt("ID_Tipo_Movimiento"));
                tipo.setDescripcion(rs.getString("Descripcion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            Conexion.cerrarConexion(conn);
        }

        return tipo;
    }

    @Override
    public List<TipoMovimiento> listarTodos() {
        List<TipoMovimiento> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConexion();
            ps = conn.prepareStatement("SELECT * FROM Tipos_Movimiento");
            rs = ps.executeQuery();

            while (rs.next()) {
                TipoMovimiento tipo = new TipoMovimiento();
                tipo.setId(rs.getInt("ID_Tipo_Movimiento"));
                tipo.setDescripcion(rs.getString("Descripcion"));
                lista.add(tipo);
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