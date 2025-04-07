package ejercicio2;

public class ProductoCongelado extends Producto{
	private String tempConge;

	public ProductoCongelado(String f, String nl, String tempConge) {
		super(f, nl);
		this.tempConge = tempConge;
	}

	public String getTempConge() {
		return tempConge;
	}

	public void setTempConge(String tempConge) {
		this.tempConge = tempConge;
	}
	
	
}
