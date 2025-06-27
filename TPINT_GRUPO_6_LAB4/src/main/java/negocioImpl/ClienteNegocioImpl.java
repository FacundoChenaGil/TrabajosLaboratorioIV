package negocioImpl;

import dao.IClienteDao;
import daoImpl.ClienteDaoImpl;
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

}
