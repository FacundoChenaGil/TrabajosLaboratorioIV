package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ITipoUsuarioDao;
import entidad.Cliente;
import entidad.TipoUsuario;

public class TipoUsuarioDaoImpl implements ITipoUsuarioDao {

	@Override
	public TipoUsuario getTipoUsuarioPorID(int idTipoUsuario) {
		
		Conexion conexionSingleton = Conexion.getConexion(); // Obtiene la instancia del Singleton
        Connection conn = conexionSingleton.getSQLConexion(); // Obtiene la conexión JDBC de la instancia
        PreparedStatement ps = null;
        ResultSet rs = null;
        TipoUsuario ts = null;
        
        try {
        	String sql = "SELECT * FROM tipos_usuario WHERE ID_Tipo_Usuario = ?";
        	ps = conn.prepareStatement(sql);
        	ps.setInt(1, idTipoUsuario);
        	rs = ps.executeQuery();
     		
        	if (rs.next()) {
                ts = new TipoUsuario(); // INSTANCIAR AQUÍ
                ts.setIdTipoUsuario(rs.getInt("ID_Tipo_Usuario"));
                ts.setDescripcion(rs.getString("Descripcion"));
            }
        	
        }
    	catch (Exception e) {
    		e.printStackTrace();
    	} finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
		
		return ts;
	}

}
