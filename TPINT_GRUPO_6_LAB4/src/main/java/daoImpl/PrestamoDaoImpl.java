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
import entidad.Prestamo;

import entidad.TipoEstadoPrestamo;
import entidad.Cliente;
import entidad.Cuenta;

public class PrestamoDaoImpl implements IPrestamoDao {

	private static final String INSERT = "INSERT INTO Prestamos "
		    + "(DNI, Fecha_Solicitud, Importe_Pedido, Importe_Cuota, Cantidad_Cuotas, Importe_a_Pagar, ID_Tipo_Estado, CBU_Acreditacion) "
		    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String OBTENER_POR_DNI_Y_ESTADO = "SELECT * FROM Prestamos WHERE DNI = ? AND ID_Tipo_Estado = ?";
	private static final int ESTADO_APROBADO = 2;
	private static final String SELECT_BY_ID =
		    "SELECT `ID Prestamo` AS idPrestamo, DNI as dni, `Fecha Solicitud` AS fechaCreacion, `Importe Pedido` AS montoPedido, " +
		    "`Importe Cuota` AS importeCuota, `Cantidad Cuotas` AS cantCuotas, " +
		    "`Importe a Pagar` AS importePagar, `ID Tipo Estado` AS idTipoEstado, CBU_Acreditacion AS idCuenta " +
		    "FROM Prestamos WHERE `ID Prestamo` = ? AND `ID Tipo Estado` != " + ESTADO_APROBADO;
	private static final String UPDATE = "UPDATE Prestamos SET Fecha_Solicitud = ?, Importe_Pedido = ?, " +
            "Importe_Cuota = ?, Cantidad_Cuotas = ?, Importe_a_Pagar = ?, ID_Tipo_Estado = ?, CBU_Acreditacion = ? " +
            "WHERE `ID Prestamo` = ?";

	@Override
	public boolean agregar(Prestamo prestamo) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean exito = false;

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement(INSERT);
			ps.setString(1, prestamo.getCliente().getDni());
			ps.setDate(2, java.sql.Date.valueOf(prestamo.getFechaSolicitud().toLocalDate()));
			ps.setBigDecimal(3, prestamo.getImportePedido());
			ps.setBigDecimal(4, prestamo.getImporteCuota());
			ps.setInt(5, prestamo.getCantidadCuotas());
			ps.setBigDecimal(6, prestamo.getImporteAPagar());
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
	    prestamo.setIDPrestamo(rs.getInt("ID_Prestamo"));
	    prestamo.setFechaSolicitud(rs.getTimestamp("Fecha_Solicitud").toLocalDateTime());
	    prestamo.setImportePedido(rs.getBigDecimal("Importe_Pedido"));
	    prestamo.setImporteCuota(rs.getBigDecimal("Importe_Cuota"));
	    prestamo.setCantidadCuotas(rs.getInt("Cantidad_Cuotas"));
	    prestamo.setImporteAPagar(rs.getBigDecimal("Importe_a_Pagar"));

	    TipoEstadoPrestamo tep = new TipoEstadoPrestamo();
	    tep.setIDTipoEstado(rs.getInt("ID_Tipo_Estado"));
	    prestamo.setTipoEstadoPrestamo(tep);

	    Cuenta c = new Cuenta();
	    c.setCbu(rs.getString("CBU_Acreditacion"));
	    prestamo.setCuentaAcreditada(c);

	    Cliente cl = new Cliente();
	    cl.setDni(rs.getString("DNI"));
	    prestamo.setCliente(cl);
	    return prestamo;
	}

	@Override
	public List<Prestamo> obtenerPrestamosPorDniYEstado(String dni, int idEstado) {
	    List<Prestamo> prestamos = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = Conexion.getConexion();
	        ps = conn.prepareStatement(OBTENER_POR_DNI_Y_ESTADO);
	        ps.setString(1, dni);
	        ps.setInt(2, idEstado);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            prestamos.add(cargarPrestamoDesdeResultSet(rs));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Manejo de la excepción
	    } finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
	        Conexion.cerrarConexion(conn);
	    }

	    return prestamos;
	}
	@Override
	public boolean modificar(Prestamo prestamo) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean exito = false;

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement(UPDATE);
			ps.setDate(1, java.sql.Date.valueOf(prestamo.getFechaSolicitud().toLocalDate()));
			ps.setBigDecimal(2, prestamo.getImportePedido());
			ps.setBigDecimal(3, prestamo.getImporteCuota());
			ps.setInt(4, prestamo.getCantidadCuotas());
			ps.setBigDecimal(5, prestamo.getImporteAPagar());
			ps.setInt(6, prestamo.getTipoEstadoPrestamo().getIDTipoEstado());
			ps.setString(7, prestamo.getCuentaAcreditada().getCbu());
			ps.setInt(8, prestamo.getIDPrestamo());

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
	    String sql = "SELECT `ID Prestamo` AS idPrestamo, DNI as dni, `Fecha Solicitud` AS fechaCreacion, `Importe Pedido` AS montoPedido, " +
	    		"`Importe Cuota` AS importeCuota, `Cantidad Cuotas` AS cantCuotas, " +
	    		"`Importe a Pagar` AS importePagar, `ID Tipo Estado` AS idTipoEstado, CBU_Acreditacion AS idCuenta " +
	            "FROM Prestamos WHERE DNI = ? AND `ID Tipo Estado` = 2";

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
	public boolean agregarPrestamo(Prestamo prestamo) {
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
	public List<Prestamo> leerPrestamosPendientes() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Prestamo> listaPrestamosPendientes = new ArrayList<>();

		try {
			conn = Conexion.getConexion();
			String sql = "SELECT P.ID_Prestamo, P.CBU_Acreditacion, P.Fecha_Solicitud, P.Importe_Pedido, P.Importe_a_Pagar, P.Cantidad_Cuotas, P.Importe_Cuota, P.ID_Tipo_Estado, C.DNI FROM Prestamos AS P JOIN Cuentas AS C ON P.CBU_Acreditacion = C.CBU WHERE P.ID_Tipo_Estado = 1";

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Prestamo prestamo = new Prestamo();

				prestamo.setIDPrestamo(rs.getInt("ID_Prestamo"));
						Cliente cl = new Cliente();
						cl.setDni(rs.getString("DNI"));
						prestamo.setCliente(cl);
                		prestamo.setFechaSolicitud(rs.getTimestamp("Fecha_Solicitud").toLocalDateTime());
                		prestamo.setImportePedido(rs.getBigDecimal("Importe_Pedido"));
                		prestamo.setImporteAPagar(rs.getBigDecimal("Importe_a_Pagar"));
                		prestamo.setCantidadCuotas(rs.getInt("Cantidad_Cuotas"));
                		prestamo.setImporteCuota(rs.getBigDecimal("Importe_Cuota"));

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