package negocio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import entidad.Movimiento;


public interface IMovimientoNegocio {
	    List<Movimiento> listarPorCBU(String cbu);
	    List<Movimiento>filtrarMovimientos(String cbu, Date desde, Date hasta, int idTipo);
	    public boolean insertarMovimiento(String cbu, BigDecimal monto, String descripcion, int tipoMovimiento);
	}
