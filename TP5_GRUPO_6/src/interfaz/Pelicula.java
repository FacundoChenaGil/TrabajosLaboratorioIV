package interfaz;

public class Pelicula {
	private static int contadorID = 1; // Comienza desde 1

    private int id;
    private String titulo;
    private Genero genero;
    
	public Pelicula(int id, String titulo, Genero genero) {
		// Se asigna el ID y luego se incrementa
		this.id = contadorID++;
		this.titulo = titulo;
		this.genero = genero;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public Genero getGenero() {
		return genero;
	}
	
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", titulo=" + titulo + ", genero=" + genero.getNombre() + "]";
	}
    
    
}
