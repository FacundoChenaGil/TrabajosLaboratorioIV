package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ICuotaDao;

import entidad.Cuota;
import entidad.Prestamo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Cuota;
import entidad.TiposDeCuentas;

public class CuotaDaoImpl implements ICuotaDao {

	private static final String OBTENER_PAGADAS_POR_PRESTAMO = "SELECT * FROM Cuotas WHERE ID_Prestamo = ? AND Fecha_Pago IS NOT NULL ORDER BY Numero_Cuota";
	private static final String OBTENER_PENDIENTES_POR_PRESTAMO = "SELECT ID_Cuota, ID_Prestamo, Numero_Cuota, Importe, Fecha_Vencimiento FROM Cuotas WHERE ID_Prestamo = ? AND Fecha_Pago IS NULL ORDER BY Numero_Cuota";
	private static final String OBTENER_POR_ID = "SELECT * FROM Cuotas WHERE ID_Cuota = ?";
	private static final String AGREGAR_CUOTA = "INSERT INTO Cuotas (ID_Prestamo, Numero_Cuota, Importe, Fecha_Vencimiento) VALUES (?, ?, ?, ?)";
	private static final String PAGAR_CUOTA_UPDATE_CUENTA = "UPDATE Cuentas SET Saldo = Saldo - ? WHERE Numero_Cuenta = ?";
	private static final String PAGAR_CUOTA_UPDATE_CUOTA = "UPDATE Cuotas SET Fecha_Pago = CURRENT_DATE() WHERE ID_Cuota = ?";

