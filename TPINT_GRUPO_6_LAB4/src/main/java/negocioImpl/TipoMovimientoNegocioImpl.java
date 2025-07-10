package negocioImpl;

import java.util.List;
import dao.ITipoMovimientoDao;
import daoImpl.TipoMovimientoDaoImpl;
import entidad.TipoMovimiento;
import negocio.ITipoMovimientoNegocio;

public class TipoMovimientoNegocioImpl implements ITipoMovimientoNegocio {

    private ITipoMovimientoDao tipoMovimientoDao = new TipoMovimientoDaoImpl();

    @Override
    public List<TipoMovimiento> listarTodos() {
        return tipoMovimientoDao.listarTodos();
    }

    @Override
    public TipoMovimiento obtenerPorId(int id) {
        return tipoMovimientoDao.obtenerPorId(id);
    }
}