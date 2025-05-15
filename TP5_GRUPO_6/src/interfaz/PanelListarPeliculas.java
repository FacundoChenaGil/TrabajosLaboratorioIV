package interfaz;

import javax.swing.JPanel;

import clases.Pelicula;

import javax.swing.*;
import java.awt.*;


public class PanelListarPeliculas extends JPanel {

	private static final long serialVersionUID = 1L;

	private JScrollPane scroll;
	private JList<Pelicula> jListPeliculas;
	private DefaultListModel<Pelicula> modeloLista;

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
		jListPeliculas.setModel(this.modeloLista);
	}
}

