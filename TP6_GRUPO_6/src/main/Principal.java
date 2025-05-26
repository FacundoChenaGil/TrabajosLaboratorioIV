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
		VentanaPrincipal ventana = new VentanaPrincipal();
		PersonaNegocio pNeg = new PersonaNegocioImpl();
        Controlador controlador = new Controlador(ventana, pNeg);
        controlador.inicializar();
	}
}
