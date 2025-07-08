package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import dao.IPrestamoBackupDao;
import entidad.PrestamoBackup;

public class PrestamoBackupDaoImpl implements IPrestamoBackupDao {

	@Override
	public boolean AltaPrestamo(PrestamoBackup prestamo) {
		String query = "INSERT INTO prestamos (DNI, Fecha_Solicitud, Importe_Pedido, Importe_a_Pagar, Plazo_Pago_Meses, Cantidad_Cuotas, Importe_Cuota, ID_Tipo_Estado, ID_Cuenta_Acreditacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        int filasAfectadas = 0;
		
		try {
			conn = Conexion.getConexion().getSQLConexion();
			ps = conn.prepareStatement(query);
			ps.setString(1, prestamo.getCliente().getDni());
			ps.setTimestamp(2, Timestamp.valueOf(prestamo.getFechaSolicitud()));
			ps.setBigDecimal(3, prestamo.getImportePedido());
			ps.setBigDecimal(4, prestamo.getImporteAPagar());
			ps.setInt(5, prestamo.getPlazoPagoMeses());
			ps.setInt(6, prestamo.getCantidadCuotas());
			ps.setBigDecimal(7, prestamo.getImporte_Cuota());
			ps.setInt(8, prestamo.getTipoEstadoPrestamo().getIDTipoEstado());
			ps.setString(9, prestamo.getCuentaAcreditada().getCbu());
			
			filasAfectadas = ps.executeUpdate();
			
			if (filasAfectadas > 0) {
                conn.commit();
            } else {
                conn.rollback();
            }

		} catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
		if(filasAfectadas == 1) {
			return true;
		}
		
		return false;
	}

}
