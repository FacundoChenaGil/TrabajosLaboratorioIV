package dao;

import entidad.Cliente;

import java.time.LocalDate;
import java.util.List; // Para métodos que devuelvan listas de clientes

public interface IClienteDao {
	// Métodos para el ABML de clientes que el administrador usará
    public boolean agregarCliente(Cliente cliente);
    public int modificarCliente(Cliente cliente);
    public boolean eliminarCliente(String dni); // Baja lógica

    // Métodos de búsqueda y listado
    public Cliente obtenerClientePorDni(String dni);
    public List<Cliente> obtenerTodosLosClientes();
    public List<Cliente> obtenerClientesPaginado(int offset, int limit, String busqueda, String filtroProvincia); // Con paginación y filtros
    public int contarClientes(String busqueda, String filtroProvincia); // Para la paginación
    
    //ExisteClienteActivo
    public boolean existeClienteActivo(String dni);
    
    // Métodos adicionales si los necesitas para la lógica del negocio
    public Cliente obtenerClientePorUsuario(String usuario); // Para cuando un cliente inicia sesión
    public boolean existeCorreoElectronico(String correo); // Para validar correo único
    
    
    public String obtenerDNIPorUsuario(String usuario);
    
    List<Cliente> obtenerClientesNuevosEntreFechas(LocalDate desde, LocalDate hasta); // Para reporte
    public List<Cliente> buscarClientesPorNombreApellidoUsuario(String texto); // Para reporte
    
}
