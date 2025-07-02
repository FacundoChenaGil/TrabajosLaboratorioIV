package entidad;

public class TipoEstadoPrestamo {
	
	private int IDTipoEstado;
	private String Descripcion;
	
	public TipoEstadoPrestamo() {
		
	}
	
	public TipoEstadoPrestamo(int iDTipoEstado, String descripcion) {
		IDTipoEstado = iDTipoEstado;
		Descripcion = descripcion;
	}

	public int getIDTipoEstado() {
		return IDTipoEstado;
	}

	public void setIDTipoEstado(int iDTipoEstado) {
		IDTipoEstado = iDTipoEstado;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
	
	

}
