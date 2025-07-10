package daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IPrestamoDao;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Prestamo;
import entidad.TipoEstadoPrestamo;

public class PrestamoDaoImpl implements IPrestamoDao {

    private static final String INSERT = "INSERT INTO Prestamos "
            + "(DNI, Fecha_Solicitud, Importe_Pedido, Importe_Cuota, Cantidad_Cuotas, Importe_a_Pagar, ID_Tipo_Estado, CBU_Acreditacion) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String OBTENER_POR_DNI_Y_ESTADO = "SELECT * FROM Prestamos WHERE DNI = ? AND ID_Tipo_Estado = ?";
    private static final String OBTENER_POR_ID = "SELECT * FROM Prestamos WHERE ID_Prestamo = ?";
    private static final String LEER_PENDIENTES = "SELECT P.ID_Prestamo, P.CBU_Acreditacion, P.Fecha_Solicitud, P.Importe_Pedido, P.Importe_a_Pagar, P.Cantidad_Cuotas, P.Importe_Cuota, P.ID_Tipo_Estado, C.DNI FROM Prestamos AS P INNER JOIN Cuentas AS C ON P.CBU_Acreditacion = C.CBU WHERE P.ID_Tipo_Estado = 1";
    private static final String RECHAZAR_PRESTAMO = "UPDATE Prestamos SET ID_Tipo_Estado = 3 WHERE ID_Prestamo = ?";

    @Override
    public boolean agregarPrestamo(Prestamo prestamo) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean exito = false;

        try {
            conn = Conexion.getConexion();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, prestamo.getCliente().getDni());
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(prestamo.getFechaSolicitud()));
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
            ps = conn.prepareStatement(OBTENER_POR_ID);
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
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            Conexion.cerrarConexion(conn);
        }

        return prestamos;
    }

    @Override
    public List<Prestamo> leerPrestamosPendientes() {
        List<Prestamo> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConexion();
            ps = conn.prepareStatement(LEER_PENDIENTES);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(cargarPrestamoDesdeResultSet(rs));
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
        boolean exito = false;
        try {
            conn = Conexion.getConexion();
            ps = conn.prepareStatement(RECHAZAR_PRESTAMO);
            ps.setInt(1, idPrestamo);
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

        Cliente cliente = new Cliente();
        cliente.setDni(rs.getString("DNI"));
        prestamo.setCliente(cliente);

        TipoEstadoPrestamo estado = new TipoEstadoPrestamo();
        estado.setIDTipoEstado(rs.getInt("ID_Tipo_Estado"));
        prestamo.setTipoEstadoPrestamo(estado);

        Cuenta cuenta = new Cuenta();
        cuenta.setCbu(rs.getString("CBU_Acreditacion"));
        prestamo.setCuentaAcreditada(cuenta);

        return prestamo;
    }

	@Override
	public List<Prestamo> obtenerPrestamosPorDni(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean solicitarPrestamo(Prestamo prestamo) {
		// Este método llama al método agregarPrestamo, que ya tiene la lógica de inserción.
		// Se mantiene por compatibilidad con la capa de negocio que lo llama.
		return agregarPrestamo(prestamo);
	}

	@Override
	public List<Prestamo> obtenerPrestamosConCuotasPendientesPorDni(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Prestamo> obtenerPrestamosFinalizadosPorDni(String dni) {
		// El estado 4 corresponde a 'Finalizado'
		return obtenerPrestamosPorDniYEstado(dni, 4);
	}
}