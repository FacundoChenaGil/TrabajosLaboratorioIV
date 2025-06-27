package negocio;

import java.util.List;

import entidad.TiposDeCuentas;

public interface ITipoDeCuentaNegocio {
	List<TiposDeCuentas> listarTiposDeCuentas();
	public TiposDeCuentas getTipoCuentaPorID(int idTipoCuenta);
	
	
}
