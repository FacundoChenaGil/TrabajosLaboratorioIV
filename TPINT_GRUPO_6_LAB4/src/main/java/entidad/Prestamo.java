package entidad;

import java.time.LocalDateTime;

public class Prestamo {
	
	private int id_prestamo;
	private String dni;
	private LocalDateTime fecha_solicitud;
	private float importe_pedido;
	private float importe_pagar;
	private int plazo_pago;
	private int cant_cuotas;
	private float importe_cuota;
	private int id_tipo_estado;
	private String id_cuenta_acreditacion;
	
	
	// Constructores
	public Prestamo () {
		
	}
		
	public Prestamo(String dni, LocalDateTime fecha_solicitud, float importe_pedido, float importe_pagar,
			int plazo_pago, int cant_cuotas, float importe_cuota, int id_tipo_estado, String id_cuenta_acreditacion) {

		this.dni = dni;
		this.fecha_solicitud = fecha_solicitud;
		this.importe_pedido = importe_pedido;
		this.importe_pagar = importe_pagar;
		this.plazo_pago = plazo_pago;
		this.cant_cuotas = cant_cuotas;
		this.importe_cuota = importe_cuota;
		this.id_tipo_estado = id_tipo_estado;
		this.id_cuenta_acreditacion = id_cuenta_acreditacion;
		
		//Getters y Setters
		
	
		
	}

	public int getId_prestamo() {
		return id_prestamo;
	}

	/*public void setId_prestamo(int id_prestamo) {
		this.id_prestamo = id_prestamo;*/

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public LocalDateTime getFecha_solicitud() {
		return fecha_solicitud;
	}

	public void setFecha_solicitud(LocalDateTime fecha_solicitud) {
		this.fecha_solicitud = fecha_solicitud;
	}

	public float getImporte_pedido() {
		return importe_pedido;
	}

	public void setImporte_pedido(float importe_pedido) {
		this.importe_pedido = importe_pedido;
	}

	public float getImporte_pagar() {
		return importe_pagar;
	}

	public void setImporte_pagar(float importe_pagar) {
		this.importe_pagar = importe_pagar;
	}

	public int getPlazo_pago() {
		return plazo_pago;
	}

	public void setPlazo_pago(int plazo_pago) {
		this.plazo_pago = plazo_pago;
	}

	public int getCant_cuotas() {
		return cant_cuotas;
	}

	public void setCant_cuotas(int cant_cuotas) {
		this.cant_cuotas = cant_cuotas;
	}

	public float getImporte_cuota() {
		return importe_cuota;
	}

	public void setImporte_cuota(float importe_cuota) {
		this.importe_cuota = importe_cuota;
	}

	public int getId_tipo_estado() {
		return id_tipo_estado;
	}

	public void setId_tipo_estado(int id_tipo_estado) {
		this.id_tipo_estado = id_tipo_estado;
	}

	public String getId_cuenta_acreditacion() {
		return id_cuenta_acreditacion;
	}

	public void setId_cuenta_acreditacion(String id_cuenta_acreditacion) {
		this.id_cuenta_acreditacion = id_cuenta_acreditacion;
	}

	@Override
	public String toString() {
		return "ID: " + id_prestamo + ", DNI:" + dni + ", Fecha se solicitud: " + fecha_solicitud
				+ ", Importe pedido: " + importe_pedido + ", Importe a pagar: " + importe_pagar + ", Plazo de pago: "
				+ plazo_pago + ", Cantidad de cuotas: " + cant_cuotas + ", Importe de cuota:" + importe_cuota + ", ID tipo de Estado="
				+ id_tipo_estado + ", ID cuenta de acreditación: " + id_cuenta_acreditacion ;
	}
	
	
	
	
}


