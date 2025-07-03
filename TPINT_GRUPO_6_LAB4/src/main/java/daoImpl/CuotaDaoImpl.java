package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ICuotaDao;
import daoImpl.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import entidad.Cuota;



public class CuotaDaoImpl implements ICuotaDao{
	
	public List<Cuota> obtenerCuotasPagadasPorPrestamo(int idPrestamo){
		
		List<Cuota> cuotasPagadas = new ArrayList<>();
		String sql = "SELECT * FROM Cuotas WHERE ID_Prestamo = ? AND Fecha_Pago IS NOT NULL ORDER BY Numero_Cuota";
		
		try (Connection con = Conexion.getNuevaConexion();
				
		    PreparedStatement stmt = con.prepareStatement(sql)){
			
			stmt.setInt(1, idPrestamo) ;
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
			    Cuota cuota = new Cuota();
			    cuota.setIdCuota(rs.getInt("ID_Cuota"));
			    cuota.setIdPrestamo(rs.getInt("ID_Prestamo"));
			    cuota.setNumeroCuota(rs.getInt("Numero_Cuota")); 
			    cuota.setImporte(rs.getFloat("Importe"));
			    cuota.setNumeroCuenta(rs.getString("Numero_Cuenta")); 

			    java.sql.Date fechaVenc = rs.getDate("Fecha_Vencimiento");
			    if (fechaVenc != null)
			        cuota.setFechaVencimiento(fechaVenc.toLocalDate());

			    java.sql.Date fechaPago = rs.getDate("Fecha_Pago");
			    if (fechaPago != null)
			        cuota.setFechaPago(fechaPago.toLocalDate());
			    
			    cuotasPagadas.add(cuota);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			
		}
		return cuotasPagadas;		
				
	}
	

}
