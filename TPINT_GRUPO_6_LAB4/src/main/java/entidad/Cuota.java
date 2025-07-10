package entidad;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cuota {

	private int idCuota;
	private Prestamo prestamo;
	private int numeroCuota;
	private BigDecimal importe;
	private LocalDate fechaVencimiento;
	private LocalDate fechaPago;
	
	public Cuota() {

	}

	public Cuota(int idCuota, Prestamo prestamo, int numeroCuota, BigDecimal importe, LocalDate fechaVencimiento,
			LocalDate fechaPago) {
		super();
		this.idCuota = idCuota;
		this.prestamo = prestamo;
		this.numeroCuota = numeroCuota;
		this.importe = importe;
		this.fechaVencimiento = fechaVencimiento;
		this.fechaPago = fechaPago;
	}

	// getters y setters

	public int getIdCuota() {
		return idCuota;
	}


	public void setIdCuota(int idCuota) {
		this.idCuota = idCuota;
	}


	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}


	public int getNumeroCuota() {
		return numeroCuota;
	}


	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}


	public BigDecimal getImporte() {
		return importe;
	}


	public void setImporte(BigDecimal importe) {
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

	@Override
	public String toString() {
	    return "Cuota:" + idCuota + ", ID Prestamo:" + prestamo.getIDPrestamo() + ", Número Cuota:" + numeroCuota
	            + ", Importe=" + importe + ", Fecha Vencimiento:" + fechaVencimiento + ", Fecha Pago:" + fechaPago
	            + ", Numero Cuenta:";
	}

}
