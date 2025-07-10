package negocio;

import java.math.BigDecimal;
import java.util.List;

import entidad.Prestamo;
import entidad.Prestamo;


public interface IPrestamoNegocio {
	
	public List<Prestamo>obtenerPrestamosPorDni(String dni);
	
	List<Prestamo> obtenerPrestamosConCuotasPendientesPorDni(String dni);
	List<Prestamo> obtenerPrestamosPorDniYEstado(String dni, int idEstado);
		
	public boolean agregarPrestamo(Prestamo prestamo);
	public BigDecimal calcularImporteAPagar(BigDecimal importe);
	public BigDecimal calcularImporteCuota(BigDecimal cantCuotas, BigDecimal importe);
	
	public List<Prestamo> leerPrestamosPendientes();
	public boolean aprobarPrestamo(int idPrestamo);
	public boolean rechazarPrestamo(int idPrestamo);
}
