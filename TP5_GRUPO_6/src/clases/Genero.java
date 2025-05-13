package clases;

public class Genero {
	private String nombre;

	public Genero(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
}
