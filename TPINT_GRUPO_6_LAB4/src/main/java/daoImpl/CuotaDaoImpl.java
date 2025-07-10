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
		Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
		String sql = "SELECT * FROM Cuotas WHERE ID_Prestamo = ? AND Fecha_Pago IS NOT NULL ORDER BY Numero_Cuota";

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idPrestamo);
			rs = ps.executeQuery();

			while (rs.next()) {
				Cuota cuota = new Cuota();
				cuota.setIdCuota(rs.getInt("ID_Cuota"));
				cuota.setIdPrestamo(rs.getInt("ID_Prestamo"));
				cuota.setNumeroCuota(rs.getInt("Numero_Cuota"));
				cuota.setImporte(rs.getBigDecimal("Importe"));
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
		} finally {
	        try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
	        try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
	        Conexion.cerrarConexion(conn);
	    }
		return cuotasPagadas;

	}

	public List<Cuota> obtenerCuotasPendientesPorPrestamo(int idPrestamo) {
		List<Cuota> cuotasPendientes = new ArrayList<>();
		Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
		String sql = "SELECT * FROM Cuotas WHERE ID_Prestamo = ? AND Fecha_Pago IS NULL ORDER BY Numero_Cuota";

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idPrestamo);
			rs = ps.executeQuery();

			while (rs.next()) {
				Cuota cuota = new Cuota();

				cuota.setIdCuota(rs.getInt("ID_Cuota"));
				cuota.setIdPrestamo(rs.getInt("ID_Prestamo"));
				cuota.setNumeroCuota(rs.getInt("Numero_Cuota"));
				cuota.setImporte(rs.getBigDecimal("Importe"));
				cuota.setNumeroCuenta(rs.getString("Numero_Cuenta"));
				Date fechaSQL = rs.getDate("Fecha_Vencimiento");
				if (fechaSQL != null) {
					cuota.setFechaVencimiento(fechaSQL.toLocalDate());
				}

				cuotasPendientes.add(cuota);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
	        try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
	        Conexion.cerrarConexion(conn);
	    }

		return cuotasPendientes;
	}

	public Cuota obtenerPorId(int idCuota) {
		Cuota cuota = null;
		Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
		String sql = "SELECT * FROM Cuotas WHERE ID_Cuota = ?";

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idCuota);
			rs = ps.executeQuery();

			if (rs.next()) {
				cuota = new Cuota();
				cuota.setIdCuota(rs.getInt("ID_Cuota"));
				cuota.setIdPrestamo(rs.getInt("ID_Prestamo"));
				cuota.setNumeroCuota(rs.getInt("Numero_Cuota"));
				cuota.setImporte(rs.getBigDecimal("Importe"));
				cuota.setNumeroCuenta(rs.getString("Numero_Cuenta"));

				Date fechaSQL = rs.getDate("Fecha_Vencimiento");
				if (fechaSQL != null) {
					cuota.setFechaVencimiento(fechaSQL.toLocalDate());
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
	        try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
	        Conexion.cerrarConexion(conn);
	    }

		return cuota;
	}

	public boolean marcarComoPagada(int idCuota, String numeroCuenta) {
		boolean pagada = false;
	    Connection conn = null;
	    PreparedStatement ps = null;
		String sql = "UPDATE Cuotas SET Fecha_Pago = CURRENT_DATE, Numero_Cuenta = ? WHERE ID_Cuota = ?";

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement(sql);
			ps.setString(1, numeroCuenta);
			ps.setInt(2, idCuota);

			if (ps.executeUpdate() > 0) {
	            conn.commit();
	            pagada = true;
	        } else {
	            conn.rollback();
	        }

		} catch (SQLException e) {
			try { if (conn != null) conn.rollback(); } catch (SQLException e2) { e2.printStackTrace(); }
			e.printStackTrace();
		} finally {
	        try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
	        Conexion.cerrarConexion(conn);
	    }
		return pagada;
	}
	
}
	


