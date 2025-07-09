package negocio;

import java.math.BigDecimal;
import java.util.List;

import entidad.Prestamo;
import entidad.PrestamoBackup;


public interface IPrestamoNegocio {
	
		public List<Prestamo>obtenerPrestamosPorDni(String dni);
		
		List<Prestamo> obtenerPrestamosConCuotasPendientesPorDni(String dni);
		
		public boolean agregarPrestamo();
		public BigDecimal calcularImporteAPagar(BigDecimal importe);
		public BigDecimal calcularImporteCuota(BigDecimal cantCuotas, BigDecimal importe);
}
