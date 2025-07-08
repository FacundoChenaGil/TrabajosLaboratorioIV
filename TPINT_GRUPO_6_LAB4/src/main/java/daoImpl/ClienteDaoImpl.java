package daoImpl;

import dao.IClienteDao; // Importa la interfaz
import entidad.Cliente; // Importa la clase Cliente
import entidad.Cuenta;
import entidad.Usuario; // Importa la clase Usuario
import entidad.TipoUsuario; // Importa la clase TipoUsuario (para la relación con Usuario)
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date; // Para convertir LocalDate a java.sql.Date
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoImpl implements IClienteDao {
	
	private UsuarioDaoImpl us;
	
	public ClienteDaoImpl() {
	    this.us = new UsuarioDaoImpl();
	}
	
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
	    boolean exito = false;
	    Conexion conexionSingleton = Conexion.getConexion(); // Obtiene la instancia del Singleton
        Connection conn = conexionSingleton.getSQLConexion(); // Obtiene la conexión JDBC de la instancia
        PreparedStatement ps = null;

	    try {
	        // Crear nueva conexión directamente

	        String sql = "INSERT INTO Clientes (DNI, CUIL, Nombre, Apellido, Sexo, Nacionalidad, Fecha_Nacimiento, Direccion, Localidad, Provincia, Correo_Electronico, Telefono, Usuario, Activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, cliente.getDni());
	        ps.setString(2, cliente.getCuil());
	        ps.setString(3, cliente.getNombre());
	        ps.setString(4, cliente.getApellido());
	        ps.setString(5, cliente.getSexo());
	        ps.setString(6, cliente.getNacionalidad());
	        ps.setDate(7, java.sql.Date.valueOf(cliente.getFechaNacimiento()));
	        ps.setString(8, cliente.getDireccion());
	        ps.setString(9, cliente.getLocalidad());
	        ps.setString(10, cliente.getProvincia());
	        ps.setString(11, cliente.getCorreoElectronico());
	        ps.setString(12, cliente.getTelefono());
	        ps.setString(13, cliente.getUsuario().getUsuario());

	        int filas = ps.executeUpdate();
	        if (filas > 0) {
	            conn.commit(); // ← esto guarda los cambios
	            exito = true;
	        }

	        ps.close();
	        conn.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return exito;
	}

    @Override
    public int modificarCliente(Cliente cliente) {
    	Conexion conexionSingleton = Conexion.getConexion(); // Obtiene la instancia del Singleton
		Connection conn = conexionSingleton.getSQLConexion(); // Obtiene la conexión JDBC de la instancia
		PreparedStatement ps = null;
		int filasAfectadas = 0;
		
		try {
			String sql = "UPDATE clientes SET CUIL = ?, Nombre = ?, Apellido = ?, Sexo = ?, Nacionalidad = ?, "
					+ "Fecha_Nacimiento = ?, Direccion = ?, Localidad = ?, Provincia = ?, Correo_Electronico = ?, "
					+ "Telefono = ?,  Activo = ? WHERE DNI = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, cliente.getCuil());
			ps.setString(2, cliente.getNombre());
			ps.setString(3, cliente.getApellido());
			ps.setString(4, cliente.getSexo());
			ps.setString(5, cliente.getNacionalidad());
			ps.setDate(6, Date.valueOf(cliente.getFechaNacimiento()));
			ps.setString(7, cliente.getDireccion());
			ps.setString(8, cliente.getLocalidad());
			ps.setString(9, cliente.getProvincia());
			ps.setString(10, cliente.getCorreoElectronico());
			ps.setString(11, cliente.getTelefono());
			ps.setBoolean(12, cliente.isActivo());
			ps.setString(13, cliente.getDni());
			
			filasAfectadas = ps.executeUpdate();
			
			  if (filasAfectadas > 0) {
	                conn.commit();
	            } else {
	                conn.rollback();
	            } 
			
			
		} catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
        return filasAfectadas;	
    	
    }

    @Override
    public boolean eliminarCliente(String dni) { // Baja lógica
    	PreparedStatement statement = null;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        boolean estado = false;

        try {
            String query = "UPDATE Clientes SET Activo = 0 WHERE DNI = ?";
            statement = conexion.prepareStatement(query);
            statement.setString(1, dni);

            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.commit();
                estado = true;
            } else {
                conexion.rollback();
            }

        } catch (Exception e) {
            try {
                conexion.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }

        return estado;
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
    public boolean existeCorreoElectronico(String correo) {
        String sql = "SELECT COUNT(*) FROM Clientes WHERE Correo_Electronico = ? AND Activo = 1";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bancoutn?useSSL=false", "root", "root");
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    	public Cliente obtenerClientePorUsuario(String usuario) {
    	    Cliente cliente = null;
    	    Connection conn = null;
    	    PreparedStatement stmt = null;
    	    ResultSet rs = null;

    	    try {
    	        conn = Conexion.getNuevaConexion(); 
    	        String query = "SELECT * FROM Clientes WHERE Usuario = ?";
    	        stmt = conn.prepareStatement(query);
    	        stmt.setString(1, usuario);
    	        rs = stmt.executeQuery();

    	        if (rs.next()) {
    	            cliente = new Cliente();
    	            cliente.setDni(rs.getString("DNI"));
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    } finally {
    	        try {
    	            if (rs != null) rs.close();
    	            if (stmt != null) stmt.close();
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }
    	    }

    	    return cliente;
    	}
    

	@Override
	public boolean existeClienteActivo(String dni) {
		PreparedStatement statement = null;
        ResultSet rs = null;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        boolean existe = false;

        String consulta = "SELECT COUNT(*) FROM Clientes WHERE DNI = ? AND Activo = 1"; 
        try {
            statement = conexion.prepareStatement(consulta);
            statement.setString(1, dni);
            rs = statement.executeQuery();

            if (rs.next()) {
                if (rs.getInt(1) > 0) {
                    existe = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return existe;
	}

	@Override
	public String obtenerDNIPorUsuario(String usuario) {
		Conexion conexionSingleton = Conexion.getConexion(); // Obtiene la instancia del Singleton
        Connection conn = conexionSingleton.getSQLConexion(); // Obtiene la conexión JDBC de la instancia
        PreparedStatement ps = null;
        ResultSet rs = null;
        String dni = null;
        
        try {
        	String sql = "SELECT DNI FROM Clientes WHERE Usuario = ?";
        	
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, usuario);
            rs = ps.executeQuery();
            
            if(rs.next()) {
            	dni = rs.getString("DNI");
            }
            
        } catch (Exception e) {
            e.printStackTrace(); // log o manejo de errores
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                // No cierres conn si usás conexión compartida tipo Singleton
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return dni;
	}
	
	
	// METODO PARA REPORTE
	
	public List<Cliente> obtenerClientesNuevosEntreFechas(LocalDate desde, LocalDate hasta) {
	    List<Cliente> lista = new ArrayList<>();

	    String sql = 
	    		"SELECT sub.DNI, sub.Nombre, sub.Apellido, sub.Usuario, sub.CBU, sub.Fecha_Alta " +
	    			    "FROM (" +
	    			    "   SELECT c.DNI, c.Nombre, c.Apellido, c.Usuario, cu.CBU, MIN(cu.Fecha_Creacion) AS Fecha_Alta " +
	    			    "   FROM Clientes c " +
	    			    "   INNER JOIN Cuentas cu ON c.DNI = cu.DNI " +
	    			    "   GROUP BY c.DNI, c.Nombre, c.Apellido, c.Usuario" +
	    			    ") AS sub " +
	    			    "WHERE sub.Fecha_Alta BETWEEN ? AND ? " +
	    			    "ORDER BY sub.Fecha_Alta ASC";

	    Connection con = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        con = Conexion.getConexion().getSQLConexion(); 
	        stmt = con.prepareStatement(sql);
	        stmt.setDate(1, java.sql.Date.valueOf(desde));
	        stmt.setDate(2, java.sql.Date.valueOf(hasta));

	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            Cliente c = new Cliente();
	            c.setDni(rs.getString("DNI"));
	            c.setNombre(rs.getString("Nombre"));
	            c.setApellido(rs.getString("Apellido"));

	            Usuario u = new Usuario();
	            u.setUsuario(rs.getString("Usuario"));
	            c.setUsuario(u);
	            
	            Cuenta cuenta = new Cuenta();
	            cuenta.setCbu(rs.getString("CBU"));
	            c.setCuenta(cuenta);
	            
	            	            
	            c.setFechaAlta(rs.getDate("Fecha_Alta").toLocalDate());

	            lista.add(c);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            Conexion.getConexion().cerrarConexion(); 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return lista;
	}
	
	
}


