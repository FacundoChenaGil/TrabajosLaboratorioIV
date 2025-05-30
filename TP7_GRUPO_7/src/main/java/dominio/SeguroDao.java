package dominio;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SeguroDao {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "segurosgroup?useSSL=false";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> obtenerTiposSeguro() {
		ArrayList<String> lista = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pSt = null;
		ResultSet rs = null;
		
		try{
			conn = DriverManager.getConnection(host + dbName, user, pass);
			
			String query = "SELECT descripcion FROM tipoSeguros";
			pSt = conn.prepareStatement(query);
			rs = pSt.executeQuery();
			
			while(rs.next()){
				lista.add(rs.getString("descripcion"));
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public int obtenerProximoId() {
		int proximoId = 0;
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			cn = DriverManager.getConnection(host + dbName, user, pass);
			
			String query = "SELECT MAX(idSeguro) AS ultimoId FROM seguros";
			
			ps = cn.prepareStatement(query);
			rs = ps.executeQuery();
			
			if (rs.next()) { 
	            proximoId = rs.getInt("ultimoId") + 1;
	        } else {
	            proximoId = 1;  
	        }
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return proximoId;
	}
	
	public ArrayList<Seguro> obtenerSeguros(String filtroTipo) {	
		ArrayList<Seguro> lista = new ArrayList<Seguro>();
		Connection conn = null;
		PreparedStatement pSt = null;
		ResultSet rs = null;
		
		try{
			conn = DriverManager.getConnection(host + dbName, user, pass);
			
			String query = "SELECT idSeguro,s.descripcion,s.idTipo,ts.descripcion AS descripcionTipo,costoContratacion,costoAsegurado " +
						   "FROM seguros AS s " +
						   "INNER JOIN tiposeguros AS ts ON s.idTipo = ts.idTipo";
			
			if(filtroTipo != null && !filtroTipo.equals("0")) {
				query += " WHERE s.idTipo = ?";
				pSt = conn.prepareStatement(query);
				pSt.setInt(1, Integer.parseInt(filtroTipo));
			}
			else {
				pSt = conn.prepareStatement(query);
			}

			
			rs = pSt.executeQuery();
			
			while(rs.next()){
				
				Seguro seguroRs = new Seguro();
				seguroRs.setId(rs.getInt("idSeguro"));
				seguroRs.setDescripcion(rs.getString("descripcion"));
				seguroRs.setIdTipo(rs.getInt("idTipo"));
				seguroRs.setDescripcionTipo(rs.getString("descripcionTipo"));
				seguroRs.setCostoContratacion(rs.getFloat("costoContratacion"));
				seguroRs.setCostoAsegurado(rs.getFloat("costoAsegurado"));
				
				lista.add(seguroRs);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return lista;
	}
}
