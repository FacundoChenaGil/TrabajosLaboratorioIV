package presentacion.vista;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import entidad.Persona;

public class PanelModificarPersona extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel lblSeleccionar;
	private JList<Persona> listaPersonas;
	private DefaultListModel<Persona> listModelPersonas;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDNI;
	private JButton btnModificar;
	
	public PanelModificarPersona() {
		inicializar();
	}
	
	public void cargarPersonas(List<Persona> personas) {
		listModelPersonas = new DefaultListModel<>();
		for(Persona p : personas) {
			listModelPersonas.addElement(p);
		}
		
		listaPersonas.setModel(listModelPersonas);
	}
	
	public JList<Persona> getListaPersonas() {
	    return listaPersonas;
	}
	
	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
	}

	public JTextField getTxtDNI() {
		return txtDNI;
	}

	private void inicializar() {
		setBounds(100, 100, 550, 250);
		setBorder(new EmptyBorder(5, 5, 5, 5));

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 100, 100, 100, 100, 0}; 
		gbl_contentPane.rowHeights = new int[]{0, 100, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gbl_contentPane);

		lblSeleccionar = new JLabel("Seleccione la persona que desea modificar:");
		lblSeleccionar.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblSeleccionar = new GridBagConstraints();
		gbc_lblSeleccionar.gridwidth = 4;
		gbc_lblSeleccionar.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccionar.gridx = 1;
		gbc_lblSeleccionar.gridy = 0;
		gbc_lblSeleccionar.anchor = GridBagConstraints.WEST;
		add(lblSeleccionar, gbc_lblSeleccionar);

		listaPersonas = new JList<Persona>();
		JScrollPane scrollPane = new JScrollPane(listaPersonas);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);

		txtNombre = new JTextField();
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.insets = new Insets(0, 0, 0, 5);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 1;
		gbc_txtNombre.gridy = 2;
		add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);

		
		txtApellido = new JTextField();
		GridBagConstraints gbc_txtApellido = new GridBagConstraints();
		gbc_txtApellido.insets = new Insets(0, 0, 0, 5);
		gbc_txtApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtApellido.gridx = 2;
		gbc_txtApellido.gridy = 2;
		add(txtApellido, gbc_txtApellido);
		txtApellido.setColumns(10);

		txtDNI = new JTextField();
		txtDNI.setEditable(false);
		GridBagConstraints gbc_txtDNI = new GridBagConstraints();
		gbc_txtDNI.insets = new Insets(0, 0, 0, 5);
		gbc_txtDNI.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDNI.gridx = 3;
		gbc_txtDNI.gridy = 2;
		add(txtDNI, gbc_txtDNI);
		txtDNI.setColumns(10);

		btnModificar = new JButton("Modificar");
		GridBagConstraints gbc_btnModificar = new GridBagConstraints();
		gbc_btnModificar.insets = new Insets(0, 0, 0, 0);
		gbc_btnModificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnModificar.gridx = 4;
		gbc_btnModificar.gridy = 2;
		add(btnModificar, gbc_btnModificar);
	}

	public JButton getBtnModificar() {
		return btnModificar;
	}

}
	
