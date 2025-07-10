package entidad;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Prestamo {
	
	private int IDPrestamo;
	private LocalDateTime FechaSolicitud;
	private BigDecimal ImportePedido;
	private BigDecimal ImporteAPagar;
	private int CantidadCuotas;
	private BigDecimal ImporteCuota;
	private Cliente Cliente;
	private TipoEstadoPrestamo TipoEstadoPrestamo;
	private Cuenta CuentaAcreditada;
	private List<Cuota> cuotasPendientes;
	private int primeraCuotaId;

	public Prestamo(int iDPrestamo, LocalDateTime fechaSolicitud, BigDecimal importePedido,
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


	public Prestamo() {
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

	public Date getFechaSolicitudAsDate() {
	    if (this.FechaSolicitud == null) {
	        return null;
	    }
	    return Date.from(this.FechaSolicitud.atZone(ZoneId.systemDefault()).toInstant());
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


	public BigDecimal getImporteCuota() {
		return ImporteCuota;
	}


	public void setImporteCuota(BigDecimal importeCuota) {
		ImporteCuota = importeCuota;
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

	public BigDecimal getMontoTotalAdeudado() {
	    if (cuotasPendientes == null || cuotasPendientes.isEmpty()) {
	        return BigDecimal.ZERO;
	    }
	    return cuotasPendientes.stream()
	                           .map(Cuota::getImporte)
	                           .reduce(BigDecimal.ZERO, BigDecimal::add);
	}


	@Override
	public String toString() {
		return "Prestamo [IDPrestamo=" + IDPrestamo + ", FechaSolicitud=" + FechaSolicitud + ", ImportePedido="
				+ ImportePedido + ", ImporteAPagar=" + ImporteAPagar + ", CantidadCuotas=" + CantidadCuotas
				+ ", ImporteCuota=" + ImporteCuota + ", Cliente=" + Cliente + ", TipoEstadoPrestamo="
				+ TipoEstadoPrestamo + ", CuentaAcreditada=" + CuentaAcreditada + "]";
	}

}
