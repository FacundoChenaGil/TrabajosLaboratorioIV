package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IPrestamoDao;
import entidad.Prestamo;

public class PrestamoDaoImpl implements IPrestamoDao {

	// AGREGAR

	private static final String INSERT = "INSERT INTO Prestamos "
		    + "(DNI, Fecha_Solicitud, Importe_Pedido, Plazo_Pago_Meses, Importe_Cuota, Cantidad_Cuotas, Importe_a_Pagar, ID_Tipo_Estado, ID_Cuenta_Acreditacion) "
		    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public boolean agregar(Prestamo prestamo) {
		boolean insertExitoso = false;

		try (Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement statement = conexion.prepareStatement(INSERT)) {

			statement.setString(1, prestamo.getDNI());
			statement.setDate(2, java.sql.Date.valueOf(prestamo.getFecha_creacion()));
			statement.setFloat(3, (float) prestamo.getMonto_pedido());
			statement.setInt(4, prestamo.getPlazo_de_pago());
			statement.setFloat(5, (float) prestamo.getImporte_cuota());
			statement.setInt(6, prestamo.getCant_cuotas());
			statement.setFloat(7, (float) prestamo.getImporte_a_pagar());
			statement.setInt(8, prestamo.getIdTipoEstado());
			statement.setString(9, prestamo.getIdCuenta());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				insertExitoso = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return insertExitoso;
	}

	private static final int ESTADO_APROBADO = 2;

	
	//OBTENER PRESTAMO POR ID
	private static final String SELECT_BY_ID =
		    "SELECT " +
		    "`ID Prestamo` AS idPrestamo, " +
		    "`Fecha Solicitud` AS fechaCreacion, " +
		    "`Importe Pedido` AS montoPedido, " +
		    "`Plazo Pago Meses` AS plazoPago, " +
		    "`Importe Cuota` AS importeCuota, " +
		    "`Cantidad Cuotas` AS cantCuotas, " +
		    "`Importe a Pagar` AS importePagar, " +
		    "`ID Tipo Estado` AS idTipoEstado, " +
		    "`ID Cuenta Acreditacion` AS idCuenta " +
		    "FROM Prestamos WHERE `ID Prestamo` = ? AND `ID Tipo Estado` != " + ESTADO_APROBADO;

	
			
	public Prestamo obtenerPrestamoPorId(int idPrestamo) {
        Prestamo prestamo = null;

        try (Connection conexion = Conexion.getConexion().getSQLConexion();
             PreparedStatement statement = conexion.prepareStatement(SELECT_BY_ID)) {

            statement.setInt(1, idPrestamo);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    prestamo = cargarPrestamoDesdeResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prestamo;
    }
	// ELIMINAR

	public boolean eliminar(int idPrestamo) {
		String sql = "UPDATE Prestamos SET Id_Tipo_Estado = ? WHERE IdPrestamo = ?";
		boolean bajaLogicaExitosa = false;

		try (Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement statement = conexion.prepareStatement(sql)) {

			statement.setInt(1, ESTADO_APROBADO);
			statement.setInt(2, idPrestamo);

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				bajaLogicaExitosa = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bajaLogicaExitosa;
	}

	// CARGAR PRESTAMO DESDE RESULTSET

	private Prestamo cargarPrestamoDesdeResultSet(ResultSet rs) throws SQLException {
	    Prestamo prestamo = new Prestamo();

	    prestamo.setDNI(rs.getString("dni"));
	    prestamo.setIdPrestamo(rs.getInt("idPrestamo"));
	    prestamo.setFecha_creacion(rs.getDate("fechaCreacion").toLocalDate());
	    prestamo.setMonto_pedido(rs.getFloat("montoPedido"));
	    prestamo.setPlazo_de_pago(rs.getInt("plazoPago"));
	    prestamo.setImporte_cuota(rs.getFloat("importeCuota"));
	    prestamo.setCant_cuotas(rs.getInt("cantCuotas"));
	    prestamo.setImporte_a_pagar(rs.getFloat("importePagar"));
	    prestamo.setIdTipoEstado(rs.getInt("idTipoEstado"));
	    prestamo.setIdCuenta(rs.getString("idCuenta"));

	    return prestamo;
	
		// no valido las fechas ya que la base de datos tiene todos atributos not null

	}

	// MODIFICAR

	private static final String UPDATE = "UPDATE Prestamos SET " + "Fecha_Creacion = ?, "
			+ "Monto_Pedido = ?, " + "Plazo_de_pago = ?, " + "Importe_cuota = ?, " + "Cant_cuotas = ?, "
			+ "Importe_a_pagar = ?, " + "Id_Tipo_Estado = ?, " + "IdCuenta = ? " + "WHERE IdPrestamo = ?";

	public boolean modificar(Prestamo prestamo) {
		boolean updateExitoso = false;

		try (Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement statement = conexion.prepareStatement(UPDATE)) {

			statement.setDate(1, java.sql.Date.valueOf(prestamo.getFecha_creacion()));
			statement.setFloat(2, (float) prestamo.getMonto_pedido());
			statement.setInt(3, prestamo.getPlazo_de_pago());
			statement.setFloat(4, (float) prestamo.getImporte_cuota());
			statement.setInt(5, prestamo.getCant_cuotas());
			statement.setFloat(6, (float) prestamo.getImporte_a_pagar());
			statement.setInt(7, prestamo.getIdTipoEstado());
			statement.setString(8, prestamo.getIdCuenta());
			statement.setInt(9, prestamo.getIdPrestamo());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				updateExitoso = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return updateExitoso;
	}
	
	public List<Prestamo> obtenerPrestamosPorDni(String dni) {
	    List<Prestamo> prestamos = new ArrayList<>();

	    String sql = "SELECT " +
	            "ID_Prestamo AS idPrestamo, " +
	            "Fecha_Solicitud AS fechaCreacion, " +
	            "Importe_Pedido AS montoPedido, " +
	            "Plazo_Pago_Meses AS plazoPago, " +
	            "Importe_Cuota AS importeCuota, " +
	            "Cantidad_Cuotas AS cantCuotas, " +
	            "Importe_a_Pagar AS importePagar, " +
	            "ID_Tipo_Estado AS idTipoEstado, " +
	            "ID_Cuenta_Acreditacion AS idCuenta, " +
	            "DNI AS dni " +
	            "FROM Prestamos " +
	            "WHERE DNI = ? AND ID_Tipo_Estado = 2";

	    try (Connection conexion = Conexion.getConexion().getSQLConexion();
	         PreparedStatement statement = conexion.prepareStatement(sql)) {

	        statement.setString(1, dni);

	        try (ResultSet rs = statement.executeQuery()) {
	            while (rs.next()) {
	                prestamos.add(cargarPrestamoDesdeResultSet(rs));
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return prestamos;
	}
}