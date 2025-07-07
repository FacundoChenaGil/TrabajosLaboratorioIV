package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import entidad.Cuota;
import entidad.Prestamo;
import dao.IPrestamoDao;
import daoImpl.PrestamoDaoImpl;
import negocio.IPrestamoNegocio;
import daoImpl.CuotaDaoImpl;
import dao.ICuotaDao;

public class PrestamoNegocioImpl implements IPrestamoNegocio{
	
	private IPrestamoDao prestamoDao = new PrestamoDaoImpl();


    public List<Prestamo>obtenerPrestamosPorDni(String dni){
        return prestamoDao.obtenerPrestamosPorDni(dni);
    }
        
 
     private ICuotaDao cuotaDao = new CuotaDaoImpl();

        public List<Prestamo> obtenerPrestamosConCuotasPendientesPorDni(String dni) {
            List<Prestamo> prestamos = prestamoDao.obtenerPrestamosPorDni(dni);
            List<Prestamo> prestamosConPendientes = new ArrayList<>();

            for (Prestamo p : prestamos) {
                List<Cuota> cuotasPendientes = cuotaDao.obtenerCuotasPendientesPorPrestamo(p.getIdPrestamo());

                if (!cuotasPendientes.isEmpty()) {
                    p.setCuotasPendientes(cuotasPendientes);
                    p.setPrimeraCuotaId(cuotasPendientes.get(0).getIdCuota()); // la más próxima a pagar
                    prestamosConPendientes.add(p);
                }
            }

            return prestamosConPendientes;
        }
   }       


