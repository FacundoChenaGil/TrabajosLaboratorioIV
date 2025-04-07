package ejercicio2;

public class Producto {
	//atributos
	private String FechaCaducidad;
	private String NroLote;
	
	//constructor por parámetro
	public Producto(String f, String nl) {
		this.FechaCaducidad = f;
		this.NroLote = nl;
	}

	//getters and setters
	public String getFechaCaducidad() {
		return FechaCaducidad;
	}
	public void setFechaCaducidad(String fechaCaducidad) {
		FechaCaducidad = fechaCaducidad;
	}
	public String getNroLote() {
		return NroLote;
	}
	public void setNroLote(String nroLote) {
		NroLote = nroLote;
	}
}
