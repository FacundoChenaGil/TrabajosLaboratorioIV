package negocio;

import java.time.LocalDate;
import java.util.List;

import entidad.Cliente;

public interface IClienteNegocio {
	public boolean existeClienteActivo(String dni);
	public Cliente obtenerClientePorDni(String dni);
	public boolean registrarCliente(Cliente cliente); 
	public String obtenerDNIPorUsuario(String usuario);
	public List<Cliente> obtenerClientes();
	public int modificarCliente(Cliente cliente);
	public Cliente obtenerClientePorUsuario(String usuario);
	public boolean existeCorreoElectronico(String correo);
	public boolean eliminarCliente(String dni);
	public List<Cliente> obtenerClientesNuevosEntreFechas(LocalDate desde, LocalDate hasta); // Para Reporte
	public List<Cliente> buscarClientesPorNombreApellidoUsuario(String texto); // Para Reporte
	public List<Cliente> obtenerTodosLosClientes(); //Para Reporte
	public boolean existeCUIL(String cuil);
}
