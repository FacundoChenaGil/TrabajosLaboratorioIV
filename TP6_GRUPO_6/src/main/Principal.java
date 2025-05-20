package main;

import presentacion.controlador.Controlador;
import presentacion.vista.VentanaPrincipal;
import negocio.PersonaNegocio;
import negocioImpl.PersonaNegocioImpl;
import dao.PersonaDao;
import daoImpl.PersonaDaoImpl;
import entidad.Persona;

public class Principal {

	public static void main(String[] args) {
		//PersonaDao dao = new PersonaDaoImpl();
		
		//dao.insert(new Persona("11111111", "Justina", "Leon"));
		VentanaPrincipal ventana = new VentanaPrincipal();
		PersonaNegocio pNeg = new PersonaNegocioImpl();
        Controlador controlador = new Controlador(ventana, pNeg);
        controlador.inicializar();
	}
}
