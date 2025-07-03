package entidad;

import java.time.LocalDate;

public class Cuota {

	private int idCuota;
	private int idPrestamo;
	private int numeroCuota;
	private float importe;
	private LocalDate fechaVencimiento;
	private LocalDate fechaPago;
	private String numeroCuenta;
	
	// Constructores
	public Cuota() {

	}

	
	public Cuota(int idCuota, int idPrestamo, int numeroCuota, float importe, LocalDate fechaVencimiento,
			LocalDate fechaPago, String numeroCuenta) {
		super();
		this.idCuota = idCuota;
		this.idPrestamo = idPrestamo;
		this.numeroCuota = numeroCuota;
		this.importe = importe;
		this.fechaVencimiento = fechaVencimiento;
		this.fechaPago = fechaPago;
		this.numeroCuenta = numeroCuenta;
	}


	public int getIdCuota() {
		return idCuota;
	}


	public void setIdCuota(int idCuota) {
		this.idCuota = idCuota;
	}


	public int getIdPrestamo() {
		return idPrestamo;
	}

	// getters y setters

	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}


	public int getNumeroCuota() {
		return numeroCuota;
	}


	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}


	public float getImporte() {
		return importe;
	}


	public void setImporte(float importe) {
		this.importe = importe;
	}


	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}


	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}


	public LocalDate getFechaPago() {
		return fechaPago;
	}


	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}


	public String getNumeroCuenta() {
		return numeroCuenta;
	}


	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	@Override
	public String toString() {
	    return "Cuota:" + idCuota + ", ID Prestamo:" + idPrestamo + ", Número Cuota:" + numeroCuota
	            + ", Importe=" + importe + ", Fecha Vencimiento:" + fechaVencimiento + ", Fecha Pago:" + fechaPago
	            + ", Numero Cuenta:" + numeroCuenta;
	}






	
}
