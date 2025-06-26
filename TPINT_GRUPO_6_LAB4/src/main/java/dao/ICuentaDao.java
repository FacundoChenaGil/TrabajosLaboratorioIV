package dao;

import java.util.List;

import entidad.Cuenta;

public interface ICuentaDao {

	public List<Cuenta> readAll();
	public Cuenta read(String cbu);
}
