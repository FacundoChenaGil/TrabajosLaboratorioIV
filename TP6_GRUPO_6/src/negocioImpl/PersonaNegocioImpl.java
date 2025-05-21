package negocioImpl;

import entidad.Persona;
import negocio.PersonaNegocio;

import java.util.ArrayList;
import java.util.List;

import dao.PersonaDao;
import daoImpl.PersonaDaoImpl;

public class PersonaNegocioImpl implements PersonaNegocio{

	private PersonaDao pdao = new PersonaDaoImpl();
	
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
	@Override
	public List<Persona> readAll() {
		return pdao.readAll();
	}
	@Override
	public boolean update(Persona persona) {
		boolean estado = pdao.update(persona);
		return estado;
	}


}
