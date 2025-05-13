package interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class PanelAgregarPelicula extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel lbl_IdPelicula;
	private JLabel lbl_ContadorId;
	private JLabel lbl_Nombre;
	private JTextField txt_Nombre;
	private JLabel lbl_Generos;
	private JComboBox<Genero> cb_Generos;
	private ArrayList<Genero> generos;
	
	
	public PanelAgregarPelicula() {
		generos = new ArrayList<>();
		generos.add(new Genero("Terror"));
		generos.add(new Genero("Acción"));
		generos.add(new Genero("Suspenso"));
		generos.add(new Genero("Romántica"));
		dibujarControles();
	}
	
	public void dibujarControles() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{129, 46, 53, 145, 0};
		gbl_contentPane.rowHeights = new int[]{50, 14, 40, 0, 20, 44, 22, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gbl_contentPane);
		
		lbl_IdPelicula = new JLabel("ID");
		GridBagConstraints gbc_lbl_IdPelicula = new GridBagConstraints();
		gbc_lbl_IdPelicula.anchor = GridBagConstraints.CENTER;
		gbc_lbl_IdPelicula.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_IdPelicula.gridx = 1;
		gbc_lbl_IdPelicula.gridy = 1;
		add(lbl_IdPelicula, gbc_lbl_IdPelicula);

		lbl_ContadorId = new JLabel(Integer.toString(Pelicula.getContadorID()));
		GridBagConstraints gbc_lbl_ContadorId = new GridBagConstraints();
		gbc_lbl_ContadorId.anchor = GridBagConstraints.CENTER;
		gbc_lbl_ContadorId.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_ContadorId.gridx = 2;
		gbc_lbl_ContadorId.gridy = 1;
		add(lbl_ContadorId, gbc_lbl_ContadorId);
		
		lbl_Nombre = new JLabel("Nombre");
		GridBagConstraints gbc_lbl_Nombre = new GridBagConstraints();
		gbc_lbl_Nombre.anchor = GridBagConstraints.CENTER;
		gbc_lbl_Nombre.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Nombre.gridx = 1;
		gbc_lbl_Nombre.gridy = 2;
		add(lbl_Nombre, gbc_lbl_Nombre);
		
		txt_Nombre = new JTextField();
		GridBagConstraints gbc_txt_Nombre = new GridBagConstraints();
		gbc_txt_Nombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Nombre.insets = new Insets(0, 0, 5, 0);
		gbc_txt_Nombre.gridx = 2;
		gbc_txt_Nombre.gridy = 2;
		add(txt_Nombre, gbc_txt_Nombre);
		txt_Nombre.setColumns(10);
		
		lbl_Generos = new JLabel("Género");
		GridBagConstraints gbc_lbl_Generos = new GridBagConstraints();
		gbc_lbl_Generos.anchor = GridBagConstraints.CENTER;
		gbc_lbl_Generos.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Generos.gridx = 1;
		gbc_lbl_Generos.gridy = 3;
		add(lbl_Generos, gbc_lbl_Generos);
		
		cb_Generos = new JComboBox<Genero>();
		GridBagConstraints gbc_cb_Generos = new GridBagConstraints();
		gbc_cb_Generos.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb_Generos.insets = new Insets(0, 0, 5, 0);
		gbc_cb_Generos.gridx = 2;
		gbc_cb_Generos.gridy = 3;
		add(cb_Generos, gbc_cb_Generos);
		
		JButton btn_Aceptar = new JButton("Aceptar");
		btn_Aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txt_Nombre.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Complete el campo nombre.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		GridBagConstraints gbc_btn_Aceptar = new GridBagConstraints();
		gbc_btn_Aceptar.anchor = GridBagConstraints.CENTER;
		gbc_btn_Aceptar.insets = new Insets(0, 0, 5, 0);
		gbc_btn_Aceptar.gridx = 2;
		gbc_btn_Aceptar.gridy = 5;
		add(btn_Aceptar, gbc_btn_Aceptar);
	}

}
