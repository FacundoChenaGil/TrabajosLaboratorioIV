package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.PersonaDao;
import entidad.Persona;

import java.sql.CallableStatement;

import java.sql.PreparedStatement;

public class PersonaDaoImpl implements PersonaDao {
	private static final String insert = "INSERT INTO personas(nombre, apellido, dni) VALUES(?, ?, ?)";
	private static final String delete = "DELETE FROM personas WHERE Dni = ?";
	private static final String readAll = "SELECT * FROM personas";
	private static final String exist = "SELECT 1 FROM personas WHERE Dni = ?";
	private static final String update = "UPDATE personas SET nombre = ?, apellido = ? WHERE dni = ?";
	
	public PersonaDaoImpl() {
		
	}
	
	@Override
	public boolean insert(Persona persona) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getApellido());
			statement.setString(3, persona.getDni());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isInsertExitoso;
	}

	@Override
	public boolean delete(Persona persona) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, persona.getDni());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isdeleteExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}

	@Override
	public List<Persona> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Persona> personas = new ArrayList<Persona>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				personas.add(getPersona(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return personas;
	}
	
	private Persona getPersona(ResultSet resultSet) throws SQLException
	{
		String dni = resultSet.getString("Dni");
		String nombre = resultSet.getString("Nombre");
		String apellido = resultSet.getString("Apellido");
		return new Persona(dni, nombre, apellido);
	}
	
	@Override
	public boolean update(Persona persona) {
		boolean isUpdateExitoso = false;
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		if(!isExist(persona.getDni())) {
			return isUpdateExitoso;
		}
		
		try 
		{
			statement = conexion.prepareStatement(update);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getApellido());
			statement.setString(3, persona.getDni());
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isUpdateExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return isUpdateExitoso;
	}
	
	@Override
	public boolean isExist(String dni) {
		boolean existe = false;
	    PreparedStatement statement;
	    ResultSet resultSet;
	    Connection conexion = Conexion.getConexion().getSQLConexion();

	    try {
	        statement = conexion.prepareStatement(exist);
	        statement.setString(1, dni);
	        resultSet = statement.executeQuery();
	        
	        if (resultSet.next()) {
	            existe = true; // Hay al menos un registro, existe
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return existe;
	}
}
