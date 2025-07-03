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

        try (Connection conn = Conexion.getNuevaConexion();
             PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Movimientos (CBU, Fecha_Movimiento, Detalle, Importe, ID_Tipo_Movimiento) VALUES (?, ?, ?, ?, ?)")) {

            stmt.setString(1, cbu);
            stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            stmt.setString(3, detalle);
            stmt.setBigDecimal(4, importe);
            stmt.setInt(5, tipoMovimientoId);

            resultado = stmt.executeUpdate() > 0;
            conn.commit(); // Si tenés autoCommit en false
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }

    @Override
    public List<Movimiento> obtenerMovimientosPorCBU(String cbu) {
        List<Movimiento> lista = new ArrayList<>();

        try (Connection conn = Conexion.getNuevaConexion();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT * FROM Movimientos WHERE CBU = ? ORDER BY Fecha_Movimiento DESC")) {

            stmt.setString(1, cbu);
            ResultSet rs = stmt.executeQuery();

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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}