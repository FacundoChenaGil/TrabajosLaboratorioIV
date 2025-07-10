package negocioImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.IClienteDao;
import dao.IUsuarioDao;
import daoImpl.ClienteDaoImpl;
import daoImpl.Conexion;
import daoImpl.UsuarioDaoImpl;
import entidad.Cliente;
import entidad.Usuario;
import negocio.IClienteNegocio;

public class ClienteNegocioImpl implements IClienteNegocio {
    private IClienteDao clienteDao;
    private IUsuarioDao usuarioDao;

    public ClienteNegocioImpl() {
        this.clienteDao = new ClienteDaoImpl();
        this.usuarioDao = new UsuarioDaoImpl();
    }
    
    @Override
    public boolean registrarCliente(Cliente cliente) {
        System.out.println("🔍 [Negocio] Verificando si el cliente ya existe...");

        // Verificar si ya existe un cliente con el mismo DNI
        if (clienteDao.existeClienteActivo(cliente.getDni())) {
            System.out.println("❌ [Negocio] Ya existe un cliente con el DNI: " + cliente.getDni());
            return false;
        }

        // Verificar si el correo electrónico ya está en uso
        if (clienteDao.existeCorreoElectronico(cliente.getCorreoElectronico())) {
            System.out.println("❌ [Negocio] El correo electrónico ya está en uso: " + cliente.getCorreoElectronico());
            return false;
        }

        // Insertar el usuario primero
        System.out.println("✅ [Negocio] Insertando usuario...");
        boolean usuarioInsertado = usuarioDao.insertarUsuario(cliente.getUsuario());
        System.out.println("🧾 ¿Se insertó el usuario?: " + usuarioInsertado);

        if (!usuarioInsertado) {
            System.out.println("❌ [Negocio] Falló al insertar el usuario.");
            return false;
        }

        // Luego insertar el cliente
        System.out.println("✅ [Negocio] Insertando cliente...");
        boolean clienteInsertado = clienteDao.agregarCliente(cliente);
        System.out.println("📥 ¿Se insertó el cliente?: " + clienteInsertado);

        return clienteInsertado;
    }
    
    @Override
    public boolean existeClienteActivo(String dni) {
        return clienteDao.existeClienteActivo(dni);
    }
    
    @Override
    public Cliente obtenerClientePorDni(String dni) {
        return clienteDao.obtenerClientePorDni(dni);
    }

	@Override
	public String obtenerDNIPorUsuario(String usuario) {
		
		String dni = clienteDao.obtenerDNIPorUsuario(usuario);
		
		return dni;
	}

	@Override
	public List<Cliente> obtenerClientes() {
		
		List<Cliente> listaClientes = new ArrayList<>();
		
		listaClientes = clienteDao.obtenerTodosLosClientes();
		
		return listaClientes;
	}

	@Override
	public int modificarCliente(Cliente cliente) {
		
		int actualizado = clienteDao.modificarCliente(cliente);
			
		return actualizado;
	}
	
	@Override
	public Cliente obtenerClientePorUsuario(String usuario) {
	    return clienteDao.obtenerClientePorUsuario(usuario);
	}

	@Override
	public boolean existeCorreoElectronico(String correo) {
		return clienteDao.existeCorreoElectronico(correo);
	}

	@Override
	public boolean eliminarCliente(String dni) {
		return clienteDao.eliminarCliente(dni);
	}
	// Para Reporte
	public List<Cliente> obtenerClientesNuevosEntreFechas(LocalDate desde, LocalDate hasta) {
	    if (desde == null || hasta == null || desde.isAfter(hasta)) {
	        throw new IllegalArgumentException("Fechas inválidas");
	    }

	    return clienteDao.obtenerClientesNuevosEntreFechas(desde, hasta);
	}
	
	public List<Cliente> buscarClientesPorNombreApellidoUsuario(String texto) {
	    return clienteDao.buscarClientesPorNombreApellidoUsuario(texto);
	}
	
	public List<Cliente> obtenerTodosLosClientes() {
	    return clienteDao.obtenerTodosLosClientes();
	}

	@Override
	public boolean existeCUIL(String cuil) {
		return clienteDao.existeCUIL(cuil);
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

	    sql += "ORDER BY SaldoTotal DESC LIMIT ? OFFSET ?"; // <--- Paginación

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
	    IClienteDao clienteDao = new ClienteDaoImpl();
	    return clienteDao.contarClientesConSaldoMinimo(saldoMinimo, nombre);
	}
	
	
	
}
	