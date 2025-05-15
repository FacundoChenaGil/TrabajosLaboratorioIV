package interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Pelicula;

public class Programa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnPeliculas;
	private JMenuItem mntmAgregar;
	private JMenuItem mntmListar;
	private static DefaultListModel<Pelicula> listModel;
	private PanelAgregarPelicula ap;
	private PanelListarPeliculas lp;
	
	public Programa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		listModel = new DefaultListModel<Pelicula>();
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnPeliculas = new JMenu("Peliculas");
		menuBar.add(mnPeliculas);
		
		mntmAgregar = new JMenuItem("Agregar");
		mntmAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				ap = new PanelAgregarPelicula();
				ap.setDefaultListModel(listModel);
				contentPane.add(ap);
				contentPane.repaint();
				contentPane.revalidate();
			}
		});
		mnPeliculas.add(mntmAgregar);
		
	    mntmListar = new JMenuItem("Listar");
	    mntmListar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            contentPane.removeAll();
	            lp = new PanelListarPeliculas(); 
	            lp.setDefaultListModel(listModel);
	            contentPane.add(lp);
	            contentPane.repaint();
	            contentPane.revalidate();
	        }
	    });
	    mnPeliculas.add(mntmListar);
	    
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(new BorderLayout());
	}

}
