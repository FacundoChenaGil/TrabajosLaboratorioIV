package ejercicio1;

import java.util.Objects;

public class Persona implements Comparable<Persona> {
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
	

	@Override
	public int hashCode() {
		return Objects.hash(Apellido, Dni, Nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(Apellido, other.Apellido) && Objects.equals(Dni, other.Dni)
				&& Objects.equals(Nombre, other.Nombre);
	}
	
	public static void VerificarDniInvalido(String dni) throws DniInvalido {
	    if (dni.length() < 7 || dni.length() > 8) {
	        throw new DniInvalido("DNI inválido: debe tener entre 7 y 8 dígitos.");
	    }
	    for (int i = 0; i < dni.length(); i++) {
	        if (!Character.isDigit(dni.charAt(i))) {
	            throw new DniInvalido("DNI inválido: solo se permiten números.");
	        }
	    }
	}
	

	@Override
	public int compareTo(Persona o) {
		return this.Apellido.compareTo(o.Apellido);
	}
	
	
	

}
