package negocioImpl;

import entidad.Persona;
import negocio.PersonaNegocio;
import dao.PersonaDao;
import daoImpl.PersonaDaoImpl;

public class PersonaNegocioImpl implements PersonaNegocio{

	PersonaDao pdao = new PersonaDaoImpl();
	@Override
	public boolean insert(Persona persona) {
		boolean estado = pdao.insert(persona);
		return estado;
	}
	@Override
	public boolean isExist(String dni) {
		boolean existe = pdao.isExist(dni);
		return existe;
	}


}
