package negocio;

import java.util.List;
import java.math.BigDecimal;

import entidad.Cuenta;
import entidad.CuentaPrestamoddlDTO;


public interface ICuentaNegocio {
	
	public int insertarCuenta(Cuenta cuenta);
	public boolean existeCBU(String cbu);
	public boolean existeNumeroCuenta(String numeroCuenta);
	public int contarCuentasPorDni(String dni);
	public List<Cuenta> readAll();
	public Cuenta read(String cbu);
	public boolean actualizarCuenta(Cuenta cuenta);
	public boolean eliminarCuenta(String cbu);
	public List<CuentaPrestamoddlDTO> CargarDDL(String dni);
	BigDecimal obtenerSaldo(String cbu);
    boolean actualizarSaldo(String cbu, BigDecimal nuevoSaldo);
    public String obtenerNombreTitular(String cbu);
    public String obtenerDniTitular(String cbu);

	

}
