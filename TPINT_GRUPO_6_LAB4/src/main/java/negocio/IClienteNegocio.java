package negocio;

import java.util.List;

import entidad.Cliente;

public interface IClienteNegocio {
	public boolean existeClienteActivo(String dni);
	public Cliente obtenerClientePorDni(String dni);
	public boolean registrarCliente(Cliente cliente); 
	public String obtenerDNIPorUsuario(String usuario);
	public List<Cliente> obtenerClientes();
	
}
