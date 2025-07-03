package negocioImpl;

import java.util.List;

import entidad.Prestamo;
import dao.IPrestamoDao;
import daoImpl.PrestamoDaoImpl;
import negocio.IPrestamoNegocio;

public class PrestamoNegocioImpl implements IPrestamoNegocio{
	
	private IPrestamoDao prestamoDao = new PrestamoDaoImpl();


    public List<Prestamo>obtenerPrestamosPorDni(String dni){
        return prestamoDao.obtenerPrestamosPorDni(dni);
        
             
    }

}
