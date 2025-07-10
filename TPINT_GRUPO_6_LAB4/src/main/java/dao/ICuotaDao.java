package dao;

import java.util.List;


import entidad.Cuota;
import entidad.Prestamo;


public interface ICuotaDao {
	public List<Cuota> obtenerCuotasPagadasPorPrestamo(int idPrestamo);
	public Cuota obtenerPorId(int idCuota);
	public List<Cuota> obtenerCuotasPendientesPorPrestamo(Prestamo prestamo);
	public boolean pagarCuota(int cuotaId, String numeroCuenta);
	public boolean agregarCuota(Cuota cuota);
}
