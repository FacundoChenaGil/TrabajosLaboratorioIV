package interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clases.Genero;
import clases.Pelicula;


public class PanelAgregarPelicula extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel lbl_IdPelicula;
	private JLabel lbl_ContadorId;
	private JLabel lbl_Nombre;
	private JTextField txt_Nombre;
	private JLabel lbl_Generos;
	private JButton btn_Aceptar;
	private JComboBox<String> cb_Generos;
	private ArrayList<Genero> generos;
	private GridBagLayout gbl_contentPane;
	private GridBagConstraints gbc_lbl_IdPelicula;
	private GridBagConstraints gbc_lbl_ContadorId;
	private GridBagConstraints gbc_lbl_Nombre;
	private GridBagConstraints gbc_txt_Nombre;
	private GridBagConstraints gbc_lbl_Generos;
	private GridBagConstraints gbc_cb_Generos;
	private GridBagConstraints gbc_btn_Aceptar;
	private DefaultListModel<Pelicula> listModel;
	
	
	public PanelAgregarPelicula() {
		dibujarControles();
	}
	
	public void dibujarControles() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{129, 46, 53, 145, 0};
		gbl_contentPane.rowHeights = new int[]{50, 14, 40, 0, 20, 44, 22, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gbl_contentPane);
		
		lbl_IdPelicula = new JLabel("ID");
		gbc_lbl_IdPelicula = new GridBagConstraints();
		gbc_lbl_IdPelicula.anchor = GridBagConstraints.CENTER;
		gbc_lbl_IdPelicula.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_IdPelicula.gridx = 1;
		gbc_lbl_IdPelicula.gridy = 1;
		add(lbl_IdPelicula, gbc_lbl_IdPelicula);

		lbl_ContadorId = new JLabel(Integer.toString(Pelicula.getContadorID()));
		gbc_lbl_ContadorId = new GridBagConstraints();
		gbc_lbl_ContadorId.anchor = GridBagConstraints.CENTER;
		gbc_lbl_ContadorId.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_ContadorId.gridx = 2;
		gbc_lbl_ContadorId.gridy = 1;
		add(lbl_ContadorId, gbc_lbl_ContadorId);
		
		lbl_Nombre = new JLabel("Nombre");
		gbc_lbl_Nombre = new GridBagConstraints();
		gbc_lbl_Nombre.anchor = GridBagConstraints.CENTER;
		gbc_lbl_Nombre.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Nombre.gridx = 1;
		gbc_lbl_Nombre.gridy = 2;
		add(lbl_Nombre, gbc_lbl_Nombre);
		
		txt_Nombre = new JTextField();
		gbc_txt_Nombre = new GridBagConstraints();
		gbc_txt_Nombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Nombre.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Nombre.gridx = 2;
		gbc_txt_Nombre.gridy = 2;
		add(txt_Nombre, gbc_txt_Nombre);
		txt_Nombre.setColumns(10);
		
		lbl_Generos = new JLabel("Género");
		gbc_lbl_Generos = new GridBagConstraints();
		gbc_lbl_Generos.anchor = GridBagConstraints.CENTER;
		gbc_lbl_Generos.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Generos.gridx = 1;
		gbc_lbl_Generos.gridy = 3;
		add(lbl_Generos, gbc_lbl_Generos);
		
		generos = new ArrayList<>();
		generos.add(new Genero("Terror"));
		generos.add(new Genero("Acción"));
		generos.add(new Genero("Suspenso"));
		generos.add(new Genero("Romántica"));
		
		cb_Generos = new JComboBox<>();
		
		cb_Generos.addItem("Seleccione un Genero");
		for(Genero g : generos) {
			cb_Generos.addItem(g.getNombre());
		}
		
		cb_Generos.setSelectedIndex(0);
		
		gbc_cb_Generos = new GridBagConstraints();
		gbc_cb_Generos.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb_Generos.insets = new Insets(0, 0, 5, 5);
		gbc_cb_Generos.gridx = 2;
		gbc_cb_Generos.gridy = 3;
		add(cb_Generos, gbc_cb_Generos);
		
		btn_Aceptar = new JButton("Aceptar");
		btn_Aceptar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String nombre = txt_Nombre.getText().trim();
		        String generoSeleccionado = (String) cb_Generos.getSelectedItem();

		        if (nombre.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Debe ingresar un nombre para la película.");
		            return;
		        }

		        if (generoSeleccionado.equals("Seleccione un Genero")) {
		            JOptionPane.showMessageDialog(null, "Debe seleccionar un género válido.");
		            return;
		        }

		        // Si pasa las validaciones, se guarda la película
		        listModel.addElement(new Pelicula(nombre, new Genero(generoSeleccionado)));
		        lbl_ContadorId.setText(Integer.toString(Pelicula.getContadorID()));
		    }
		});
		gbc_btn_Aceptar = new GridBagConstraints();
		gbc_btn_Aceptar.anchor = GridBagConstraints.CENTER;
		gbc_btn_Aceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btn_Aceptar.gridx = 2;
		gbc_btn_Aceptar.gridy = 5;
		add(btn_Aceptar, gbc_btn_Aceptar);
	}
	
	public void setDefaultListModel(DefaultListModel<Pelicula> listModel)
	{
		this.listModel = listModel;
	}
}
