package daoImpl;

import dao.IClienteDao; // Importa la interfaz
import entidad.Cliente; // Importa la clase Cliente
import entidad.Usuario; // Importa la clase Usuario
import entidad.TipoUsuario; // Importa la clase TipoUsuario (para la relación con Usuario)
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date; // Para convertir LocalDate a java.sql.Date
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoImpl implements IClienteDao {
	
	private UsuarioDaoImpl us;
	
	
	
	private Cliente cargarClienteDesdeResultSet(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setDni(rs.getString("DNI"));
        cliente.setCuil(rs.getString("CUIL"));
        cliente.setNombre(rs.getString("Nombre"));
        cliente.setApellido(rs.getString("Apellido"));
        cliente.setSexo(rs.getString("Sexo"));
        cliente.setNacionalidad(rs.getString("Nacionalidad"));
        // Asegúrate de que Fecha_Nacimiento no sea null en la DB, o maneja el null
        Date sqlDate = rs.getDate("Fecha_Nacimiento");
        cliente.setFechaNacimiento(sqlDate != null ? sqlDate.toLocalDate() : null);
        cliente.setDireccion(rs.getString("Direccion"));
        cliente.setLocalidad(rs.getString("Localidad"));
        cliente.setProvincia(rs.getString("Provincia"));
        cliente.setCorreoElectronico(rs.getString("Correo_Electronico"));
        cliente.setTelefono(rs.getString("Telefono"));
        cliente.setActivo(rs.getBoolean("Activo")); // Activo del cliente

        Usuario usuario = new Usuario();
        usuario.setUsuario(rs.getString("Usuario"));
        usuario.setClave(rs.getString("Clave")); // La clave puede ser útil, pero con cuidado
        usuario.setActivo(rs.getBoolean("UsuarioActivo")); // Activo del usuario

        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setIdTipoUsuario(rs.getInt("ID_Tipo_Usuario"));
        tipoUsuario.setDescripcion(rs.getString("TipoUsuarioDescripcion"));
        usuario.setTipoUsuario(tipoUsuario);

        cliente.setUsuario(usuario); // Asignar el objeto Usuario
        return cliente;
    }
	
	@Override
    public boolean agregarCliente(Cliente cliente) {
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        return false;
    }

    @Override
    public boolean eliminarCliente(String dni) { // Baja lógica
        return false;
    }

    @Override
    public Cliente obtenerClientePorDni(String dni) {
    	Conexion conexionSingleton = Conexion.getConexion(); // Obtiene la instancia del Singleton
        Connection conn = conexionSingleton.getSQLConexion(); // Obtiene la conexión JDBC de la instancia
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cliente cliente = null;
        
        try {
        	String sql = "SELECT * FROM clientes WHERE DNI = ?";
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, dni);
        	rs = ps.executeQuery();
     		
        	if(rs.next()) {
        		cliente = new Cliente();
        		
        		cliente.setDni(rs.getString("DNI"));
        		cliente.setCuil(rs.getString("CUIL"));
        		cliente.setNombre(rs.getString("Nombre"));
        		cliente.setApellido(rs.getString("Apellido"));
        		cliente.setSexo(rs.getString("Sexo"));
        		cliente.setNacionalidad(rs.getString("Nacionalidad"));
        		Date sqlDate = rs.getDate("Fecha_Nacimiento");
        		cliente.setFechaNacimiento(sqlDate != null ? sqlDate.toLocalDate() : null);
        		cliente.setDireccion(rs.getString("Direccion"));
        		cliente.setLocalidad(rs.getString("Localidad"));
        		cliente.setProvincia(rs.getString("Provincia"));
        		cliente.setCorreoElectronico(rs.getString("Correo_Electronico"));
        		cliente.setTelefono(rs.getString("Telefono"));
        		cliente.setUsuario(us.getUsuarioPorNombre(rs.getString("Usuario")));
        		cliente.setActivo(rs.getBoolean("Activo"));
        		
        	}
        }
    	catch (Exception e) {
    		e.printStackTrace();
    	} finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    	
        return cliente;
    }

    @Override
    public List<Cliente> obtenerTodosLosClientes() {
    	Conexion conexionSingleton = Conexion.getConexion(); // Obtiene la instancia del Singleton
        Connection conn = conexionSingleton.getSQLConexion(); // Obtiene la conexión JDBC de la instancia
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Cliente> listaClientes = new ArrayList<>();

        try {
            String sql = "SELECT c.*, u.Clave, u.ID_Tipo_Usuario, tu.Descripcion AS TipoUsuarioDescripcion, u.Activo AS UsuarioActivo " +
                         "FROM Clientes c " +
                         "INNER JOIN Usuarios u ON c.Usuario = u.Usuario " +
                         "INNER JOIN Tipos_Usuario tu ON u.ID_Tipo_Usuario = tu.ID_Tipo_Usuario";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listaClientes.add(cargarClienteDesdeResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("ERROR DAO: Error al obtener todos los clientes: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            // NO CERRAR LA CONEXIÓN DEL SINGLETON AQUÍ
        }
        return listaClientes;
    }

    @Override
    public List<Cliente> obtenerClientesPaginado(int offset, int limit, String busqueda, String filtroProvincia) {
        List<Cliente> listaClientes = new ArrayList<>();
        return listaClientes;
    }

    @Override
    public int contarClientes(String busqueda, String filtroProvincia) {
        return 0;
    }

    @Override
    public Cliente obtenerClientePorUsuario(String usuarioStr) {
        Cliente cliente = null;
        return cliente;
    }
}
