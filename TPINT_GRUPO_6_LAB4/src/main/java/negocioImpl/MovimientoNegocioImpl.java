package negocioImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import dao.IMovimientoDao;
import daoImpl.MovimientoDaoImpl;
import entidad.Movimiento;
import negocio.IMovimientoNegocio;

public class MovimientoNegocioImpl implements IMovimientoNegocio {

    private IMovimientoDao movimientoDao = new MovimientoDaoImpl();
    
    @Override
    public List<Movimiento> listarPorCBU(String cbu) {
        return movimientoDao.obtenerPorCBU(cbu);
    }

    @Override
    public List<Movimiento> filtrarMovimientos(String cbu, Date desde, Date hasta, int idTipo) {
        return movimientoDao.obtenerFiltrados(cbu, desde, hasta, idTipo);
    }
    
    @Override
    public boolean insertarMovimiento(String cbu, BigDecimal monto, String descripcion, int tipoMovimiento) {
        return movimientoDao.insertarMovimiento(cbu, monto, descripcion, tipoMovimiento);
    }
    
}