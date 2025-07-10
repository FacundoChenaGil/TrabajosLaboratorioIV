package negocio;

import entidad.TipoMovimiento;
import java.util.List;

public interface ITipoMovimientoNegocio {
    List<TipoMovimiento> listarTodos();
    TipoMovimiento obtenerPorId(int id);
}