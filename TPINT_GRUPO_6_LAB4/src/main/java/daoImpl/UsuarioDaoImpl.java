package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.IUsuarioDao;
import entidad.Cliente;
import entidad.Usuario;

public class UsuarioDaoImpl implements IUsuarioDao {
	
	private TipoUsuarioDaoImpl tu;
	
	public UsuarioDaoImpl() {
        this.tu = new TipoUsuarioDaoImpl(); 
    }
	
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
	
	@Override
	public boolean existeUsuario(String nombreUsuario) {
	    String sql = "SELECT COUNT(*) FROM Usuarios WHERE Usuario = ?";
	    boolean existe = false;

	    try (Connection conn = Conexion.getConexion().getSQLConexion();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, nombreUsuario);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            existe = rs.getInt(1) > 0;
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return existe;
	}
	
	@Override
	public boolean insertarUsuario(Usuario usuario) {
	    boolean exito = false;

	    try {
	        // Crear nueva conexión directa
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bancoutn?useSSL=false", "root", "root");

	        String sql = "INSERT INTO Usuarios (Usuario, Clave, ID_Tipo_Usuario, Activo) VALUES (?, ?, ?, 1)";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, usuario.getUsuario());
	        ps.setString(2, usuario.getClave());
	        ps.setInt(3, usuario.getTipoUsuario().getIdTipoUsuario());

	        int filas = ps.executeUpdate();
	        if (filas > 0) {
	            exito = true;
	        }

	        ps.close();
	        conn.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return exito;
	}

}
