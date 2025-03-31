package EJERCICIO1;

public class Empleado {
	//atributos
	private final int Id;
	private String Nombre;
	private int Edad;
	public static int Cont = 1000;
	
	public Empleado() {
		this.Id = Cont++;
		this.Nombre = "Sin nombre";
		this.Edad = 99;
	}
	
	Empleado(String nombre, int edad) {
		this.Id = Cont++;
		this.Nombre = nombre;
		this.Edad = edad;
	}
	
	@Override
	public String toString() {
		return "Empleado [Id=" + Id + ", Nombre=" + Nombre + ", Edad=" + Edad + "]";
	}	

	//getters y setters
	public int getId() {
		return Id;
	}
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		this.Nombre = nombre;
	}
	public int getEdad() {
		return Edad;
	}
	public void setEdad(int edad) {
		this.Edad = edad;
	}
	public static int devuelveProximoID() {
		return Cont;
	}
}
