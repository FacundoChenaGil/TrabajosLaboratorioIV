package interfaz;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class PanelAgregarPelicula extends JPanel {
	private static final long serialVersionUID = 1L;
	private JComboBox<Genero> cbGeneros;
	private ArrayList<Genero> generos;
	/**
	 * Create the panel.
	 */
	public PanelAgregarPelicula() {
		generos = new ArrayList<>();
		generos.add(new Genero("Seleccione un género"));
		generos.add(new Genero("Terror"));
		generos.add(new Genero("Acción"));
		generos.add(new Genero("Suspenso"));
		generos.add(new Genero("Romántica"));
	}

}
