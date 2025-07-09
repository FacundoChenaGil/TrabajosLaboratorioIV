package negocio;

import entidad.Usuario;

public interface IUsuarioNegocio {
	
	public boolean altaUsuario(Usuario usuario);
	public Usuario getUsuarioPorNombre(String usuario);
	public boolean modificarClave(Usuario usuario);
	public boolean existeUsuario(String usuario);
}
