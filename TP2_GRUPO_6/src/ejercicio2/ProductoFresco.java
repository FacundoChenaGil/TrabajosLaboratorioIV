package ejercicio2;

public class ProductoFresco extends Producto{
	private String FechaEnvasado;
	private String PaisOrigen;
	
	public ProductoFresco(String f, String nl, String fe, String po) {
		super(f, nl);
		this.FechaEnvasado = fe;
		this.PaisOrigen = po;
	}

	public String getFechaEnvasado() {
		return FechaEnvasado;
	}

	public void setFechaEnvasado(String fechaEnvasado) {
		FechaEnvasado = fechaEnvasado;
	}

	public String getPaisOrigen() {
		return PaisOrigen;
	}

	public void setPaisOrigen(String paisOrigen) {
		PaisOrigen = paisOrigen;
	}
	
	
}
