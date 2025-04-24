package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Ejercicio_1.Ejercicio1;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal frame = new PantallaPrincipal();
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
	public PantallaPrincipal() {
		setTitle("Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 250, 390, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGrupo = new JLabel("GRUPO NRO: 6");
		lblGrupo.setFont(new Font("Arial", Font.BOLD, 15));
		lblGrupo.setBounds(35, 21, 121, 33);
		contentPane.add(lblGrupo);
		
		JButton btnEjercicio1 = new JButton("Ejercicio 1");
		btnEjercicio1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ejercicio1 ej1 = new Ejercicio1();
				ej1.setVisible(true);
			}
		});
		btnEjercicio1.setFont(new Font("Arial", Font.PLAIN, 10));
		btnEjercicio1.setBounds(152, 64, 85, 21);
		contentPane.add(btnEjercicio1);
		
		JButton btnEjercicio2 = new JButton("Ejercicio 2");
		btnEjercicio2.setFont(new Font("Arial", Font.PLAIN, 10));
		btnEjercicio2.setBounds(152, 95, 85, 21);
		contentPane.add(btnEjercicio2);
		
		JButton btnEjercicio3 = new JButton("Ejercicio 3");
		btnEjercicio3.setFont(new Font("Arial", Font.PLAIN, 10));
		btnEjercicio3.setBounds(152, 126, 85, 21);
		contentPane.add(btnEjercicio3);
	}

}
