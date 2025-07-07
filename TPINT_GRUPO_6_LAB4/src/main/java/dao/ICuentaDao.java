package dao;

import java.math.BigDecimal;
import java.util.List;

import entidad.Cuenta;
import entidad.CuentaPrestamoddlDTO;

public interface ICuentaDao {
	public int insertarCuenta(Cuenta cuenta);
    public boolean existeNumeroCuenta(String numeroCuenta);
    public boolean existeCBU(String cbu);
    public int contarCuentasPorDni(String dni);
	public List<Cuenta> readAll();
	public Cuenta read(String cbu);
	public boolean actualizarCuenta(Cuenta cuenta);
	public boolean eliminarCuenta(String cbu);
	public List<CuentaPrestamoddlDTO> CargarDDl(String dni);
	public BigDecimal obtenerSaldo(String cbu);
	public boolean actualizarSaldo(String cbu, BigDecimal nuevoSaldo);
	public String obtenerNombreTitular(String cbu);
	public String obtenerDniTitular(String cbu);
	public String obtenerCBUPorDNI(String dni);
	public List<Cuenta> obtenerCuentasPorDni(String dni);
	public Cuenta obtenerPorNumero(String numeroCuenta);
	boolean actualizarSaldoPorNumeroCuenta(String numeroCuenta, BigDecimal nuevoSaldo);
	


}
