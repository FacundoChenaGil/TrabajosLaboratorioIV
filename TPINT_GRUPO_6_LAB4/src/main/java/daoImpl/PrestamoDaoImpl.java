package daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IPrestamoDao;
import entidad.Prestamo;
import entidad.PrestamoBackup;

import entidad.TipoEstadoPrestamo;
import entidad.Cuenta;

public class PrestamoDaoImpl implements IPrestamoDao {

	private static final String INSERT = "INSERT INTO Prestamos "
		    + "(DNI, Fecha_Solicitud, Importe_Pedido, Plazo_Pago_Meses, Importe_Cuota, Cantidad_Cuotas, Importe_a_Pagar, ID_Tipo_Estado, ID_Cuenta_Acreditacion) "
		    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final int ESTADO_APROBADO = 2;
	private static final String SELECT_BY_ID =
		    "SELECT `ID Prestamo` AS idPrestamo, DNI as dni, `Fecha Solicitud` AS fechaCreacion, `Importe Pedido` AS montoPedido, " +
		    "`Plazo Pago Meses` AS plazoPago, `Importe Cuota` AS importeCuota, `Cantidad Cuotas` AS cantCuotas, " +
		    "`Importe a Pagar` AS importePagar, `ID Tipo Estado` AS idTipoEstado, `ID Cuenta Acreditacion` AS idCuenta " +
		    "FROM Prestamos WHERE `ID Prestamo` = ? AND `ID Tipo Estado` != " + ESTADO_APROBADO;
	private static final String UPDATE = "UPDATE Prestamos SET Fecha_Solicitud = ?, Importe_Pedido = ?, Plazo_Pago_Meses = ?, " +
            "Importe_Cuota = ?, Cantidad_Cuotas = ?, Importe_a_Pagar = ?, ID_Tipo_Estado = ?, ID_Cuenta_Acreditacion = ? " +
            "WHERE `ID Prestamo` = ?";

	@Override
	public boolean agregar(Prestamo prestamo) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean exito = false;

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement(INSERT);
			ps.setString(1, prestamo.getDNI());
			ps.setDate(2, java.sql.Date.valueOf(prestamo.getFechaCreacion()));
			ps.setFloat(3, (float) prestamo.getMontoPedido());
			ps.setInt(4, prestamo.getPlazoPago());
			ps.setFloat(5, (float) prestamo.getImporteCuota());
			ps.setInt(6, prestamo.getCantidadCuotas());
			ps.setFloat(7, (float) prestamo.getImporteAPagar());
			ps.setInt(8, prestamo.getIdTipoEstado());
			ps.setString(9, prestamo.getIdCuenta());

