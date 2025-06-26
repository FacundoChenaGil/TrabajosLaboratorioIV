package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ICuentaDao;
import entidad.Cliente;
import entidad.Cuenta;

public class CuentaDaoImpl implements ICuentaDao {
	
	private TipoDeCuentaDaoImpl tdc;
	private ClienteDaoImpl cl;
	
	public CuentaDaoImpl() {
	    this.tdc = new TipoDeCuentaDaoImpl();
	    this.cl = new ClienteDaoImpl();
	}
	

	@Override
	public List<Cuenta> readAll() {
		Conexion conexionSingleton = Conexion.getConexion(); // Obtiene la instancia del Singleton
        Connection conn = conexionSingleton.getSQLConexion(); // Obtiene la conexión JDBC de la instancia
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Cuenta> listaCuentas = new ArrayList<>();

        try {
        	String sql = "SELECT * FROM cuentas";
        	
        	
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                
                cuenta.setCBU(rs.getString("CBU"));
                Date sqlDate = rs.getDate("Fecha_Creacion");
        		cuenta.setFechaCreacion(sqlDate != null ? sqlDate.toLocalDate() : null);
        		cuenta.setNumeroCuenta(rs.getString("Numero_Cuenta"));
        		cuenta.setSaldo(rs.getBigDecimal("Saldo"));
        		cuenta.setActiva(rs.getBoolean("Activa"));
        		cuenta.setTipoCuenta(tdc.getTipoCuentaPorID(rs.getInt("ID_Tipo_Cuenta")));
        		cuenta.setCliente(cl.obtenerClientePorDni(rs.getString("DNI")));
                
        		
        		listaCuentas.add(cuenta);
            }
        } catch (SQLException e) {
            System.err.println("ERROR DAO: Error al obtener todas las cuentas " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            // NO CERRAR LA CONEXIÓN DEL SINGLETON AQUÍ
        }
        return listaCuentas;
	}


	@Override
	public Cuenta read(String cbu) {
		Conexion conexionSingleton = Conexion.getConexion(); // Obtiene la instancia del Singleton
        Connection conn = conexionSingleton.getSQLConexion(); // Obtiene la conexión JDBC de la instancia
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cuenta cuenta = null;
        
        try {
        	String sql = "SELECT * FROM cuentas WHERE CBU = ?";
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, cbu);
        	rs = ps.executeQuery();
        	
        	
        	if(rs.next()) {
        		cuenta = new Cuenta();
        		
        		cuenta.setCBU(rs.getString("CBU"));
                Date sqlDate = rs.getDate("Fecha_Creacion");
        		cuenta.setFechaCreacion(sqlDate != null ? sqlDate.toLocalDate() : null);
        		cuenta.setNumeroCuenta(rs.getString("Numero_Cuenta"));
        		cuenta.setSaldo(rs.getBigDecimal("Saldo"));
        		cuenta.setActiva(rs.getBoolean("Activa"));
        		cuenta.setTipoCuenta(tdc.getTipoCuentaPorID(rs.getInt("ID_Tipo_Cuenta")));
        		cuenta.setCliente(cl.obtenerClientePorDni(rs.getString("DNI")));
        	}
        	
        }
        catch (SQLException e2) {
            System.err.println("ERROR DAO: Error al obtener la cuenta con CBU: " + cbu + e2.getMessage());
            e2.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            // NO CERRAR LA CONEXIÓN DEL SINGLETON AQUÍ
        }
                
		return cuenta;
	}

}
