package dao;

import java.util.List;

import entidad.TiposDeCuentas;

public interface ITipoDeCuentaDao {
	public List<TiposDeCuentas> listarTiposDeCuentas();
	public TiposDeCuentas getTipoCuentaPorID(int idTipoCuenta);
}
