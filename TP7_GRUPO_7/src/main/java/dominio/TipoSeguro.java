package dominio;

public class TipoSeguro {
	private int IdTipo;
	private String Descripcion;
	
	public TipoSeguro() {
		
	}
	
	public TipoSeguro(int idTipo, String descripcion) {
		IdTipo = idTipo;
		Descripcion = descripcion;
	}

	public int getIdTipo() {
		return IdTipo;
	}
	public void setIdTipo(int idTipo) {
		IdTipo = idTipo;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
	

}
