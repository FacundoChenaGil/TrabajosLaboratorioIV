package negocioImpl;

import java.math.BigDecimal;
import java.util.List;

import dao.ICuentaDao;
import dao.ICuotaDao;
import daoImpl.CuentaDaoImpl;
import daoImpl.CuotaDaoImpl;
import entidad.Cuenta;
import entidad.Cuota;
import excepciones.SaldoInsuficienteExcepcion;
import negocio.ICuotaNegocio;

public class CuotaNegocioImpl implements ICuotaNegocio {

    private ICuotaDao cuotaDao = new CuotaDaoImpl();
    private ICuentaDao cuentaDao = new CuentaDaoImpl(); 

    public List<Cuota> listarHistorialPagos(int idPrestamo) {
        return cuotaDao.obtenerCuotasPagadasPorPrestamo(idPrestamo);
    }

    @Override
    public boolean pagarCuota(int cuotaId, String numeroCuenta) throws SaldoInsuficienteExcepcion {
        Cuota cuota = cuotaDao.obtenerPorId(cuotaId);
        Cuenta cuenta = cuentaDao.obtenerPorNumero(numeroCuenta);

        if (cuota == null || cuenta == null) {
            return false; 
        }

        if (cuenta.getSaldo().compareTo(cuota.getImporte()) < 0) {
            throw new SaldoInsuficienteExcepcion("El saldo de la cuenta es insuficiente para pagar la cuota.");
        }

        return cuotaDao.pagarCuota(cuotaId, numeroCuenta);
    }

    public Cuota obtenerPorId(int idCuota) {
        return cuotaDao.obtenerPorId(idCuota);
    }

}
