package entidad;

import java.time.LocalDate;

public class Cuota {

	private int ID_Cuota;
	private int ID_Prestamo;
	private int Numero_Cuota;
	private float Importe;
	private LocalDate Fecha_Vencimiento;
	private LocalDate Fecha_Pago;

	// Constructores
	public Cuota() {

	}

	public Cuota(int iD_Prestamo, int numero_Cuota, float importe, LocalDate fecha_Vencimiento, LocalDate fecha_Pago) {
		ID_Prestamo = iD_Prestamo;
		Numero_Cuota = numero_Cuota;
		Importe = importe;
		Fecha_Vencimiento = fecha_Vencimiento;
		Fecha_Pago = fecha_Pago;
	}

	// getters y setters
	public int getID_Cuota() {
		return ID_Cuota;
	}

	public void setID_Cuota(int iD_Cuota) {
		ID_Cuota = iD_Cuota;
	}

	public int getID_Prestamo() {
		return ID_Prestamo;
	}

	public void setID_Prestamo(int iD_Prestamo) {
		ID_Prestamo = iD_Prestamo;
	}

	public int getNumero_Cuota() {
		return Numero_Cuota;
	}

	public void setNumero_Cuota(int numero_Cuota) {
		Numero_Cuota = numero_Cuota;
	}

	public float getImporte() {
		return Importe;
	}

	public void setImporte(float importe) {
		Importe = importe;
	}

	public LocalDate getFecha_Vencimiento() {
		return Fecha_Vencimiento;
	}

	public void setFecha_Vencimiento(LocalDate fecha_Vencimiento) {
		Fecha_Vencimiento = fecha_Vencimiento;
	}

	public LocalDate getFecha_Pago() {
		return Fecha_Pago;
	}

	public void setFecha_Pago(LocalDate fecha_Pago) {
		Fecha_Pago = fecha_Pago;
	}

	// Método toString

	@Override
	public String toString() {
		return "ID_Cuota: " + ID_Cuota + ", ID_Prestamo:" + ID_Prestamo + ", Numero_Cuota: " + Numero_Cuota
				+ ", Importe: " + Importe + ", Fecha_Vencimiento: " + Fecha_Vencimiento + ", Fecha_Pago: " + Fecha_Pago;
	}

}
