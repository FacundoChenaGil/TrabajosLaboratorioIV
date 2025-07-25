package daoImpl;

import dao.IClienteDao; // Importa la interfaz
import entidad.Cliente; // Importa la clase Cliente
import entidad.Cuenta;
import entidad.Usuario; // Importa la clase Usuario
import entidad.TipoUsuario; // Importa la clase TipoUsuario (para la relación con Usuario)

import java.math.BigDecimal;
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
	    Connection conn = null;
	    PreparedStatement ps = null;
	    boolean exito = false;

	    try {
	        // 1. OBTENER una conexión NUEVA
	        conn = Conexion.getConexion();

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

	        // 2. USAR la conexión
	        int filas = ps.executeUpdate();
	        if (filas > 0) {
	            conn.commit(); // Confirmamos la transacción solo si fue exitosa
	            exito = true;
	        }

	    } catch (SQLException e) {
	        // Si hay un error, revertimos los cambios
	        if (conn != null) {
	            try {
	                conn.rollback();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	        e.printStackTrace();
	    } finally {
	        // 3. CERRAR los recursos en el bloque finally para asegurar que siempre se ejecute
	        try {
	            if (ps != null) ps.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        // Usamos nuestro método de ayuda para cerrar la conexión
	        Conexion.cerrarConexion(conn);
	    }

	    return exito;
	}

    @Override
    public int modificarCliente(Cliente cliente) {
        Connection conn = null;
        PreparedStatement ps = null;
        int filasAfectadas = 0;

        try {
            conn = Conexion.getConexion();
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
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Conexion.cerrarConexion(conn);
        }

        return filasAfectadas;
    }

    @Override
    public boolean eliminarCliente(String dni) { // Baja lógica
        Connection conn = null;
        PreparedStatement ps = null;
        boolean exito = false;

        try {
            conn = Conexion.getConexion();
            String query = "UPDATE Clientes SET Activo = 0 WHERE DNI = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, dni);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                conn.commit();
                exito = true;
            } else {
                conn.rollback();
            }

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Conexion.cerrarConexion(conn);
        }

        return exito;
    }

    @Override
    public Cliente obtenerClientePorDni(String dni) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cliente cliente = null;

        try {
            conn = Conexion.getConexion();
            // Usar el método privado para cargar el cliente para no repetir código
            String sql = "SELECT c.*, u.Clave, u.ID_Tipo_Usuario, tu.Descripcion AS TipoUsuarioDescripcion, u.Activo AS UsuarioActivo " +
                         "FROM Clientes c " +
                         "INNER JOIN Usuarios u ON c.Usuario = u.Usuario " +
                         "INNER JOIN Tipos_Usuario tu ON u.ID_Tipo_Usuario = tu.ID_Tipo_Usuario " +
                         "WHERE c.DNI = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();

            if (rs.next()) {
                cliente = cargarClienteDesdeResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Conexion.cerrarConexion(conn);
        }

        return cliente;
    }

    @Override
    public List<Cliente> obtenerTodosLosClientes() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Cliente> listaClientes = new ArrayList<>();

        try {
            conn = Conexion.getConexion();
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
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Conexion.cerrarConexion(conn);
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
        // El try-with-resources es una excelente forma de asegurar que los recursos se cierren.
        // Automáticamente llama a .close() al final, incluso si hay errores.
        try (Connection conn = Conexion.getConexion();
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
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cliente cliente = null;

        try {
            conn = Conexion.getConexion();
            // Reutilizamos el método de carga para mantener la consistencia
            String sql = "SELECT c.*, u.Clave, u.ID_Tipo_Usuario, tu.Descripcion AS TipoUsuarioDescripcion, u.Activo AS UsuarioActivo " +
                         "FROM Clientes c " +
                         "INNER JOIN Usuarios u ON c.Usuario = u.Usuario " +
                         "INNER JOIN Tipos_Usuario tu ON u.ID_Tipo_Usuario = tu.ID_Tipo_Usuario " +
                         "WHERE c.Usuario = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                cliente = cargarClienteDesdeResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Conexion.cerrarConexion(conn);
        }

        return cliente;
    }
    

    @Override
    public boolean existeClienteActivo(String dni) {
        String sql = "SELECT COUNT(*) FROM Clientes WHERE DNI = ? AND Activo = 1";
        // Usamos try-with-resources para un código más limpio
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dni);
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
    public String obtenerDNIPorUsuario(String usuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String dni = null;

        try {
            conn = Conexion.getConexion();
            String sql = "SELECT DNI FROM Clientes WHERE Usuario = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                dni = rs.getString("DNI");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Conexion.cerrarConexion(conn);
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
	        con = Conexion.getConexion(); 
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
	            Conexion.cerrarConexion(con); 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return lista;
	}
	
	public List<Cliente> buscarClientesPorNombreApellidoUsuario(String texto) {
	    List<Cliente> clientes = new ArrayList<>();

	    String sql = "SELECT c.DNI, c.Nombre, c.Apellido, c.Correo_Electronico, c.Telefono, c.Activo, u.Usuario " +
                "FROM clientes c " +
                "JOIN usuarios u ON c.Usuario = u.Usuario " +
                "WHERE c.Nombre LIKE ? OR c.Apellido LIKE ? OR u.Usuario LIKE ? " +
                "ORDER BY c.Apellido ASC, c.Nombre ASC";

	    try (Connection con = Conexion.getConexion();
	         PreparedStatement stmt = con.prepareStatement(sql)) {

	        String filtro = texto + "%";
	        stmt.setString(1, filtro);
	        stmt.setString(2, filtro);
	        stmt.setString(3, filtro);

	        try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cliente cliente = new Cliente();
    
                    cliente.setDni(rs.getString("DNI"));
                    cliente.setNombre(rs.getString("Nombre"));
                    cliente.setApellido(rs.getString("Apellido"));
                    cliente.setCorreoElectronico(rs.getString("Correo_Electronico"));
                    cliente.setTelefono(rs.getString("Telefono"));
                    cliente.setActivo(rs.getBoolean("Activo"));
    
                    Usuario usuario = new Usuario();
                    usuario.setUsuario(rs.getString("Usuario"));
                    cliente.setUsuario(usuario);
    
                    clientes.add(cliente);
                }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return clientes;
	}
	


	@Override
	public boolean existeCUIL(String cuil) {
		String sql = "SELECT COUNT(*) FROM Clientes WHERE CUIL = ? AND Activo = 1";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cuil);
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

  
  


public List<Cliente> obtenerTodosConSaldo() {
    List<Cliente> lista = new ArrayList<>();

    String sql =  "SELECT c.dni, c.nombre, c.apellido, SUM(cu.saldo) AS saldo_total " +
                  "FROM clientes c " +
                  "INNER JOIN cuentas cu ON c.dni = cu.dni " +
                  "GROUP BY c.dni, c.nombre, c.apellido " +
                  "HAVING saldo_total > 0";

    try (Connection con = Conexion.getConexion();
         PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setDni(rs.getString("DNI"));
            cliente.setNombre(rs.getString("Nombre"));
            cliente.setApellido(rs.getString("Apellido"));
            cliente.setSaldoTotal(rs.getBigDecimal("saldo_total")); 
            lista.add(cliente);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
}


public List<Cliente> obtenerClientesConSaldoMinimo(BigDecimal saldoMinimo, String nombre, int offset, int limite) {
    List<Cliente> lista = new ArrayList<>();

    boolean filtrarNombre = nombre != null && !nombre.trim().isEmpty();
    boolean filtrarMonto = saldoMinimo != null && saldoMinimo.compareTo(BigDecimal.ZERO) > 0;

    String sql = "SELECT c.DNI, c.Nombre, c.Apellido, SUM(cu.Saldo) AS SaldoTotal " +
                 "FROM clientes c INNER JOIN cuentas cu ON c.DNI = cu.DNI ";

    if (filtrarNombre) {
        sql += "WHERE c.Nombre LIKE ? OR c.Apellido LIKE ? ";
    }

    sql += "GROUP BY c.DNI, c.Nombre, c.Apellido ";

    if (filtrarMonto && filtrarNombre) {
        sql += "HAVING SUM(cu.Saldo) >= 0 AND SUM(cu.Saldo) >= ? ";
    } else if (filtrarMonto) {
        sql += "HAVING SUM(cu.Saldo) >= ? ";
    } else {
        sql += "HAVING SUM(cu.Saldo) >= 0 ";
    }

    sql += "LIMIT ? OFFSET ?";

    try (Connection con = Conexion.getConexion();
         PreparedStatement stmt = con.prepareStatement(sql)) {

        int i = 1;

        if (filtrarNombre) {
            String filtro = nombre.trim() + "%";
            stmt.setString(i++, filtro);
            stmt.setString(i++, filtro);
        }

        if (filtrarMonto) {
            stmt.setBigDecimal(i++, saldoMinimo);
        }

        stmt.setInt(i++, limite);
        stmt.setInt(i++, offset);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Cliente c = new Cliente();
            c.setDni(rs.getString("DNI"));
            c.setNombre(rs.getString("Nombre"));
            c.setApellido(rs.getString("Apellido"));
            c.setSaldoTotal(rs.getBigDecimal("SaldoTotal"));
            lista.add(c);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
}

public int contarClientesConSaldoMinimo(BigDecimal saldoMinimo, String nombre) {
    int total = 0;

    boolean filtrarNombre = nombre != null && !nombre.trim().isEmpty();
    boolean filtrarMonto = saldoMinimo != null && saldoMinimo.compareTo(BigDecimal.ZERO) > 0;

    String sql = "SELECT COUNT(*) AS Total FROM (" +
                 "SELECT c.DNI " +
                 "FROM clientes c INNER JOIN cuentas cu ON c.DNI = cu.DNI ";

    if (filtrarNombre) {
        sql += "WHERE c.Nombre LIKE ? OR c.Apellido LIKE ? ";
    }

    sql += "GROUP BY c.DNI, c.Nombre, c.Apellido ";

    if (filtrarMonto && filtrarNombre) {
        sql += "HAVING SUM(cu.Saldo) >= 0 AND SUM(cu.Saldo) >= ? ";
    } else if (filtrarMonto) {
        sql += "HAVING SUM(cu.Saldo) >= ? ";
    } else {
        sql += "HAVING SUM(cu.Saldo) >= 0 ";
    }

    sql += ") AS subconsulta";

    try (Connection con = Conexion.getConexion();
         PreparedStatement stmt = con.prepareStatement(sql)) {

        int i = 1;

        if (filtrarNombre) {
            String filtro = nombre.trim() + "%";
            stmt.setString(i++, filtro);
            stmt.setString(i++, filtro);
        }

        if (filtrarMonto) {
            stmt.setBigDecimal(i++, saldoMinimo);
        }

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            total = rs.getInt("Total");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return total;
}
}