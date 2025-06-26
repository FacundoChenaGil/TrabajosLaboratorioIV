package negocio;

import java.util.List;

import entidad.Cuenta;

public interface ICuentaNegocio {
	
	public List<Cuenta> readAll();
	public Cuenta read(String cbu);

}
