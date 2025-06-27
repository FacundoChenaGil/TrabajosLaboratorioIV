package negocioImpl;

import java.util.List;

import entidad.TiposDeCuentas;
import negocio.ITipoDeCuentaNegocio;
import dao.ITipoDeCuentaDao;
import daoImpl.TipoDeCuentaDaoImpl;

public class TipoDeCuentaNegocioImpl implements ITipoDeCuentaNegocio{
	
	private ITipoDeCuentaDao tipoCuentaDao = new TipoDeCuentaDaoImpl();

	@Override
	public List<TiposDeCuentas> listarTiposDeCuentas() {
		 return tipoCuentaDao.listarTiposDeCuentas();
	}

	@Override
	public TiposDeCuentas getTipoCuentaPorID(int idTipoCuenta) {
		
		TiposDeCuentas tipoCuenta = tipoCuentaDao.getTipoCuentaPorID(idTipoCuenta);
		
		return tipoCuenta;
	}
}
