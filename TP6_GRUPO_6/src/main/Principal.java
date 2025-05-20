package main;

import dao.PersonaDao;
import daoImpl.PersonaDaoImpl;
import entidad.Persona;

public class Principal {

	public static void main(String[] args) {
		PersonaDao dao = new PersonaDaoImpl();
		
		dao.insert(new Persona("11111111", "Justina", "Leon"));
	}
}
