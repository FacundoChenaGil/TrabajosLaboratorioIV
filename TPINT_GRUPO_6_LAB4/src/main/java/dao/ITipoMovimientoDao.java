package dao;

import entidad.TipoMovimiento;
import java.util.List;

public interface ITipoMovimientoDao {
    TipoMovimiento obtenerPorId(int id);
    List<TipoMovimiento> listarTodos();
}
