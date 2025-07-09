package negocioImpl;

import entidad.TipoEstadoPrestamo;
import negocio.ITipoEstadoPrestamoNegocio;
import dao.ITipoEstadoPrestamoDao;
import daoImpl.TipoEstadoPrestamoDaoImpl;

public class TipoEstadoPrestamoNegocioImpl implements ITipoEstadoPrestamoNegocio {
	
	private ITipoEstadoPrestamoDao tsDao = new TipoEstadoPrestamoDaoImpl();

	
	
	public TipoEstadoPrestamoNegocioImpl() {
		
	}



	public TipoEstadoPrestamoNegocioImpl(ITipoEstadoPrestamoDao tsDao) {
		this.tsDao = tsDao;
	}



	@Override
	public TipoEstadoPrestamo read(int id) {
		TipoEstadoPrestamo ts = tsDao.read(id);
		return ts;
	}

}
