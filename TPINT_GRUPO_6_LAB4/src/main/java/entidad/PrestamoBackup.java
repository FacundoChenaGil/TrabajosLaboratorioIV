package entidad;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PrestamoBackup {
	
	private int IDPrestamo;
	private LocalDateTime FechaSolicitud;
	private BigDecimal ImportePedido;
	private BigDecimal ImporteAPagar;
	private int CantidadCuotas;
	private BigDecimal ImporteCuota;
	private Cliente Cliente;
	private TipoEstadoPrestamo TipoEstadoPrestamo;
	private Cuenta CuentaAcreditada;

	public PrestamoBackup(int iDPrestamo, LocalDateTime fechaSolicitud, BigDecimal importePedido,
			BigDecimal importeAPagar, int cantidadCuotas, BigDecimal importeCuota,
			entidad.Cliente cliente, entidad.TipoEstadoPrestamo tipoEstadoPrestamo, Cuenta cuentaAcreditada) {
		IDPrestamo = iDPrestamo;
		FechaSolicitud = fechaSolicitud;
		ImportePedido = importePedido;
		ImporteAPagar = importeAPagar;
		CantidadCuotas = cantidadCuotas;
		ImporteCuota = importeCuota;
		Cliente = cliente;
		TipoEstadoPrestamo = tipoEstadoPrestamo;
		CuentaAcreditada = cuentaAcreditada;
	}


	public PrestamoBackup() {
		// TODO Auto-generated constructor stub
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


	public BigDecimal getImporteCuota() {
		return ImporteCuota;
	}


	public void setImporte_Cuota(BigDecimal importeCuota) {
		ImporteCuota = importeCuota;
	}
	
	

}
