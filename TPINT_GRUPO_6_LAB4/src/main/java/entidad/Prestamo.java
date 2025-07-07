package entidad;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Prestamo {

	private int idPrestamo;
	private String dni;
	private LocalDate fechaCreacion;
	private float importeAPagar;
	private float montoPedido;
	private int plazoPago;
	private float importeCuota;
	private int cantCuotas;
	private int idTipoEstado;
	private String idCuenta;
	private List<Cuota> cuotasPendientes;
	private int primeraCuotaId;
	

	public Prestamo() {
	}

	

	public Prestamo(int idPrestamo, String dni, LocalDate fechaCreacion, float importeaPagar, float montoPedido,
			int plazoPago, float importeCuota, int cantCuotas, int idTipoEstado, String idCuenta,
			List<Cuota> cuotasPendientes, int primeraCuotaId) {
		super();
		this.idPrestamo = idPrestamo;
		this.dni = dni;
		this.fechaCreacion = fechaCreacion;
		this.importeAPagar = importeaPagar;
		this.montoPedido = montoPedido;
		this.plazoPago = plazoPago;
		this.importeCuota = importeCuota;
		this.cantCuotas = cantCuotas;
		this.idTipoEstado = idTipoEstado;
		this.idCuenta = idCuenta;
		this.cuotasPendientes = cuotasPendientes;
		this.primeraCuotaId = primeraCuotaId;
		
	}



	public int getIdPrestamo() {
		return idPrestamo;
	}



	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}



	public String getDNI() {
		return dni;
	}



	public void setDNI(String dni) {
		this.dni = dni;
	}



	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}



	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}



	public float getImporteAPagar() {
		return importeAPagar;
	}



	public void setImporteAPagar(float importeAPagar) {
		this.importeAPagar = importeAPagar;
	}



	public float getMontoPedido() {
		return montoPedido;
	}



	public void setMontoPedido(float montoPedido) {
		this.montoPedido = montoPedido;
	}



	public int getPlazoPago() {
		return plazoPago;
	}



	public void setPlazoPago(int plazoPago) {
		this.plazoPago = plazoPago;
	}



	public float getImporteCuota() {
		return importeCuota;
	}



	public void setImporteCuota(float importeCuota) {
		this.importeCuota = importeCuota;
	}



	public int getCantidadCuotas() {
		return cantCuotas;
	}



	public void setCantidadCuotas(int cantCuotas) {
		this.cantCuotas = cantCuotas;
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



	public List<Cuota> getCuotasPendientes() {
		return cuotasPendientes;
	}



	public void setCuotasPendientes(List<Cuota> cuotasPendientes) {
		this.cuotasPendientes = cuotasPendientes;
	}



	public int getPrimeraCuotaId() {
		return primeraCuotaId;
	}



	public void setPrimeraCuotaId(int primeraCuotaId) {
		this.primeraCuotaId = primeraCuotaId;
	}

	
	public float getMontoTotalAdeudado() {
	    float total = 0;
	    if (cuotasPendientes != null) {
	        for (Cuota c : cuotasPendientes) {
	            total += c.getImporte();
	        }
	    }
	    return total;
	}
	



	@Override
	public String toString() {
		return "IdPrestamo:" + idPrestamo + ", DNI: " + dni + ", FechaCreación: " + fechaCreacion
				+ ", Importe a Pagar=" + importeAPagar + ", MontoPedido=" + montoPedido + ", Plazo de Pago: " + plazoPago
				+ ", Importe Cuota:" + importeCuota + ", Cant.Cuotas:" + cantCuotas + ", IdTipoEstado:" + idTipoEstado
				+ ", IdCuenta:" + idCuenta + ", CuotasPendientes:" + cuotasPendientes + ", PrimeraCuotaId:"
				+ primeraCuotaId ;
	}



	
}
