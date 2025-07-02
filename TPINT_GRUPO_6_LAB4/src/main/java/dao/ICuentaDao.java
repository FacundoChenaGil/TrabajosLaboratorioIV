package dao;

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
}
