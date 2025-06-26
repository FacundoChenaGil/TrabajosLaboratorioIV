package entidad;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Cuenta {
	private String CBU;
	private String NumeroCuenta;
	private LocalDate FechaCreacion;
	private BigDecimal Saldo;
	private boolean Activa;
	private TiposDeCuentas TipoCuenta;
	private Cliente Cliente;
	
	public Cuenta() {
		
	}
	
	public Cuenta(String cBU, String numeroCuenta, LocalDate fechaCreacion, BigDecimal saldo, boolean activa,
			TiposDeCuentas tipoCuenta, entidad.Cliente cliente) {
		CBU = cBU;
		NumeroCuenta = numeroCuenta;
		FechaCreacion = fechaCreacion;
		Saldo = saldo;
		Activa = activa;
		TipoCuenta = tipoCuenta;
		Cliente = cliente;
	}

	public String getCBU() {
		return CBU;
	}

	public void setCBU(String cBU) {
		CBU = cBU;
	}

	public String getNumeroCuenta() {
		return NumeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		NumeroCuenta = numeroCuenta;
	}

	public LocalDate getFechaCreacion() {
		return FechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
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
