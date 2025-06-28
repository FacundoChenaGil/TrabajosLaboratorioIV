package util;

import daoImpl.UsuarioDaoImpl;
import entidad.TipoUsuario;
import entidad.Usuario;

public class InicializarCuentas {
	 public static void main(String[] args) {
	        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
	        
	        crearUsuarioSiNoExiste(usuarioDao, "admin", "admin", 1, "administrador");
	    }
	    
	    private static void crearUsuarioSiNoExiste(UsuarioDaoImpl usuarioDao, String username, 
	                                             String password, int idTipoUsuario, 
	                                             String descripcionTipo) {
	        if (usuarioDao.getUsuarioPorNombre(username) == null) {
	            try {
	                // Crear nuevo usuario
	                Usuario usuario = new Usuario();
	                usuario.setUsuario(username);
	                
	                // Hashear la contraseña
	                String hashedPassword = PasswordHasher.hashPassword(password);
	                usuario.setClave(hashedPassword);
	                usuario.setActivo(true);
	                
	                // Configurar tipo de usuario
	                TipoUsuario tipo = new TipoUsuario();
	                tipo.setIdTipoUsuario(idTipoUsuario);
	                tipo.setDescripcion(descripcionTipo);
	                usuario.setTipoUsuario(tipo);
	                
	                // Insertar usuario
	                if (usuarioDao.insertarUsuario(usuario)) {
	                    System.out.println("Usuario " + descripcionTipo + " creado exitosamente");
	                    System.out.println("Usuario: " + username);
	                    System.out.println("Contraseña: " + password);
	                    System.out.println("--------------------------------");
	                } else {
	                    System.err.println("Error al insertar el usuario " + username);
	                }
	                
	            } catch (Exception e) {
	                System.err.println("Error al crear usuario " + username + ":");
	                e.printStackTrace();
	            }
	        } else {
	            System.out.println("El usuario " + username + " ya existe");
	        }
	    }
}
