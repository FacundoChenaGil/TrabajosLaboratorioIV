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

        try (Connection conn = Conexion.getNuevaConexion();
        		
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Tipos_Movimiento WHERE ID_Tipo_Movimiento = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tipo = new TipoMovimiento();
                tipo.setId(rs.getInt("ID_Tipo_Movimiento"));
                tipo.setDescripcion(rs.getString("Descripcion"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tipo;
    }

    @Override
    public List<TipoMovimiento> listarTodos() {
        List<TipoMovimiento> lista = new ArrayList<>();

        try (Connection conn = Conexion.getNuevaConexion();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Tipos_Movimiento");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TipoMovimiento tipo = new TipoMovimiento();
                tipo.setId(rs.getInt("ID_Tipo_Movimiento"));
                tipo.setDescripcion(rs.getString("Descripcion"));
                lista.add(tipo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}