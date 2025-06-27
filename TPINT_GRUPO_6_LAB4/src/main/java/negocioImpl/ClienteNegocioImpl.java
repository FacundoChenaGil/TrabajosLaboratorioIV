package negocioImpl;

import dao.IClienteDao;
import dao.IUsuarioDao;
import daoImpl.ClienteDaoImpl;
import daoImpl.UsuarioDaoImpl;
import entidad.Cliente;
import entidad.Usuario;
import negocio.IClienteNegocio;

public class ClienteNegocioImpl implements IClienteNegocio{
	private IClienteDao clienteDao;
	private IUsuarioDao usuarioDao;

	public ClienteNegocioImpl() {
        this.clienteDao = new ClienteDaoImpl();
        this.usuarioDao = new UsuarioDaoImpl();
    }
	 @Override
	 public boolean registrarCliente(Cliente cliente) {
		    System.out.println("🔍 [Negocio] Verificando si el cliente ya existe...");

		    if (clienteDao.existeClienteActivo(cliente.getDni()) || clienteDao.existeCorreo(cliente.getCorreoElectronico())) {
		        System.out.println("❌ [Negocio] El cliente ya existe (DNI o correo).");
		        return false;
		    }

		    System.out.println("✅ [Negocio] Cliente no existe, intentando insertar usuario...");

		    boolean usuarioInsertado = clienteDao.insertarUsuario(cliente.getUsuario());
		    System.out.println("🧾 ¿Se insertó el usuario?: " + usuarioInsertado);

		    if (!usuarioInsertado) {
		        System.out.println("❌ [Negocio] Falló al insertar el usuario.");
		        return false;
		    }

		    boolean clienteInsertado = clienteDao.insertarCliente(cliente);
		    System.out.println("📥 ¿Se insertó el cliente?: " + clienteInsertado);

		    return clienteInsertado;
		}
	 
	 
	@Override
	public boolean existeClienteActivo(String dni) {
		return clienteDao.existeClienteActivo(dni);
	}
	@Override
	public Cliente obtenerClientePorDni(String dni) {
		Cliente cliente = new Cliente();
		
		cliente = clienteDao.obtenerClientePorDni(dni);
		
		return cliente;
	}
	
	

}
