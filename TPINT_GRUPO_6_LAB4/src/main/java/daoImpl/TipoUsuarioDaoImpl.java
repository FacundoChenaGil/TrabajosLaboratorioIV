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
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TipoUsuario ts = null;
        
        try {
        	conn = Conexion.getConexion();
        	String sql = "SELECT * FROM tipos_usuario WHERE ID_Tipo_Usuario = ?";
        	ps = conn.prepareStatement(sql);
        	ps.setInt(1, idTipoUsuario);
        	rs = ps.executeQuery();
     		
        	if (rs.next()) {
                ts = new TipoUsuario();
                ts.setIdTipoUsuario(rs.getInt("ID_Tipo_Usuario"));
                ts.setDescripcion(rs.getString("Descripcion"));
            }
        	
        }
    	catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            Conexion.cerrarConexion(conn);
        }
		
		return ts;
	}

}
