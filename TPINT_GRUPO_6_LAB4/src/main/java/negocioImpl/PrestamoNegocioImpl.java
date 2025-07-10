package negocioImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Cuota;
import entidad.Prestamo;
import entidad.TipoEstadoPrestamo;
import dao.IPrestamoDao;
import daoImpl.PrestamoDaoImpl;
import negocio.IPrestamoNegocio;
import daoImpl.CuotaDaoImpl;
import dao.ICuotaDao;
import dao.ICuentaDao;
import daoImpl.CuentaDaoImpl;
import dao.IClienteDao;
import daoImpl.ClienteDaoImpl;
import dao.ITipoEstadoPrestamoDao;
import daoImpl.TipoEstadoPrestamoDaoImpl;

public class PrestamoNegocioImpl implements IPrestamoNegocio{
	
	private IPrestamoDao prestamoDao = new PrestamoDaoImpl();
	private ICuotaDao cuotaDao = new CuotaDaoImpl();
	private ICuentaDao cuentaDao = new CuentaDaoImpl();
	private IClienteDao clienteDao = new ClienteDaoImpl();
	private ITipoEstadoPrestamoDao tsDao = new TipoEstadoPrestamoDaoImpl();

	

    public PrestamoNegocioImpl(IPrestamoDao prestamoDao, ICuotaDao cuotaDao, ICuentaDao cuentaDao,
			IClienteDao clienteDao, ITipoEstadoPrestamoDao tsDao) {
		super();
		this.prestamoDao = prestamoDao;
		this.cuotaDao = cuotaDao;
		this.cuentaDao = cuentaDao;
		this.clienteDao = clienteDao;
		this.tsDao = tsDao;
	}
    
	public PrestamoNegocioImpl() {
		
	}

	public List<Prestamo>obtenerPrestamosPorDni(String dni){
        return prestamoDao.obtenerPrestamosPorDni(dni);
    }
    
    public BigDecimal calcularImporteAPagar(BigDecimal importe) {
    	BigDecimal porcentaje = new BigDecimal("1.20");
        BigDecimal importeAPagar = importe.multiply(porcentaje);
        return importeAPagar;
    }
    
    public BigDecimal calcularImporteCuota(BigDecimal cantCuotas, BigDecimal importe) {
    	BigDecimal importeCuota = importe.divide(cantCuotas, 2, RoundingMode.HALF_UP);
    	return importeCuota;
    }
    
    public List<Prestamo> obtenerPrestamosConCuotasPendientesPorDni(String dni) {
    	List<Prestamo> prestamos = prestamoDao.obtenerPrestamosPorDni(dni);
    	List<Prestamo> prestamosConPendientes = new ArrayList<>();
    	
    	for (Prestamo p : prestamos) {
    		List<Cuota> cuotasPendientes = cuotaDao.obtenerCuotasPendientesPorPrestamo(p.getIDPrestamo());
    		
    		if (!cuotasPendientes.isEmpty()) {
    			p.setCuotasPendientes(cuotasPendientes);
    			p.setPrimeraCuotaId(cuotasPendientes.get(0).getIdCuota()); // la más próxima a pagar
    			prestamosConPendientes.add(p);
    		}
    	}
    	
    	return prestamosConPendientes;
    }

	@Override
	public List<Prestamo> obtenerPrestamosPorDniYEstado(String dni, int idEstado) {
		return prestamoDao.obtenerPrestamosPorDniYEstado(dni, idEstado);
	}

	@Override
	public boolean agregarPrestamo(Prestamo prestamo) {
		boolean creado = prestamoDao.agregarPrestamo(prestamo);
		return creado;
	}

	@Override
	public List<Prestamo> leerPrestamosPendientes() {
		return prestamoDao.leerPrestamosPendientes();
	}

	@Override
	public boolean aprobarPrestamo(int idPrestamo) {
		return prestamoDao.aprobarPrestamo(idPrestamo);
	}

	@Override
	public boolean rechazarPrestamo(int idPrestamo) {
		return prestamoDao.rechazarPrestamo(idPrestamo);
	}

   }       


