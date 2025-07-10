package negocio;

import java.util.List;
import entidad.Cuota;
import excepciones.SaldoInsuficienteExcepcion;


public interface ICuotaNegocio {
	
	public List<Cuota> listarHistorialPagos(int idPrestamo);
	public Cuota obtenerPorId(int idCuota);
	public boolean pagarCuota(int cuotaId, String numeroCuenta) throws SaldoInsuficienteExcepcion;
	

}
