package main;

import dao.PersonaDao;
import daoImpl.PersonaDaoImpl;
import entidad.Persona;

public class Principal {

	public static void main(String[] args) {
		PersonaDao dao = new PersonaDaoImpl();
		
		dao.insert(new Persona("385533293", "Ernesto", "Riveiro"));
	}
}