	@Override
	public List<Cuota> obtenerCuotasPagadasPorPrestamo(int idPrestamo) {
		List<Cuota> cuotasPagadas = new ArrayList<>();
		Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement(OBTENER_PAGADAS_POR_PRESTAMO);
			ps.setInt(1, idPrestamo);
			rs = ps.executeQuery();

			while (rs.next()) {
				cuotasPagadas.add(crearCuotaDesdeResultSet(rs, true));
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

	@Override
	public List<Cuota> obtenerCuotasPendientesPorPrestamo(Prestamo prestamo) {
		List<Cuota> cuotasPendientes = new ArrayList<>();
		Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement(OBTENER_PENDIENTES_POR_PRESTAMO);
			ps.setInt(1, prestamo.getIDPrestamo());
			rs = ps.executeQuery();

			while (rs.next()) {
				cuotasPendientes.add(crearCuotaDesdeResultSet(rs, prestamo, false));
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

	@Override
	public Cuota obtenerPorId(int idCuota) {
		Cuota cuota = null;
		Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement(OBTENER_POR_ID);
			ps.setInt(1, idCuota);
			rs = ps.executeQuery();

			if (rs.next()) {
				cuota = crearCuotaDesdeResultSet(rs, true); // Asumimos que si se busca por ID puede estar paga
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

	@Override
	public boolean agregarCuota(Cuota cuota) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    boolean exito = false;

	    try {
	        conn = Conexion.getConexion();
	        conn.setAutoCommit(false);
	        ps = conn.prepareStatement(AGREGAR_CUOTA);
	        ps.setInt(1, cuota.getPrestamo().getIDPrestamo());
	        ps.setInt(2, cuota.getNumeroCuota());
	        ps.setBigDecimal(3, cuota.getImporte());
	        ps.setDate(4, java.sql.Date.valueOf(cuota.getFechaVencimiento()));

	        if (ps.executeUpdate() > 0) {
	            conn.commit();
	            exito = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (conn != null) {
	                conn.rollback();
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    } finally {
	        try {
	            if (ps != null) {
	                ps.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        Conexion.cerrarConexion(conn);
	    }
	    return exito;
	}

	@Override
	public boolean pagarCuota(int cuotaId, String numeroCuenta) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    boolean exito = false;

	    String sqlGetCuota = "SELECT ID_Prestamo, Importe FROM Cuotas WHERE ID_Cuota = ? AND Fecha_Pago IS NULL";
	    String sqlGetCuenta = "SELECT Saldo FROM Cuentas WHERE Numero_Cuenta = ?";
	    String sqlCheckCuotas = "SELECT COUNT(*) AS pendientes FROM Cuotas WHERE ID_Prestamo = ? AND Fecha_Pago IS NULL";
	    String sqlUpdatePrestamo = "UPDATE Prestamos SET ID_Tipo_Estado = 4 WHERE ID_Prestamo = ?";

	    try {
	        conn = Conexion.getConexion();
	        conn.setAutoCommit(false);

	        // 1. Obtener datos de la cuota
	        ps = conn.prepareStatement(sqlGetCuota);
	        ps.setInt(1, cuotaId);
	        rs = ps.executeQuery();
	        if (!rs.next()) throw new SQLException("Cuota no encontrada o ya pagada.");
	        int idPrestamo = rs.getInt("ID_Prestamo");
	        java.math.BigDecimal importeCuota = rs.getBigDecimal("Importe");
	        rs.close();
	        ps.close();

	        // 2. Verificar saldo de la cuenta
	        ps = conn.prepareStatement(sqlGetCuenta);
	        ps.setString(1, numeroCuenta);
	        rs = ps.executeQuery();
	        if (!rs.next() || rs.getBigDecimal("Saldo").compareTo(importeCuota) < 0) throw new SQLException("Saldo insuficiente.");
	        rs.close();
	        ps.close();

	        // 3. Debitar de la cuenta
	        ps = conn.prepareStatement(PAGAR_CUOTA_UPDATE_CUENTA);
	        ps.setBigDecimal(1, importeCuota);
	        ps.setString(2, numeroCuenta);
	        if (ps.executeUpdate() == 0) throw new SQLException("No se pudo actualizar el saldo de la cuenta.");
	        ps.close();

	        // 4. Marcar cuota como pagada
	        ps = conn.prepareStatement(PAGAR_CUOTA_UPDATE_CUOTA);
	        ps.setInt(1, cuotaId);
	        if (ps.executeUpdate() == 0) throw new SQLException("No se pudo marcar la cuota como pagada.");
	        ps.close();

	        // 5. Verificar si es la última cuota y finalizar préstamo si corresponde
	        ps = conn.prepareStatement(sqlCheckCuotas);
	        ps.setInt(1, idPrestamo);
	        rs = ps.executeQuery();
	        if (rs.next() && rs.getInt("pendientes") == 0) {
	            rs.close(); // Cerrar ResultSet antes de la siguiente operación
	            ps.close();
	            
	            ps = conn.prepareStatement(sqlUpdatePrestamo);
	            ps.setInt(1, idPrestamo);
	            ps.executeUpdate();
	            ps.close();
	        }

	        conn.commit();
	        exito = true;

	    } catch (SQLException e) {
	        try { if (conn != null) conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
	        e.printStackTrace();
	    } finally {
	        try { if (rs != null && !rs.isClosed()) rs.close(); } catch (Exception e) { e.printStackTrace(); }
	        try { if (ps != null && !ps.isClosed()) ps.close(); } catch (Exception e) { e.printStackTrace(); }
	        Conexion.cerrarConexion(conn);
	    }
	    return exito;
	}
	

	private Cuota crearCuotaDesdeResultSet(ResultSet rs, boolean isPagada) throws SQLException {
		Prestamo p = new Prestamo();
		p.setIDPrestamo(rs.getInt("ID_Prestamo"));
		return crearCuotaDesdeResultSet(rs, p, isPagada);
	}

	private Cuota crearCuotaDesdeResultSet(ResultSet rs, Prestamo prestamo, boolean isPagada) throws SQLException {
		Cuota cuota = new Cuota();
		cuota.setPrestamo(prestamo);
		cuota.setIdCuota(rs.getInt("ID_Cuota"));
		cuota.setNumeroCuota(rs.getInt("Numero_Cuota"));
		cuota.setImporte(rs.getBigDecimal("Importe"));
		
		Date fechaVenc = rs.getDate("Fecha_Vencimiento");
		if (fechaVenc != null) {
			cuota.setFechaVencimiento(fechaVenc.toLocalDate());
		}
		
		if (isPagada) {
			Date fechaPago = rs.getDate("Fecha_Pago");
			if (fechaPago != null) {
				cuota.setFechaPago(fechaPago.toLocalDate());
			}
		}
		
		return cuota;
	}


	
}
