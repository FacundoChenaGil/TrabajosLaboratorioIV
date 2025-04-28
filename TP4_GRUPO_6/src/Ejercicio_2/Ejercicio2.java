package Ejercicio_2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JButton;

public class Ejercicio2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNota1;
	private JLabel lblNota2;
	private JTextField txtNota2;
	private JLabel lblNota3;
	private JTextField txtNota3;
	private JPanel contentPane;
	private JPanel pnlCondicion;
	private JLabel lblPromedio;
	private JTextField txtPromedio;
	private JLabel lblCondicion;
	private JTextField txtCondicion;
	private JButton btnCalcular;
	private JButton btnNuevo;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejercicio2 frame = new Ejercicio2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ejercicio2() {
		setTitle("Promedio");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 250, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlNotas = new JPanel();
		pnlNotas.setBounds(29, 22, 251, 180);
		pnlNotas.setBorder(new TitledBorder("Notas del estudiante"));
		contentPane.add(pnlNotas);
		pnlNotas.setLayout(null);
		
		JLabel lblNota1 = new JLabel("Nota 1:");
		lblNota1.setBounds(33, 38, 46, 14);
		pnlNotas.add(lblNota1);
		
		txtNota1 = new JTextField();
		txtNota1.setBounds(89, 35, 119, 20);
		pnlNotas.add(txtNota1);
		txtNota1.setColumns(10);
		
		lblNota2 = new JLabel("Nota 2:");
		lblNota2.setBounds(33, 69, 46, 14);
		pnlNotas.add(lblNota2);
		
		txtNota2 = new JTextField();
		txtNota2.setColumns(10);
		txtNota2.setBounds(89, 66, 119, 20);
		pnlNotas.add(txtNota2);
		
		lblNota3 = new JLabel("Nota 3:");
		lblNota3.setBounds(33, 99, 46, 14);
		pnlNotas.add(lblNota3);
		
		txtNota3 = new JTextField();
		txtNota3.setColumns(10);
		txtNota3.setBounds(89, 97, 119, 20);
		pnlNotas.add(txtNota3);
		
		JLabel lblTPS = new JLabel("TPS");
		lblTPS.setBounds(33, 127, 46, 14);
		pnlNotas.add(lblTPS);
		
		String[] tpsOpciones = {"Aprobado", "Desaprobado"};
		
		JComboBox<String> cbAprobacion = new JComboBox<>(tpsOpciones);
		cbAprobacion.setBounds(89, 124, 119, 22);
		pnlNotas.add(cbAprobacion);
		
		pnlCondicion = new JPanel();
		pnlCondicion.setLayout(null);
		pnlCondicion.setBounds(29, 215, 251, 116);
		pnlCondicion.setBorder(new TitledBorder("Notas del estudiante"));
		contentPane.add(pnlCondicion);
		
		lblPromedio = new JLabel("Promedio:");
		lblPromedio.setBounds(33, 38, 48, 14);
		pnlCondicion.add(lblPromedio);
		
		txtPromedio = new JTextField();
		txtPromedio.setColumns(10);
		txtPromedio.setBounds(89, 35, 119, 20);
		pnlCondicion.add(txtPromedio);
		
		lblCondicion = new JLabel("Condicion");
		lblCondicion.setBounds(33, 69, 46, 14);
		pnlCondicion.add(lblCondicion);
		
		txtCondicion = new JTextField();
		txtCondicion.setColumns(10);
		txtCondicion.setBounds(89, 66, 119, 20);
		pnlCondicion.add(txtCondicion);
		
		btnCalcular = new JButton("CALCULAR");
		btnCalcular.setBounds(308, 38, 99, 40);
		contentPane.add(btnCalcular);
		
		btnNuevo = new JButton("NUEVO");
		btnNuevo.setBounds(308, 90, 99, 40);
		contentPane.add(btnNuevo);
		
		btnSalir = new JButton("SALIR");
		btnSalir.setBounds(308, 142, 99, 40);
		contentPane.add(btnSalir);
	}
}
