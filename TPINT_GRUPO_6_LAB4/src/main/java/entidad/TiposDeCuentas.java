package entidad;

public class TiposDeCuentas {
	private int ID;
	private String Descripcion;
	
	public TiposDeCuentas() {
		
	}
	
	public TiposDeCuentas(int iD, String descripcion) {
		ID = iD;
		Descripcion = descripcion;
	}
	
	//GETS Y SETS
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	@Override
	public String toString() {
		return ID + " - " + Descripcion;
	}

	
	
	
}
