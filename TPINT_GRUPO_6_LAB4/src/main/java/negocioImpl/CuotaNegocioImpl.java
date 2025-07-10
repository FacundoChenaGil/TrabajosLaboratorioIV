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
	@Override
	public boolean pagarCuota(int cuotaId, String numeroCuenta) {
		return cuotaDao.pagarCuota(cuotaId, numeroCuenta);
	}
	
	public Cuota obtenerPorId(int idCuota) {
	    return cuotaDao.obtenerPorId(idCuota);
	}
	
}
