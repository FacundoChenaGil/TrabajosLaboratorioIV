package main;

import presentacion.controlador.Controlador;
import presentacion.vista.VentanaPrincipal;
import dao.PersonaDao;
import daoImpl.PersonaDaoImpl;
import entidad.Persona;

public class Principal {

	public static void main(String[] args) {
		//PersonaDao dao = new PersonaDaoImpl();
		
		//dao.insert(new Persona("11111111", "Justina", "Leon"));
		VentanaPrincipal ventana = new VentanaPrincipal();
        Controlador controlador = new Controlador(ventana);
        controlador.inicializar();
	}
}
