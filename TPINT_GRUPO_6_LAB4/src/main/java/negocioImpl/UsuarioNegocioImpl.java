package negocioImpl;

import daoImpl.UsuarioDaoImpl;
import entidad.Usuario;
import negocio.IUsuarioNegocio;

public class UsuarioNegocioImpl implements IUsuarioNegocio {
	
	private UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();	

	public UsuarioNegocioImpl(UsuarioDaoImpl usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	public UsuarioNegocioImpl() {
		
	}

	@Override
	public boolean altaUsuario(Usuario usuario) {
		
		boolean exito = usuarioDao.insertarUsuario(usuario); 
		
		return exito;
	}

}
