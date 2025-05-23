package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private JMenu mnPersonas;
	private JMenuItem mntmAgregar;
	private JMenuItem mntmModificar;
	private JMenuItem mntmEliminar;
	private JMenuItem mntmListar;
	
	
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 250);
		getContentPane().setLayout(new java.awt.BorderLayout());
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnPersonas = new JMenu("Personas");
		menuBar.add(mnPersonas);
		
		mntmAgregar = new JMenuItem("Agregar");
		mnPersonas.add(mntmAgregar);
		
		mntmModificar = new JMenuItem("Modificar");
		mnPersonas.add(mntmModificar);
		
		mntmEliminar = new JMenuItem("Eliminar");
		mnPersonas.add(mntmEliminar);
		
		mntmListar = new JMenuItem("Listar");
		mnPersonas.add(mntmListar);
	}
	
	public JMenuItem getMntmAgregar() {
	    return mntmAgregar;
	}

	public JMenuItem getMntmModificar() {
	    return mntmModificar;
	}

	public JMenuItem getMntmEliminar() {
	    return mntmEliminar;
	}

	public JMenuItem getMntmListar() {
	    return mntmListar;
	}
	
}
