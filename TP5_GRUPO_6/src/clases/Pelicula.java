package clases;

public class Pelicula implements Comparable<Pelicula> {
	private static int contadorID = 1; // Comienza desde 1

    private int id;
    private String titulo;
    private Genero genero;
    
	public Pelicula(String titulo, Genero genero) {
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
	
	public static int getContadorID() {
		return contadorID;
	}

	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", titulo=" + titulo + ", genero=" + genero.getNombre() + "]";
	}

	@Override
	public int compareTo(Pelicula o) {
		// TODO Auto-generated method stub
		return this.titulo.compareToIgnoreCase(o.getTitulo());
	}
    
    
}
