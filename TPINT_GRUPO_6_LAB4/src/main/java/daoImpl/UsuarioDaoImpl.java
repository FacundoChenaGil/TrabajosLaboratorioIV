package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.IUsuarioDao;
import entidad.Cliente;
import entidad.Usuario;

public class UsuarioDaoImpl implements IUsuarioDao {
	
	private TipoUsuarioDaoImpl tu;

	@Override
	public Usuario getUsuarioPorNombre(String usuario) {
		
		Conexion conexionSingleton = Conexion.getConexion(); // Obtiene la instancia del Singleton
        Connection conn = conexionSingleton.getSQLConexion(); // Obtiene la conexión JDBC de la instancia
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario us = null;
        
        try {
        	String sql = "SELECT * FROM usuarios WHERE Usuario = ?";
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, usuario);
        	rs = ps.executeQuery();
        	
        	if(rs.next()) {
        		us = new Usuario();
        		us.setUsuario(rs.getString("Usuario"));
        		us.setClave(rs.getString("Clave"));
        		us.setTipoUsuario(tu.getTipoUsuarioPorID(rs.getInt("ID_Tipo_Usuario")));
        		us.setActivo(rs.getBoolean("Activo"));
        	}
        	
        }
    	catch (Exception e) {
    		e.printStackTrace();
    	} finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
		
		return us;
	}

}
