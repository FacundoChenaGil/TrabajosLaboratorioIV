package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ITipoDeCuentaDao;
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

	    return lista;
	}

}
