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
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario us = null;
        
        try {
        	conn = Conexion.getConexion();
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
    	catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            Conexion.cerrarConexion(conn);
        }
		
		return us;
	}
	
	@Override
	public boolean existeUsuario(String nombreUsuario) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    boolean existe = false;

	    try {
	    	conn = Conexion.getConexion();
	        String sql = "SELECT COUNT(*) FROM Usuarios WHERE Usuario = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, nombreUsuario);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            existe = rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            Conexion.cerrarConexion(conn);
	    }

	    return existe;
	}
	
	@Override
	public boolean insertarUsuario(Usuario usuario) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    boolean exito = false;

	    try {
	        conn = Conexion.getConexion();
	        String sql = "INSERT INTO Usuarios (Usuario, Clave, ID_Tipo_Usuario, Activo) VALUES (?, ?, ?, 1)";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, usuario.getUsuario());
	        ps.setString(2, usuario.getClave());
	        ps.setInt(3, usuario.getTipoUsuario().getIdTipoUsuario());

	        int filas = ps.executeUpdate();
	        if (filas > 0) {
	            conn.commit();
	            exito = true;
	        }
	    } catch (SQLException e) {
	        try { if (conn != null) conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
	        e.printStackTrace();
	    } finally {
	    	try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            Conexion.cerrarConexion(conn);
	    }

	    return exito;
	}

	@Override
	public boolean modificarClave(Usuario usuario) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    boolean exito = false;

	    try {
	    	conn = Conexion.getConexion();
	        String sql = "UPDATE usuarios SET Clave = ? WHERE Usuario = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, usuario.getClave());
	        ps.setString(2, usuario.getUsuario());

	        int filasAfectadas = ps.executeUpdate();

	        if (filasAfectadas > 0) {
	            conn.commit();
	            exito = true;
	        }
	    } catch (SQLException e) {
	        try { if (conn != null) conn.rollback(); } catch (SQLException rollbackEx) { rollbackEx.printStackTrace(); }
	        e.printStackTrace();
	    } finally {
	        try { if (ps != null) ps.close(); } catch (SQLException ex) { ex.printStackTrace(); }
	        Conexion.cerrarConexion(conn);
	    }

	    return exito;	
	}

}
