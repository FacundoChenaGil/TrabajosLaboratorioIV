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
			+ "(Fecha_Creacion, Fecha_Fin, Monto_Pedido, Plazo_de_pago, Importe_cuota, Cant_cuotas, Importe_a_pagar, Id_Tipo_Estado, IdCuenta) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public boolean agregar(Prestamo prestamo) {
		boolean insertExitoso = false;

		try (Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement statement = conexion.prepareStatement(INSERT)) {

			statement.setDate(1, java.sql.Date.valueOf(prestamo.getFecha_creacion()));
			statement.setDate(2, java.sql.Date.valueOf(prestamo.getFecha_fin()));
			statement.setFloat(3, (float) prestamo.getMonto_pedido());
			statement.setInt(4, prestamo.getPlazo_de_pago());
			statement.setFloat(5, (float) prestamo.getImporte_cuota());
			statement.setInt(6, prestamo.getCant_cuotas());
			statement.setFloat(7, (float) prestamo.getImporte_a_pagar());
			statement.setInt(8, prestamo.getIdTipoEstado());
			statement.setInt(9, prestamo.getIdCuenta());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				insertExitoso = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return insertExitoso;
	}

	private static final int ESTADO_ELIMINADO = 2;

	// OBTENER PRESTAMO POR ID

	private static final String SELECT_BY_ID = "SELECT * FROM Prestamos WHERE IdPrestamo = ? AND Id_Tipo_Estado != "
			+ ESTADO_ELIMINADO;

	public Prestamo obtenerPrestamoPorId(int idPrestamo) {
		Prestamo prestamo = null;

		try (Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement statement = conexion.prepareStatement(SELECT_BY_ID)) {

			statement.setInt(1, idPrestamo);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					prestamo = cargarPrestamoDesdeResultSet(resultSet);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prestamo;
	}

	private static final String SELECT_ACTIVOS = "SELECT * FROM Prestamos WHERE Id_Tipo_Estado != " + ESTADO_ELIMINADO;

	public List<Prestamo> obtenerTodos() {
		List<Prestamo> prestamos = new ArrayList<>();

		try (Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement statement = conexion.prepareStatement(SELECT_ACTIVOS);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				prestamos.add(cargarPrestamoDesdeResultSet(resultSet));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prestamos;
	}

	// ELIMINAR

	public boolean eliminar(int idPrestamo) {
		String sql = "UPDATE Prestamos SET Id_Tipo_Estado = ? WHERE IdPrestamo = ?";
		boolean bajaLogicaExitosa = false;

		try (Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement statement = conexion.prepareStatement(sql)) {

			statement.setInt(1, ESTADO_ELIMINADO);
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
		prestamo.setIdPrestamo((rs.getInt("IdPrestamo")));
		prestamo.setFecha_creacion(rs.getDate("Fecha_Creacion").toLocalDate());
		prestamo.setFecha_fin(rs.getDate("Fecha_Fin").toLocalDate());
		prestamo.setImporte_a_pagar(rs.getFloat("importe_a_pagar"));
		prestamo.setMonto_pedido(rs.getFloat("Monto_Pedido"));
		prestamo.setPlazo_de_pago(rs.getInt("Plazo_de_pago"));
		prestamo.setImporte_cuota(rs.getFloat("Importe_cuota"));
		prestamo.setCant_cuotas(rs.getInt("Cant_cuotas"));
		prestamo.setIdTipoEstado(rs.getInt("Id_Tipo_Estado"));
		prestamo.setIdCuenta(rs.getInt("IdCuenta"));

		return prestamo;

		// no valido las fechas ya que la base de datos tiene todos atributos not null

	}

	// MODIFICAR

	private static final String UPDATE = "UPDATE Prestamos SET " + "Fecha_Creacion = ?, " + "Fecha_Fin = ?, "
			+ "Monto_Pedido = ?, " + "Plazo_de_pago = ?, " + "Importe_cuota = ?, " + "Cant_cuotas = ?, "
			+ "Importe_a_pagar = ?, " + "Id_Tipo_Estado = ?, " + "IdCuenta = ? " + "WHERE IdPrestamo = ?";

	public boolean modificar(Prestamo prestamo) {
		boolean updateExitoso = false;

		try (Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement statement = conexion.prepareStatement(UPDATE)) {

			statement.setDate(1, java.sql.Date.valueOf(prestamo.getFecha_creacion()));
			statement.setDate(2, java.sql.Date.valueOf(prestamo.getFecha_fin()));
			statement.setFloat(3, (float) prestamo.getMonto_pedido());
			statement.setInt(4, prestamo.getPlazo_de_pago());
			statement.setFloat(5, (float) prestamo.getImporte_cuota());
			statement.setInt(6, prestamo.getCant_cuotas());
			statement.setFloat(7, (float) prestamo.getImporte_a_pagar());
			statement.setInt(8, prestamo.getIdTipoEstado());
			statement.setInt(9, prestamo.getIdCuenta());
			statement.setInt(10, prestamo.getIdPrestamo());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				updateExitoso = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return updateExitoso;
	}

}
