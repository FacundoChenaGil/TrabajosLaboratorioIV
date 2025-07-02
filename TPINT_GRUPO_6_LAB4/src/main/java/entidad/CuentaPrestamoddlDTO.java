package entidad;

public class CuentaPrestamoddlDTO {

	private String CBU;
	private String NumeroCuenta;
	
	public CuentaPrestamoddlDTO(String cBU, String numeroCuenta) {
		CBU = cBU;
		NumeroCuenta = numeroCuenta;
	}
	
	public CuentaPrestamoddlDTO() {
		
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
}
