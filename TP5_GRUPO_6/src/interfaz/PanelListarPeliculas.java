package interfaz;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class PanelListarPeliculas extends JPanel {

	private static final long serialVersionUID = 1L;

	private JList<String> jListPeliculas;
	private DefaultListModel<String> modeloLista;

	public PanelListarPeliculas() {
		setLayout(new BorderLayout());

		modeloLista = new DefaultListModel<>();
		jListPeliculas = new JList<>(modeloLista);
		JScrollPane scroll = new JScrollPane(jListPeliculas);

		add(new JLabel("Películas guardadas:"), BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
	}


}

