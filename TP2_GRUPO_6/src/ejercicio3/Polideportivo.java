package ejercicio3;

public class Polideportivo implements IEdificio, IInstalacionDeportiva  {
	private String name;
	private double superficie;
	private int tipoDeInstalacion;
	
	public Polideportivo() {
		this.name = "sin nombre";
		this.superficie = 0;
		this.tipoDeInstalacion = 0;
	}
	
	public Polideportivo(String name, double superficie, int tipoDeInstalacion) {
		this.name = name;
		this.superficie = superficie;
		this.tipoDeInstalacion = tipoDeInstalacion;
	}

	public void setSuperficieEdificio(double superficieEdificio) {
		this.superficie = superficieEdificio;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTipoDeInstalacion(int tipoDeInstalacion) {
		this.tipoDeInstalacion = tipoDeInstalacion;
	}

	@Override
	public int getTipoDeInstalacion() {
		return this.tipoDeInstalacion;
	}

	@Override
	public double getSuperficieEdificio() {
		// TODO Auto-generated method stub
		return this.superficie;
	}

	@Override
	public String toString() {
		return "Polideportivo [name=" + name + ", superficie=" + getSuperficieEdificio() + 
				", tipoDeInstalacion=" + getTipoDeInstalacion() + "]";
	}
	
	

}
