package negocioImpl;


import java.util.List;

import dao.ICuotaDao;
import daoImpl.CuotaDaoImpl;
import entidad.Cuota;
import negocio.ICuotaNegocio;


public class CuotaNegocioImpl implements ICuotaNegocio{
	
	private ICuotaDao cuotaDao = new CuotaDaoImpl();
	
	public List<Cuota>listarHistorialPagos(int idPrestamo){
		return cuotaDao.obtenerCuotasPagadasPorPrestamo(idPrestamo);
		
	}
}
