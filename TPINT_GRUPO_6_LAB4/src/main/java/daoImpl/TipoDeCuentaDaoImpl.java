package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ITipoDeCuentaDao;
import dao.TiposDeCuenta;
import entidad.Cliente;
import entidad.TiposDeCuentas;

public class TipoDeCuentaDaoImpl implements ITipoDeCuentaDao{

	@Override
	public List<TiposDeCuentas> listarTiposDeCuentas() {
		List<TiposDeCuentas> lista = new ArrayList<>();

	    String query = "SELECT ID_Tipo_Cuenta, Descripcion FROM Tipos_Cuenta";

	    try {
	        Connection conn = Conexion.getConexion().getSQLConexion();
	        PreparedStatement stmt = conn.prepareStatement(query);
	        ResultSet rs = stmt.executeQuery();
	    
	        while (rs.next()) {
	            TiposDeCuentas tc = new TiposDeCuentas();
	            tc.setID(rs.getInt("ID_Tipo_Cuenta"));
	            tc.setDescripcion(rs.getString("Descripcion"));
	            lista.add(tc);
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al listar tipos de cuenta: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    for(TiposDeCuentas tipo : lista) {
	    	System.out.println(tipo.getDescripcion());
	    }

	    return lista;
	}

	@Override
	public TiposDeCuentas getTipoCuentaPorID(int idTipoCuenta) {
		Conexion conexionSingleton = Conexion.getConexion(); // Obtiene la instancia del Singleton
        Connection conn = conexionSingleton.getSQLConexion(); // Obtiene la conexión JDBC de la instancia
        PreparedStatement ps = null;
        ResultSet rs = null;
        TiposDeCuentas tdc = null;
        
        try {
        	String sql = "SELECT * FROM tipos_cuenta WHERE ID_Tipo_Cuenta = ?";
        	ps = conn.prepareStatement(sql);
        	ps.setInt(1, idTipoCuenta);
        	rs = ps.executeQuery();
     		
        	if(rs.next()) {
        		tdc.setID(rs.getInt("ID_Tipo_Cuenta"));
        		tdc.setDescripcion(rs.getString("Descripcion"));
    
        	}
        }
    	catch (Exception e) {
    		e.printStackTrace();
    	} finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    	
        return tdc;
	}

}
