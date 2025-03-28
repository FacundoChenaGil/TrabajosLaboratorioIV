package EJERCICIO1;

public class Empleado {
	//atributos
	private final int id;
	private String nombre;
	private int edad;
	public static int cont = 1000;
	
	public Empleado() {
		id = cont ++;
		
	}

	
	//getters y setters
	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
}
