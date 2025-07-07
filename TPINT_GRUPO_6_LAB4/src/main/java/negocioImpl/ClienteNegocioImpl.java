package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import dao.IClienteDao;
import dao.IUsuarioDao;
import daoImpl.ClienteDaoImpl;
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
}
