package ejercicio3;

public class EdificioOficinas {
	private int cantidadOficinas;
    private double superficie;

    // Constructor por defecto
    public EdificioOficinas() {
        this.cantidadOficinas = 0;
        this.superficie = 0.0;
    }

    // Constructor con parámetros
    public EdificioOficinas(int cantidadOficinas, double superficie) {
    	super();
        this.cantidadOficinas = cantidadOficinas;
        this.superficie = superficie;
    }

    // Getters y Setters
    public int getCantidadOficinas() {
        return cantidadOficinas;
    }

    public void setCantidadOficinas(int cantidadOficinas) {
        this.cantidadOficinas = cantidadOficinas;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }
    // toString para mostrar los datos
    @Override
    public String toString() {
        return "Edificio de Oficinas: cantidad de oficinas = " + cantidadOficinas + ", superficie = " + superficie;
    }
}



