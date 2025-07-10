package negocioImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.IClienteDao;
import dao.ICuentaDao;
import dao.ICuotaDao;
import dao.IPrestamoDao;
import dao.ITipoEstadoPrestamoDao;
import daoImpl.ClienteDaoImpl;
import daoImpl.CuentaDaoImpl;
import daoImpl.CuotaDaoImpl;
import daoImpl.PrestamoDaoImpl;
import daoImpl.TipoEstadoPrestamoDaoImpl;
import entidad.Cuota;
import entidad.Prestamo;
import negocio.IPrestamoNegocio;

public class PrestamoNegocioImpl implements IPrestamoNegocio {

	private IPrestamoDao prestamoDao = new PrestamoDaoImpl();
	private ICuotaDao cuotaDao = new CuotaDaoImpl();
	private IClienteDao clienteDao = new ClienteDaoImpl();
	private ITipoEstadoPrestamoDao tsDao = new TipoEstadoPrestamoDaoImpl();

	public PrestamoNegocioImpl() {
	}



	@Override
	public List<Prestamo> obtenerPrestamosPorDni(String dni) {
		List<Prestamo> prestamos = prestamoDao.obtenerPrestamosPorDni(dni);
		for (Prestamo prestamo : prestamos) {
			prestamo.setCliente(clienteDao.obtenerClientePorDni(prestamo.getCliente().getDni()));
			prestamo.setTipoEstadoPrestamo(tsDao.read(prestamo.getTipoEstadoPrestamo().getIDTipoEstado()));
		}
		return prestamos;
	}

	@Override
	public List<Prestamo> obtenerPrestamosFinalizadosPorDni(String dni) {
		return obtenerPrestamosPorDniYEstado(dni, 4);
	}

	@Override
	public List<Prestamo> obtenerPrestamosConCuotasPendientesPorDni(String dni) {
		List<Prestamo> prestamosAprobados = prestamoDao.obtenerPrestamosPorDniYEstado(dni, 2); // 2 = Aprobado
		List<Prestamo> prestamosConCuotasPendientes = new ArrayList<>();

		for (Prestamo prestamo : prestamosAprobados) {
			// Completamos la información del préstamo antes de devolverlo
			prestamo.setCliente(clienteDao.obtenerClientePorDni(prestamo.getCliente().getDni()));
			prestamo.setTipoEstadoPrestamo(tsDao.read(prestamo.getTipoEstadoPrestamo().getIDTipoEstado()));
			List<Cuota> cuotasPendientes = cuotaDao.obtenerCuotasPendientesPorPrestamo(prestamo);
			if (!cuotasPendientes.isEmpty()) {
				prestamo.setCuotasPendientes(cuotasPendientes);
				prestamo.setPrimeraCuotaId(cuotasPendientes.get(0).getIdCuota()); // Establece la primera cuota como pagable
				prestamosConCuotasPendientes.add(prestamo);
			}
		}

		return prestamosConCuotasPendientes;
	}

	@Override
	public List<Prestamo> leerPrestamosPendientes() {
		List<Prestamo> prestamos = prestamoDao.leerPrestamosPendientes();
		for (Prestamo prestamo : prestamos) {
			prestamo.setCliente(clienteDao.obtenerClientePorDni(prestamo.getCliente().getDni()));
			prestamo.setTipoEstadoPrestamo(tsDao.read(prestamo.getTipoEstadoPrestamo().getIDTipoEstado()));
		}
		return prestamos;
	}

	@Override
	public boolean aprobarPrestamo(int idPrestamo) {
		// 1. Obtener el préstamo completo para tener todos los datos necesarios
		Prestamo prestamo = prestamoDao.obtenerPrestamoPorId(idPrestamo);
		if (prestamo == null) {
			return false; // El préstamo no existe
		}

		// 2. Generar las cuotas
		boolean cuotasGeneradas = generarCuotas(prestamo);
		if (!cuotasGeneradas) {
			// Aquí iría la lógica de rollback en una transacción real
			return false;
		}

		// 3. Cambiar el estado del préstamo a Aprobado
		return prestamoDao.aprobarPrestamo(idPrestamo);
	}

	@Override
	public boolean rechazarPrestamo(int idPrestamo) {
		return prestamoDao.rechazarPrestamo(idPrestamo);
	}

	@Override
	public boolean generarCuotas(Prestamo prestamo) {
		int cantidadCuotas = prestamo.getCantidadCuotas();
		LocalDate fecha = LocalDate.now();

		for (int i = 1; i <= cantidadCuotas; i++) {
			Cuota cuota = new Cuota();
			cuota.setPrestamo(prestamo);
			cuota.setNumeroCuota(i);
			cuota.setImporte(prestamo.getImporteCuota());
			cuota.setFechaVencimiento(fecha.plusMonths(i));

			if (!cuotaDao.agregarCuota(cuota)) {
				// En un escenario real, esto debería provocar un rollback de la transacción
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean agregarPrestamo(Prestamo prestamo) {
		return prestamoDao.solicitarPrestamo(prestamo);
	}

	@Override
	public BigDecimal calcularImporteAPagar(BigDecimal importe) {
		// Se asume una tasa de interés fija del 10% para el cálculo.
		BigDecimal interes = new BigDecimal("1.10");
		return importe.multiply(interes);
	}

	@Override
	public BigDecimal calcularImporteCuota(BigDecimal cantCuotas, BigDecimal importeAPagar) {
		if (cantCuotas == null || cantCuotas.compareTo(BigDecimal.ZERO) <= 0) {
			return BigDecimal.ZERO; // Evitar división por cero
		}
		// Se divide con 2 decimales y redondeo hacia arriba.
		return importeAPagar.divide(cantCuotas, 2, java.math.RoundingMode.HALF_UP);
	}



	@Override
	public List<Prestamo> obtenerPrestamosPorDniYEstado(String dni, int idEstado) {
		List<Prestamo> prestamos = prestamoDao.obtenerPrestamosPorDniYEstado(dni, idEstado);
		for (Prestamo prestamo : prestamos) {
			prestamo.setCliente(clienteDao.obtenerClientePorDni(prestamo.getCliente().getDni()));
			prestamo.setTipoEstadoPrestamo(tsDao.read(prestamo.getTipoEstadoPrestamo().getIDTipoEstado()));
		}
		return prestamos;
	}
}
