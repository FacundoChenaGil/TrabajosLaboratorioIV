package entidad;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Cuenta {
	private int CBU;
	private String NumeroCuenta;
	private LocalDateTime FechaCreacion;
	private BigDecimal Saldo;
	private boolean Activa;
	private TiposDeCuentas TipoCuenta;
	private Cliente Cliente;
	
	public Cuenta() {
		
	}
	
	public Cuenta(int cBU, String numeroCuenta, LocalDateTime fechaCreacion, BigDecimal saldo, boolean activa,
			TiposDeCuentas tipoCuenta, entidad.Cliente cliente) {
		CBU = cBU;
		NumeroCuenta = numeroCuenta;
		FechaCreacion = fechaCreacion;
		Saldo = saldo;
		Activa = activa;
		TipoCuenta = tipoCuenta;
		Cliente = cliente;
	}

	public int getCBU() {
		return CBU;
	}

	public void setCBU(int cBU) {
		CBU = cBU;
	}

	public String getNumeroCuenta() {
		return NumeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		NumeroCuenta = numeroCuenta;
	}

	public LocalDateTime getFechaCreacion() {
		return FechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		FechaCreacion = fechaCreacion;
	}

	public BigDecimal getSaldo() {
		return Saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		Saldo = saldo;
	}

	public boolean isActiva() {
		return Activa;
	}

	public void setActiva(boolean activa) {
		Activa = activa;
	}

	public TiposDeCuentas getTipoCuenta() {
		return TipoCuenta;
	}

	public void setTipoCuenta(TiposDeCuentas tipoCuenta) {
		TipoCuenta = tipoCuenta;
	}

	public Cliente getCliente() {
		return Cliente;
	}

	public void setCliente(Cliente cliente) {
		Cliente = cliente;
	}
	
	
	
	

}
