package negocio;

import java.util.List;

import entidad.Cuenta;

public interface ICuentaNegocio {
	
	public int insertarCuenta(Cuenta cuenta);
	public boolean existeCBU(String cbu);
	public boolean existeNumeroCuenta(String numeroCuenta);
	public int contarCuentasPorDni(String dni);
	public List<Cuenta> readAll();
	public Cuenta read(String cbu);
	public boolean actualizarCuenta(Cuenta cuenta);
	public boolean eliminarCuenta(String cbu);

}
