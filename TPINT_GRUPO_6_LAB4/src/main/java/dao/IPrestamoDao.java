package dao;

import java.util.List;

import entidad.Prestamo;

public interface IPrestamoDao {
	public List<Prestamo> leerPrestamosPendientes();
	public boolean aprobarPrestamo(int idPrestamo);
	public boolean rechazarPrestamo(int idPrestamo);
	
	//OTROS MÉTODOS
	public Prestamo obtenerPrestamoPorId(int idPrestamo);
	public List<Prestamo> obtenerPrestamosConCuotasPendientesPorDni(String dni);
	public List<Prestamo> obtenerPrestamosFinalizadosPorDni(String dni);
	public List<Prestamo> obtenerPrestamosPorDni(String dni);
	public List<Prestamo> obtenerPrestamosPorDniYEstado(String dni, int idEstado);
	boolean solicitarPrestamo(Prestamo prestamo);
	boolean agregarPrestamo(Prestamo prestamo);
	

}