			if (ps.executeUpdate() > 0) {
				conn.commit();
				exito = true;
			}
		} catch (SQLException e) {
			try { if (conn != null) conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
			e.printStackTrace();
		} finally {
			try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
			Conexion.cerrarConexion(conn);
		}

		return exito;
	}

	@Override
	public Prestamo obtenerPrestamoPorId(int idPrestamo) {
        Prestamo prestamo = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConexion();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, idPrestamo);
            rs = ps.executeQuery();

            if (rs.next()) {
                prestamo = cargarPrestamoDesdeResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
        	try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            Conexion.cerrarConexion(conn);
        }

        return prestamo;
    }

	@Override
	public boolean eliminar(int idPrestamo) {
		String sql = "UPDATE Prestamos SET ID_Tipo_Estado = ? WHERE `ID Prestamo` = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		boolean exito = false;

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ESTADO_APROBADO);
			ps.setInt(2, idPrestamo);

			if (ps.executeUpdate() > 0) {
				conn.commit();
				exito = true;
			}
		} catch (SQLException e) {
			try { if (conn != null) conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
			e.printStackTrace();
		} finally {
			try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
			Conexion.cerrarConexion(conn);
		}

		return exito;
	}

	private Prestamo cargarPrestamoDesdeResultSet(ResultSet rs) throws SQLException {
	    Prestamo prestamo = new Prestamo();
	    prestamo.setDNI(rs.getString("dni"));
	    prestamo.setIdPrestamo(rs.getInt("idPrestamo"));
	    prestamo.setFechaCreacion(rs.getDate("fechaCreacion").toLocalDate());
	    prestamo.setMontoPedido(rs.getFloat("montoPedido"));
	    prestamo.setPlazoPago(rs.getInt("plazoPago"));
	    prestamo.setImporteCuota(rs.getFloat("importeCuota"));
	    prestamo.setCantidadCuotas(rs.getInt("cantCuotas"));
	    prestamo.setImporteAPagar(rs.getFloat("importePagar"));
	    prestamo.setIdTipoEstado(rs.getInt("idTipoEstado"));
	    prestamo.setIdCuenta(rs.getString("idCuenta"));
	    return prestamo;
	}

	@Override
	public boolean modificar(Prestamo prestamo) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean exito = false;

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement(UPDATE);
			ps.setDate(1, java.sql.Date.valueOf(prestamo.getFechaCreacion()));
			ps.setFloat(2, (float) prestamo.getMontoPedido());
			ps.setInt(3, prestamo.getPlazoPago());
			ps.setFloat(4, (float) prestamo.getImporteCuota());
			ps.setInt(5, prestamo.getCantidadCuotas());
			ps.setFloat(6, (float) prestamo.getImporteAPagar());
			ps.setInt(7, prestamo.getIdTipoEstado());
			ps.setString(8, prestamo.getIdCuenta());
			ps.setInt(9, prestamo.getIdPrestamo());

			if (ps.executeUpdate() > 0) {
				conn.commit();
				exito = true;
			}
		} catch (SQLException e) {
			try { if (conn != null) conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
			e.printStackTrace();
		} finally {
			try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
			Conexion.cerrarConexion(conn);
		}

		return exito;
	}
	
	@Override
	public List<Prestamo> obtenerPrestamosPorDni(String dni) {
	    List<Prestamo> prestamos = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    String sql = "SELECT ID_Prestamo AS idPrestamo, Fecha_Solicitud AS fechaCreacion, Importe_Pedido AS montoPedido, " +
	            "Plazo_Pago_Meses AS plazoPago, Importe_Cuota AS importeCuota, Cantidad_Cuotas AS cantCuotas, " +
	            "Importe_a_Pagar AS importePagar, ID_Tipo_Estado AS idTipoEstado, ID_Cuenta_Acreditacion AS idCuenta, DNI AS dni " +
	            "FROM Prestamos WHERE DNI = ? AND ID_Tipo_Estado = 2";

	    try {
	        conn = Conexion.getConexion();
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, dni);
	        rs = ps.executeQuery();

            while (rs.next()) {
                prestamos.add(cargarPrestamoDesdeResultSet(rs));
            }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
        	try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            Conexion.cerrarConexion(conn);
	    }

	    return prestamos;
	}

	@Override
	public boolean agregarPrestamo(PrestamoBackup prestamo) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean exito = false;
		
		String sql = "INSERT INTO prestamos "
				+ "(DNI, Fecha_Solicitud, Importe_Pedido, Importe_a_Pagar, Cantidad_Cuotas, Importe_Cuota, ID_Tipo_Estado, CBU_Acreditacion) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement(sql);
			ps.setString(1, prestamo.getCliente().getDni());
			ps.setTimestamp(2, java.sql.Timestamp.valueOf(prestamo.getFechaSolicitud()));
			ps.setBigDecimal(3, prestamo.getImportePedido());
			ps.setBigDecimal(4, prestamo.getImporteAPagar());
			ps.setInt(5, prestamo.getCantidadCuotas());
			ps.setBigDecimal(6, prestamo.getImporteCuota());
			ps.setInt(7, prestamo.getTipoEstadoPrestamo().getIDTipoEstado());
			ps.setString(8, prestamo.getCuentaAcreditada().getCbu());

			if (ps.executeUpdate() > 0) {
				conn.commit();
				exito = true;
			}
		} catch (SQLException e) {
			try { if (conn != null) conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
			e.printStackTrace();
		} finally {
			try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
			Conexion.cerrarConexion(conn);
		}

		return exito;
	}

	@Override
	public List<PrestamoBackup> leerPrestamosPendientes() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<PrestamoBackup> listaPrestamosPendientes = new ArrayList<>();

		try {
			conn = Conexion.getConexion();
			String sql = "SELECT P.ID_Prestamo, P.CBU_Acreditacion, P.Fecha_Solicitud, P.Importe_Pedido, P.Importe_a_Pagar, P.Cantidad_Cuotas, P.Importe_Cuota, P.ID_Tipo_Estado FROM Prestamos AS P JOIN Tipos_Estado_Prestamo AS TEP ON P.ID_Tipo_Estado = TEP.ID_Tipo_Estado WHERE P.ID_Tipo_Estado = 1";

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				PrestamoBackup prestamo = new PrestamoBackup();

				prestamo.setIDPrestamo(rs.getInt("ID_Prestamo"));
                		prestamo.setFechaSolicitud(rs.getTimestamp("Fecha_Solicitud").toLocalDateTime());
                		prestamo.setImportePedido(rs.getBigDecimal("Importe_Pedido"));
                		prestamo.setImporteAPagar(rs.getBigDecimal("Importe_a_Pagar"));
                		prestamo.setCantidadCuotas(rs.getInt("Cantidad_Cuotas"));
                		prestamo.setImporte_Cuota(rs.getBigDecimal("Importe_Cuota"));

                		TipoEstadoPrestamo tipoEstado = new TipoEstadoPrestamo();
                		tipoEstado.setIDTipoEstado(rs.getInt("ID_Tipo_Estado"));
                		prestamo.setTipoEstadoPrestamo(tipoEstado);

                		Cuenta cuentaAcreditada = new Cuenta();
                		cuentaAcreditada.setCbu(rs.getString("CBU_Acreditacion"));
                		prestamo.setCuentaAcreditada(cuentaAcreditada);

				listaPrestamosPendientes.add(prestamo);
			}
		} catch (SQLException e) {
			System.err.println("ERROR DAO: Error al obtener los prestamos pendientes." + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Conexion.cerrarConexion(conn);
		}
		return listaPrestamosPendientes;
	}

	@Override
	public boolean aprobarPrestamo(int idPrestamo) {
		Connection conn = null;
        CallableStatement stmt = null;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareCall("{ CALL SP_AprobarPrestamo(?) }");
            stmt.setInt(1, idPrestamo);
            stmt.execute();
            conn.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            return false;

        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Conexion.cerrarConexion(conn);
        }
	}

	@Override
	public boolean rechazarPrestamo(int idPrestamo) {
		Connection conn = null;
	    PreparedStatement ps = null;
	    int filasAfectadas = 0;

	    try {
	        conn = Conexion.getConexion();

	        String sql = "UPDATE prestamos SET ID_Tipo_Estado = 3 WHERE ID_Prestamo = ?";

	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, idPrestamo);

	        filasAfectadas = ps.executeUpdate();

	        if (filasAfectadas > 0) {
	            conn.commit();
	            return true;  //éxito
	        } else {
	            conn.rollback();
	            return false; //no se actualizó nada
	        }

	    } catch (SQLException e) {
	        if (conn != null) {
	            try {
	                conn.rollback();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	        e.printStackTrace();
	        return false;  //error
	    } finally {
	        try {
	            if (ps != null) ps.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        Conexion.cerrarConexion(conn);
	    }
	}
}