package negocio;

import java.math.BigDecimal;

public interface IMovimientoNegocio {
    boolean insertarMovimiento(String cbu, BigDecimal monto, String descripcion, int tipoMovimiento);
}
