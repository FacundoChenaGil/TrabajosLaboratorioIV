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


	@Override
	public int insertarCuenta(Cuenta cuenta) {
		return cDao.insertarCuenta(cuenta);
	}


	@Override
	public boolean existeCBU(String cbu) {
		return cDao.existeCBU(cbu);
	}


	@Override
	public boolean existeNumeroCuenta(String numeroCuenta) {
		return cDao.existeNumeroCuenta(numeroCuenta);
	}


	@Override
	public int contarCuentasPorDni(String dni) {
		return cDao.contarCuentasPorDni(dni);
	}

}
