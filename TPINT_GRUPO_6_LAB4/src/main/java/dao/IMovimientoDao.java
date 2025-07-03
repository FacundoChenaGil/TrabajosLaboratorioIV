package dao;

import java.math.BigDecimal;
import java.util.List;


import entidad.Movimiento;

public interface IMovimientoDao {
    boolean insertarMovimiento(String cbu, BigDecimal importe, String detalle, int tipoMovimientoId);
    List<Movimiento> obtenerMovimientosPorCBU(String cbu);
}