package dao;

import java.util.List;

import entidad.Cuota;


public interface ICuotaDao {
	
	public List<Cuota> obtenerCuotasPagadasPorPrestamo(int idPrestamo);
	public Cuota obtenerPorId(int idCuota);
	boolean marcarComoPagada(int idCuota, String numeroCuenta);
	public List<Cuota> obtenerCuotasPendientesPorPrestamo(int idPrestamo);
	
}
