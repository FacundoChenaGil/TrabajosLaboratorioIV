package negocio;

import java.math.BigDecimal;
import java.util.List;

import entidad.Prestamo;

public interface IPrestamoNegocio {

	// Métodos para clientes
	List<Prestamo> obtenerPrestamosPorDni(String dni);
	List<Prestamo> obtenerPrestamosConCuotasPendientesPorDni(String dni);
	List<Prestamo> obtenerPrestamosFinalizadosPorDni(String dni);
	boolean agregarPrestamo(Prestamo prestamo);
	BigDecimal calcularImporteAPagar(BigDecimal importe);
	BigDecimal calcularImporteCuota(BigDecimal cantCuotas, BigDecimal importeAPagar);

	// Métodos para administradores
	List<Prestamo> leerPrestamosPendientes();
	boolean aprobarPrestamo(int idPrestamo);
	boolean rechazarPrestamo(int idPrestamo);
	boolean generarCuotas(Prestamo prestamo);
	List<Prestamo> obtenerPrestamosPorDniYEstado(String dni, int estadoActivo);

}
