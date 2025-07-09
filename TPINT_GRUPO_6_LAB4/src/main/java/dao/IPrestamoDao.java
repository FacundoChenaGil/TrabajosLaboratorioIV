package dao;

import java.util.List;

import entidad.Prestamo;
import entidad.PrestamoBackup;

public interface IPrestamoDao {
	
	public boolean agregar(Prestamo prestamo);
	public boolean modificar(Prestamo prestamo);
	public boolean eliminar(int idPrestamo); // Baja lógica
	
	public boolean agregarPrestamo(PrestamoBackup prestamo);
	
	//OTROS MÉTODOS
	public Prestamo obtenerPrestamoPorId(int IdPrestamo);
	public List<Prestamo> obtenerPrestamosPorDni(String dni);
	

}
