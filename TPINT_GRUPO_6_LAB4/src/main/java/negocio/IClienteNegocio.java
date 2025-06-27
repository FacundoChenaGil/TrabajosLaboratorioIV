package negocio;

import entidad.Cliente;

public interface IClienteNegocio {
	public boolean existeClienteActivo(String dni);
	public Cliente obtenerClientePorDni(String dni);
}
