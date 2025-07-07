package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ICuotaDao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import entidad.Cuota;

public class CuotaDaoImpl implements ICuotaDao {

	public List<Cuota> obtenerCuotasPagadasPorPrestamo(int idPrestamo) {

		List<Cuota> cuotasPagadas = new ArrayList<>();
		String sql = "SELECT * FROM Cuotas WHERE ID_Prestamo = ? AND Fecha_Pago IS NOT NULL ORDER BY Numero_Cuota";

		try (Connection con = Conexion.getNuevaConexion();

				PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, idPrestamo);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Cuota cuota = new Cuota();
				cuota.setIdCuota(rs.getInt("ID_Cuota"));
				cuota.setIdPrestamo(rs.getInt("ID_Prestamo"));
				cuota.setNumeroCuota(rs.getInt("Numero_Cuota"));
				cuota.setImporte(rs.getFloat("Importe"));
				cuota.setNumeroCuenta(rs.getString("Numero_Cuenta"));

				java.sql.Date fechaVenc = rs.getDate("Fecha_Vencimiento");
				if (fechaVenc != null)
					cuota.setFechaVencimiento(fechaVenc.toLocalDate());

				java.sql.Date fechaPago = rs.getDate("Fecha_Pago");
				if (fechaPago != null)
					cuota.setFechaPago(fechaPago.toLocalDate());

				cuotasPagadas.add(cuota);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return cuotasPagadas;

	}

	public List<Cuota> obtenerCuotasPendientesPorPrestamo(int idPrestamo) {
		List<Cuota> cuotasPendientes = new ArrayList<>();

		String sql = "SELECT * FROM Cuotas WHERE ID_Prestamo = ? AND Fecha_Pago IS NULL ORDER BY Numero_Cuota";

		try (Connection con = Conexion.getNuevaConexion();
				PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, idPrestamo);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Cuota cuota = new Cuota();

				cuota.setIdCuota(rs.getInt("ID_Cuota"));
				cuota.setIdPrestamo(rs.getInt("ID_Prestamo"));
				cuota.setNumeroCuota(rs.getInt("Numero_Cuota"));
				cuota.setImporte(rs.getFloat("Importe"));
				cuota.setNumeroCuenta(rs.getString("Numero_Cuenta"));
				Date fechaSQL = rs.getDate("Fecha_Vencimiento");
				if (fechaSQL != null) {
					cuota.setFechaVencimiento(fechaSQL.toLocalDate());
				}

				cuotasPendientes.add(cuota);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cuotasPendientes;
	}

	public Cuota obtenerPorId(int idCuota) {
		Cuota cuota = null;

		String sql = "SELECT * FROM Cuotas WHERE ID_Cuota = ?";

		try (Connection con = Conexion.getNuevaConexion();
				PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, idCuota);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				cuota = new Cuota();
				cuota.setIdCuota(rs.getInt("ID_Cuota"));
				cuota.setIdPrestamo(rs.getInt("ID_Prestamo"));
				cuota.setNumeroCuota(rs.getInt("Numero_Cuota"));
				cuota.setImporte(rs.getFloat("Importe"));
				cuota.setNumeroCuenta(rs.getString("Numero_Cuenta"));

				Date fechaSQL = rs.getDate("Fecha_Vencimiento");
				if (fechaSQL != null) {
					cuota.setFechaVencimiento(fechaSQL.toLocalDate());
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cuota;
	}

	public boolean marcarComoPagada(int idCuota, String numeroCuenta) {
		String sql = "UPDATE Cuotas SET Fecha_Pago = CURRENT_DATE, Numero_Cuenta = ? WHERE ID_Cuota = ?";

		try (Connection con = Conexion.getNuevaConexion();
				PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, numeroCuenta);
			stmt.setInt(2, idCuota);

			int filas = stmt.executeUpdate();
			con.commit();
			
			return filas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
	


