package dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


import entidad.Movimiento;

public interface IMovimientoDao {
	List<Movimiento> obtenerPorCBU(String cbu);
    List<Movimiento> obtenerFiltrados(String cbu, Date desde, Date hasta, int idTipo);
    public boolean insertarMovimiento(String cbu, BigDecimal monto, String descripcion, int tipoMovimiento);
}
