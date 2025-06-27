package negocioImpl;

import dao.IClienteDao;
import daoImpl.ClienteDaoImpl;
import entidad.Cliente;
import negocio.IClienteNegocio;

public class ClienteNegocioImpl implements IClienteNegocio{
	private IClienteDao clienteDao;
	
	 public ClienteNegocioImpl() {
	        this.clienteDao = new ClienteDaoImpl();
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
