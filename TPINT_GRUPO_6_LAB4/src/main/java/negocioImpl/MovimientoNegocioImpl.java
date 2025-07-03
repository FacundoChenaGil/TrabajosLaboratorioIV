package negocioImpl;

import java.math.BigDecimal;

import dao.IMovimientoDao;
import daoImpl.MovimientoDaoImpl;
import negocio.IMovimientoNegocio;

public class MovimientoNegocioImpl implements IMovimientoNegocio {

    private IMovimientoDao movimientoDao = new MovimientoDaoImpl();

    @Override
    public boolean insertarMovimiento(String cbu, BigDecimal monto, String descripcion, int tipoMovimiento) {
        return movimientoDao.insertarMovimiento(cbu, monto, descripcion, tipoMovimiento);
    }
}