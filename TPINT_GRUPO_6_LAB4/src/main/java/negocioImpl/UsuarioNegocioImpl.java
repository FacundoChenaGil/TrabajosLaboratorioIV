package negocioImpl;

import daoImpl.UsuarioDaoImpl;
import util.PasswordHasher;
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

	@Override
	public Usuario getUsuarioPorNombre(String usuario) {
		Usuario us = usuarioDao.getUsuarioPorNombre(usuario);
		
		return us;
	}

	@Override
	public boolean modificarClave(Usuario usuario) {
		
		String claveHasheada = PasswordHasher.hashPassword(usuario.getClave());
		usuario.setClave(claveHasheada);
		
		boolean actualizada = usuarioDao.modificarClave(usuario);
		return actualizada;
	}

	@Override
	public boolean existeUsuario(String usuario) {
		return usuarioDao.existeUsuario(usuario);
	}

}
