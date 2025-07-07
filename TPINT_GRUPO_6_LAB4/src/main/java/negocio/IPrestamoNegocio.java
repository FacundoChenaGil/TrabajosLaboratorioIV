package negocio;

import java.util.List;

import entidad.Prestamo;


public interface IPrestamoNegocio {
	
		public List<Prestamo>obtenerPrestamosPorDni(String dni);
		
		List<Prestamo> obtenerPrestamosConCuotasPendientesPorDni(String dni);
}
