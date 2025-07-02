package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import daoImpl.ClienteDaoImpl;
import daoImpl.CuentaDaoImpl;
import daoImpl.TipoDeCuentaDaoImpl;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.CuentaPrestamoddlDTO;
import entidad.TiposDeCuentas;
import negocio.ICuentaNegocio;

public class CuentaNegocioImpl implements ICuentaNegocio {
	
	private CuentaDaoImpl cDao = new CuentaDaoImpl();
	private ClienteDaoImpl clDao = new ClienteDaoImpl();
	private TipoDeCuentaDaoImpl tcDao = new TipoDeCuentaDaoImpl();
	


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


	@Override
	public boolean actualizarCuenta(Cuenta cuenta) {
		
		boolean actualizado = cDao.actualizarCuenta(cuenta);
		
		return actualizado;
	}


	@Override
	public boolean eliminarCuenta(String cbu) {
		
		boolean eliminado = cDao.eliminarCuenta(cbu);
		
		return eliminado;
	}


	@Override
	public List<CuentaPrestamoddlDTO> CargarDDL(String dni) {
		
		List<CuentaPrestamoddlDTO> cuentasDDL = new ArrayList<>();
		
		cuentasDDL = cDao.CargarDDl(dni);
		
		return cuentasDDL;
	}

}
