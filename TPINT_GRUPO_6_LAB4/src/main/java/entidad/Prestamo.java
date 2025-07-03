package entidad;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Prestamo{
	
	private int idPrestamo;
	private String DNI;
	private LocalDate fecha_creacion;
	private float importe_a_pagar;
	private float monto_pedido;
	private int plazo_de_pago;
	private float importe_cuota;
	private int cant_cuotas;
	private int idTipoEstado;
	private String idCuenta;
	
	public Prestamo () {
	}
	
	
	
	
	public Prestamo(int idPrestamo, String DNI,LocalDate fecha_creacion,float importe_a_pagar,
			float monto_pedido, int plazo_de_pago, float importe_cuota, int cant_cuotas, int idTipoEstado,
			String idCuenta) {
		
		this.idPrestamo = idPrestamo;
		this.DNI = DNI;
		this.fecha_creacion = fecha_creacion;
		this.importe_a_pagar = importe_a_pagar;
		this.monto_pedido = monto_pedido;
		this.plazo_de_pago = plazo_de_pago;
		this.importe_cuota = importe_cuota;
		this.cant_cuotas = cant_cuotas;
		this.idTipoEstado = idTipoEstado;
		this.idCuenta = idCuenta;
	}


	public String getDNI() {
		return DNI;
	}




	public void setDNI(String dNI) {
		DNI = dNI;
	}




	public int getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public LocalDate getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(LocalDate fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	
	public float getImporte_a_pagar() {
		return importe_a_pagar;
	}
	public void setImporte_a_pagar(float importe_a_pagar) {
		this.importe_a_pagar = importe_a_pagar;
	}
	public float getMonto_pedido() {
		return monto_pedido;
	}
	public void setMonto_pedido(float monto_pedido) {
		this.monto_pedido = monto_pedido;
	}
	public int getPlazo_de_pago() {
		return plazo_de_pago;
	}
	public void setPlazo_de_pago(int plazo_de_pago) {
		this.plazo_de_pago = plazo_de_pago;
	}
	public float getImporte_cuota() {
		return importe_cuota;
	}
	public void setImporte_cuota(float importe_cuota) {
		this.importe_cuota = importe_cuota;
	}
	public int getCant_cuotas() {
		return cant_cuotas;
	}
	public void setCant_cuotas(int cant_cuotas) {
		this.cant_cuotas = cant_cuotas;
	}
	public int getIdTipoEstado() {
		return idTipoEstado;
	}
	public void setIdTipoEstado(int idTipoEstado) {
		this.idTipoEstado = idTipoEstado;
	}
	public String getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}
	
	
	
	
	@Override
	public String toString() {
		return "IdPrestamo: " + idPrestamo + ", DNI: " + DNI + "Fecha_creacion:" + fecha_creacion +  ", Importe_a_pagar: " + importe_a_pagar + ", Monto_pedido:" + monto_pedido + ", Plazo_de_pago:"
				+ plazo_de_pago + ", importe_cuota=" + importe_cuota + ", cant_cuotas=" + cant_cuotas
				+ ", IdTipoEstado:" + idTipoEstado + ", IdCuenta:" + idCuenta ;
	}



	

	

}
