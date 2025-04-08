package ejercicio2;

public class ProductoRefrigerado extends Producto {
	private String CodOSA;

	public ProductoRefrigerado(String f, String nl, String codOSA) {
		super(f, nl);
		CodOSA = codOSA;
	}

	public String getCodOSA() {
		return CodOSA;
	}

	public void setCodOSA(String codOSA) {
		CodOSA = codOSA;
	}

	public void mostrar() {
		super.mostrar();
		System.out.println("Código OSA: " + CodOSA);
	}
}
