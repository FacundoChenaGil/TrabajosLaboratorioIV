package entidad;

import java.math.BigDecimal;

public class PromedioPorTipoCuenta {
	
	private TiposDeCuentas tipoCuenta;
    private BigDecimal promedio;
    
    public PromedioPorTipoCuenta() {
    	
    };
    
    
    
	public PromedioPorTipoCuenta(TiposDeCuentas tipoCuenta, BigDecimal promedio) {
		super();
		this.tipoCuenta = tipoCuenta;
		this.promedio = promedio;
	}



	public TiposDeCuentas getTipoCuenta() {
		return tipoCuenta;
	}



	public void setTipoCuenta(TiposDeCuentas tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}



	public BigDecimal getPromedio() {
		return promedio;
	}



	public void setPromedio(BigDecimal promedio) {
		this.promedio = promedio;
	}



	@Override
	public String toString() {
		return "Tipo de Cuenta: " + tipoCuenta + ", Promedio: " + promedio;
	}
	
	
	
	
	

}
