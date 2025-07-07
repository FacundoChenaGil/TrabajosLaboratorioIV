package negocio;

import entidad.Usuario;

public interface IUsuarioNegocio {
	
	public boolean altaUsuario(Usuario usuario);
	public Usuario getUsuarioPorNombre(String usuario);

}
