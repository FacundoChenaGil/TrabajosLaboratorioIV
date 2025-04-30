package Ejercicio_1;

import java.awt.EventQueue;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Ejercicio1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtNacimiento;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejercicio1 frame = new Ejercicio1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ejercicio1() {
		setTitle("Contactos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 250, 400, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(42, 24, 88, 30);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(150, 31, 174, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblApellido.setBounds(42, 65, 88, 30);
		contentPane.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(150, 72, 174, 20);
		contentPane.add(txtApellido);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTelefono.setBounds(42, 106, 88, 30);
		contentPane.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(150, 113, 174, 20);
		contentPane.add(txtTelefono);
		
		JLabel lblNacimiento = new JLabel("Fecha Nac.");
		lblNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNacimiento.setBounds(42, 147, 88, 30);
		contentPane.add(lblNacimiento);
				
		txtNacimiento = new JTextField();
		txtNacimiento.setColumns(10);
		txtNacimiento.setBounds(150, 154, 174, 20);
		contentPane.add(txtNacimiento);
		
	
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.setBounds(42, 219, 300, 60);
		contentPane.add(btnMostrar);
		
		validarCampos.soloLetras(txtNombre);
		validarCampos.soloLetras(txtApellido);
		validarCampos.soloNumeros(txtTelefono);
		
		
		JLabel lblSalida = new JLabel("Los datos ingresados fueron:");
		lblSalida.setBounds(42, 219, 300, 60); 
		lblSalida.setVerticalAlignment(JLabel.TOP); 
		contentPane.add(lblSalida);
		
		btnMostrar.addActionListener(new EventoBtnMostrar(txtNombre, txtApellido, txtTelefono,txtNacimiento, lblSalida));
	}
}
