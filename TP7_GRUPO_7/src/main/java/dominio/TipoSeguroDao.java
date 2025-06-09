package dominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TipoSeguroDao {
	
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
	
	public ArrayList<TipoSeguro> obtenerTiposSeguro() {
		ArrayList<TipoSeguro> lista = new ArrayList<TipoSeguro>();
		Connection conn = null;
		PreparedStatement pSt = null;
		ResultSet rs = null;
		
		try{
			conn = DriverManager.getConnection(host + dbName, user, pass);
			
			String query = "SELECT idTipo AS ID, descripcion FROM tipoSeguros";
			pSt = conn.prepareStatement(query);
			rs = pSt.executeQuery();
			
			while(rs.next()){
				TipoSeguro ts = new TipoSeguro();
				ts.setIdTipo(rs.getInt("ID"));
				ts.setDescripcion(rs.getString("descripcion"));
				
				lista.add(ts);
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return lista;
	}

}
