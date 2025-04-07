package ejercicio2;

public class ProductoFresco extends Producto{
	private String FechaEnvasado;
	private String PaisOrigen;
	
	
	public ProductoFresco(String f, String nl, String fe, String po) {
		super(f, nl);
		FechaEnvasado = fe;
		PaisOrigen = po;
	}
}
