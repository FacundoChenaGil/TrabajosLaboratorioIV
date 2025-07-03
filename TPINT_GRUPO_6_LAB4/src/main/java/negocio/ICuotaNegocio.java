package negocio;

import java.util.List;
import entidad.Cuota;


public interface ICuotaNegocio {
	
	public List<Cuota> listarHistorialPagos(int idPrestamo);
	
	
	

}
