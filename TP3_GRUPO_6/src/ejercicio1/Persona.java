package ejercicio1;

public class Persona {
	private String Nombre;
	private String Apellido;
	private String Dni;
	
	public Persona() {
		
	}
	
	public Persona(String nombre, String apellido, String dni) {
		this.Nombre = nombre;
		this.Apellido = apellido;
		this.Dni = dni;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}

	@Override
	public String toString() {
		return "Persona [Nombre=" + Nombre + ", Apellido=" + Apellido + ", Dni=" + Dni + "]";
	}
	
	
	
	

}
