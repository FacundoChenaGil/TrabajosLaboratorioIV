package dao;



import java.sql.Connection;

import entidad.Usuario;

public interface IUsuarioDao {
	
	public Usuario getUsuarioPorNombre(String usuario);
	public boolean existeUsuario(String usuario);
	public boolean insertarUsuario(Usuario usuario);
	public boolean modificarClave(Usuario usuario);


}
