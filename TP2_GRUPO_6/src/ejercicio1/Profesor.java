package ejercicio1;

public class Profesor extends Empleado{
	private String Cargo;
	private int AntiguedadDocente;
	
	//constructores
	public Profesor() {
		super();
		this.Cargo = "Sin cargo";
		this.AntiguedadDocente = 0;
	}

	//getters y setters
	public String getCargo() {
		return Cargo;
	}

	public void setCargo(String cargo) {
		Cargo = cargo;
	}

	public int getAntiguedadDocente() {
		return AntiguedadDocente;
	}

	public void setAntiguedadDocente(int antiguedadDocente) {
		AntiguedadDocente = antiguedadDocente;
	}
	
	
}

