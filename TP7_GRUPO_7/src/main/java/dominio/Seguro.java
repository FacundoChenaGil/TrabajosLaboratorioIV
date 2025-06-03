package dominio;

public class Seguro {
	private int id;
	private String descripcion;
	private int idTipo;
	private String descripcionTipo;
	private double costoContratacion;
	private double costoAsegurado;
	
	public Seguro() {
		
	}
	
	public Seguro(int id, String descripcion, int idTipo, String descripcionTipo, double costoContratacion, double costoAsegurado) {
		this.id = id;
		this.descripcion = descripcion;
		this.idTipo = idTipo;
		this.descripcionTipo = descripcionTipo;
		this.costoContratacion = costoContratacion;
		this.costoAsegurado = costoAsegurado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public String getDescripcionTipo() {
		return descripcionTipo;
	}

	public void setDescripcionTipo(String descripcionTipo) {
		this.descripcionTipo = descripcionTipo;
	}

	public double getCostoContratacion() {
		return costoContratacion;
	}

	public void setCostoContratacion(double costoContratacion) {
		this.costoContratacion = costoContratacion;
	}

	public double getCostoAsegurado() {
		return costoAsegurado;
	}

	public void setCostoAsegurado(double costoAsegurado) {
		this.costoAsegurado = costoAsegurado;
	}

	@Override
	public String toString() {
		return "Seguro [id=" + id + ", descripcion=" + descripcion + ", idTipo=" + idTipo + ", costoContratacion="
				+ costoContratacion + ", costoAsegurado=" + costoAsegurado + "]";
	}
		
}
