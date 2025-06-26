package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import daoImpl.CuentaDaoImpl;
import entidad.Cuenta;
import negocio.ICuentaNegocio;

public class CuentaNegocioImpl implements ICuentaNegocio {
	
	private CuentaDaoImpl cDao = new CuentaDaoImpl();


	@Override
	public List<Cuenta> readAll() {
		
		List<Cuenta> listaCuentas = new ArrayList<>();
		
		listaCuentas = cDao.readAll();
		
		return listaCuentas;
	}


	@Override
	public Cuenta read(String cbu) {
		Cuenta cuenta = new Cuenta();
		
		cuenta = cDao.read(cbu);
		
		return cuenta;
	}

}
