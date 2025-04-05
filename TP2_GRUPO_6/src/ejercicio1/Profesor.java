package ejercicio1;

public class Profesor extends Empleado implements Comparable<Profesor>{
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
	
	
	@Override
	public int compareTo(Profesor profe) {
		if (this.getId() < profe.getId()) {
	        return -1; //va antes
	    } else if (this.getId() > profe.getId()) {
	        return 1; //va despues
	    } else {
	        return 0; //el id es el mismo, no lo guarda
	    }
	}
	
}

