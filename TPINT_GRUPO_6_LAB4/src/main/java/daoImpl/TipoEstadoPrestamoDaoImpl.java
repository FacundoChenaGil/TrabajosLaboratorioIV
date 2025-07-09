package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ITipoEstadoPrestamoDao;
import entidad.TipoEstadoPrestamo;

public class TipoEstadoPrestamoDaoImpl implements ITipoEstadoPrestamoDao {

	@Override
	public TipoEstadoPrestamo read(int id) {
		Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
		TipoEstadoPrestamo ts = null;
		
		String sql = "SELECT * FROM tipos_estados_prestamo WHERE ID_Tipo_Estado = ?";
		
		 try {
	            conn = Conexion.getConexion();
	            ps = conn.prepareStatement(sql);
	            ps.setInt(1, id);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	                ts.setIDTipoEstado(rs.getInt("ID_Tipo_Estado"));
	                ts.setDescripcion(rs.getString("Descripcion"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	        	try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
	        	try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
	            Conexion.cerrarConexion(conn);
	        }
		 
		return ts;
	}

}
