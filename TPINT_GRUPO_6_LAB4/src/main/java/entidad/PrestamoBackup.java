package entidad;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PrestamoBackup {
	
	private int IDPrestamo;
	private LocalDateTime FechaSolicitud;
	private BigDecimal ImportePedido;
	private BigDecimal ImporteAPagar;
	private int CantidadCuotas;
	private BigDecimal Importe_Cuota;
	private Cliente Cliente;
	private TipoEstadoPrestamo TipoEstadoPrestamo;
	private Cuenta CuentaAcreditada;

	public PrestamoBackup(int iDPrestamo, LocalDateTime fechaSolicitud, BigDecimal importePedido,
			BigDecimal importeAPagar, int cantidadCuotas, BigDecimal importe_Cuota,
			entidad.Cliente cliente, entidad.TipoEstadoPrestamo tipoEstadoPrestamo, Cuenta cuentaAcreditada) {
		IDPrestamo = iDPrestamo;
		FechaSolicitud = fechaSolicitud;
		ImportePedido = importePedido;
		ImporteAPagar = importeAPagar;
		CantidadCuotas = cantidadCuotas;
		Importe_Cuota = importe_Cuota;
		Cliente = cliente;
		TipoEstadoPrestamo = tipoEstadoPrestamo;
		CuentaAcreditada = cuentaAcreditada;
	}


	public int getIDPrestamo() {
		return IDPrestamo;
	}


	public void setIDPrestamo(int iDPrestamo) {
		IDPrestamo = iDPrestamo;
	}


	public LocalDateTime getFechaSolicitud() {
		return FechaSolicitud;
	}


	public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
		FechaSolicitud = fechaSolicitud;
	}


	public BigDecimal getImportePedido() {
		return ImportePedido;
	}


	public void setImportePedido(BigDecimal importePedido) {
		ImportePedido = importePedido;
	}


	public BigDecimal getImporteAPagar() {
		return ImporteAPagar;
	}


	public void setImporteAPagar(BigDecimal importeAPagar) {
		ImporteAPagar = importeAPagar;
	}

	public int getCantidadCuotas() {
		return CantidadCuotas;
	}


	public void setCantidadCuotas(int cantidadCuotas) {
		CantidadCuotas = cantidadCuotas;
	}


	public Cliente getCliente() {
		return Cliente;
	}


	public void setCliente(Cliente cliente) {
		Cliente = cliente;
	}


	public TipoEstadoPrestamo getTipoEstadoPrestamo() {
		return TipoEstadoPrestamo;
	}


	public void setTipoEstadoPrestamo(TipoEstadoPrestamo tipoEstadoPrestamo) {
		TipoEstadoPrestamo = tipoEstadoPrestamo;
	}


	public Cuenta getCuentaAcreditada() {
		return CuentaAcreditada;
	}


	public void setCuentaAcreditada(Cuenta cuentaAcreditada) {
		CuentaAcreditada = cuentaAcreditada;
	}


	public BigDecimal getImporte_Cuota() {
		return Importe_Cuota;
	}


	public void setImporte_Cuota(BigDecimal importe_Cuota) {
		Importe_Cuota = importe_Cuota;
	}
	
	

}
