package dao;

import java.util.List;

import entidad.Cuota;


public interface ICuotaDao {
	
	public List<Cuota> obtenerCuotasPagadasPorPrestamo(int idPrestamo);

}
