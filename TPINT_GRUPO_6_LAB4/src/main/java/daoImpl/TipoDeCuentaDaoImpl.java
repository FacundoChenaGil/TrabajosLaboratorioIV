package daoImpl;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ITipoDeCuentaDao;
import dao.ITipoDeCuentaDao;
import entidad.Cliente;
import entidad.TiposDeCuentas;

public class TipoDeCuentaDaoImpl implements ITipoDeCuentaDao{

	@Override
	public List<TiposDeCuentas> listarTiposDeCuentas() {
		List<TiposDeCuentas> lista = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    String query = "SELECT ID_Tipo_Cuenta, Descripcion FROM Tipos_Cuenta";

	    try {
	        conn = Conexion.getConexion();
	        ps = conn.prepareStatement(query);
	        rs = ps.executeQuery();
	    
	        while (rs.next()) {
	            TiposDeCuentas tc = new TiposDeCuentas();
	            tc.setID(rs.getInt("ID_Tipo_Cuenta"));
	            tc.setDescripcion(rs.getString("Descripcion"));
	            lista.add(tc);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
	        try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
	        Conexion.cerrarConexion(conn);
	    }

	    return lista;
	}

	@Override
	public TiposDeCuentas getTipoCuentaPorID(int idTipoCuenta) {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TiposDeCuentas tdc = null;
        
        try {
        	conn = Conexion.getConexion();
        	String sql = "SELECT * FROM tipos_cuenta WHERE ID_Tipo_Cuenta = ?";
        	ps = conn.prepareStatement(sql);
        	ps.setInt(1, idTipoCuenta);
        	rs = ps.executeQuery();
     		
        	if(rs.next()) {
        		tdc = new TiposDeCuentas();
        		tdc.setID(rs.getInt("ID_Tipo_Cuenta"));
        		tdc.setDescripcion(rs.getString("Descripcion"));
        	}
        }
    	catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            Conexion.cerrarConexion(conn);
        }
    	
        return tdc;
	}

}
