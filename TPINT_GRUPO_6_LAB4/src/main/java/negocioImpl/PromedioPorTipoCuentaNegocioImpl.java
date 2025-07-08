package negocioImpl;

import java.util.List;

import entidad.PromedioPorTipoCuenta;
import negocio.IPromedioPorTipoCuentaNegocio;
import dao.IPromedioPorTipoCuentaDao;
import daoImpl.PromedioPorTipoCuentaDaoImpl;

public class PromedioPorTipoCuentaNegocioImpl implements IPromedioPorTipoCuentaNegocio{

	
	private IPromedioPorTipoCuentaDao promedioDao = new PromedioPorTipoCuentaDaoImpl();

    @Override
    public List<PromedioPorTipoCuenta> obtenerPromedios() {
        return promedioDao.obtenerPromedioSaldoPorTipoCuenta();
	
    }
}