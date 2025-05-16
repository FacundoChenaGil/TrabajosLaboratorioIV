package interfaz;

import javax.swing.JPanel;

import clases.Pelicula;

import javax.swing.*;
import java.awt.*;
import java.util.TreeSet;


public class PanelListarPeliculas extends JPanel {

	private static final long serialVersionUID = 1L;

	private JScrollPane scroll;
	private JList<Pelicula> jListPeliculas;
	private DefaultListModel<Pelicula> modeloLista;
	
	public void ordenarPorTitulo() {
	   TreeSet<Pelicula> peliculas = new TreeSet<Pelicula>();
	    
	    for(int i=0; i<modeloLista.getSize(); i++) {
	    	peliculas.add(modeloLista.getElementAt(i));
	    }

	    modeloLista.clear();
	    for (Pelicula p : peliculas) {
	        modeloLista.addElement(p);
	    }

	}

	public PanelListarPeliculas() {
		setLayout(new BorderLayout());

		modeloLista = new DefaultListModel<>();
		jListPeliculas = new JList<>(modeloLista);
		scroll = new JScrollPane(jListPeliculas);

		add(new JLabel("Películas guardadas:"), BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
	}

	public void setDefaultListModel(DefaultListModel<Pelicula> listModel) {
		this.modeloLista = listModel;
		ordenarPorTitulo();
		jListPeliculas.setModel(this.modeloLista);
	}
}

