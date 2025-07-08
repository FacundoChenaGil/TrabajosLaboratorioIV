package daoImpl;

import java.math.BigDecimal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import dao.IPromedioPorTipoCuentaDao;
import entidad.PromedioPorTipoCuenta;
import entidad.TiposDeCuentas;
import daoImpl.Conexion;


public class PromedioPorTipoCuentaDaoImpl implements IPromedioPorTipoCuentaDao {
	
	public List<PromedioPorTipoCuenta> obtenerPromedioSaldoPorTipoCuenta() {
	   
		List<PromedioPorTipoCuenta> promedios = new ArrayList<>();
	  
	    String sql="SELECT " +
	             "tc.ID_Tipo_Cuenta AS ID_Tipo_Cuenta, " +
	             "tc.Descripcion AS nombre, " +
	             "AVG(c.Saldo) AS promedio " +
	             "FROM cuentas c " +
	             "JOIN tipos_cuenta tc ON c.ID_Tipo_Cuenta = tc.ID_Tipo_Cuenta " +
	             "GROUP BY tc.ID_Tipo_Cuenta, tc.Descripcion";
	    
	    try (Connection con = (Connection) Conexion.getConexion();
	         PreparedStatement stmt = con.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            TiposDeCuentas tipo = new TiposDeCuentas(
	                rs.getInt("ID_Tipo_Cuenta"),
	                rs.getString("nombre")
	            );

	            BigDecimal promedio = rs.getBigDecimal("promedio");
	            PromedioPorTipoCuenta obj = new PromedioPorTipoCuenta(tipo, promedio);

	            promedios.add(obj);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return promedios;
	}
}







