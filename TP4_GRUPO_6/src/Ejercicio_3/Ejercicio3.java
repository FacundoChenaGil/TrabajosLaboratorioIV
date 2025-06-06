package Ejercicio_3;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ejercicio3 extends JFrame {

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private ButtonGroup bgSO = new ButtonGroup();
    private JTextField txtHoras;
    private JRadioButton rdbtnWindows;
    private JRadioButton rdbtnMac;
    private JRadioButton rdbtnLinux;
    private JCheckBox cbxProgamacion;
    private JCheckBox cbxAdmin;
    private JCheckBox cbxDesigner;
    private JButton btnAceptar;
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejercicio3 frame = new Ejercicio3();
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
	public Ejercicio3() {
		setTitle("Seleccion multiple");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 250, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlSO = new JPanel();
		pnlSO.setBounds(20, 22, 392, 58);
		contentPane.add(pnlSO);
		pnlSO.setLayout(null);
		
		JLabel lblSO = new JLabel("Elige un sistema operativo");
		lblSO.setBounds(43, 21, 122, 14);
		pnlSO.add(lblSO);
		
		rdbtnWindows = new JRadioButton("Windows");
		rdbtnWindows.setBounds(171, 17, 75, 23);
		pnlSO.add(rdbtnWindows);
		bgSO.add(rdbtnWindows);
		
		rdbtnMac = new JRadioButton("Mac");
		rdbtnMac.setBounds(248, 17, 50, 23);
		pnlSO.add(rdbtnMac);
		bgSO.add(rdbtnMac);
		
		rdbtnLinux = new JRadioButton("Linux");
		rdbtnLinux.setBounds(300, 17, 63, 23);
		pnlSO.add(rdbtnLinux);
		bgSO.add(rdbtnLinux);
		
		JPanel pnlEspecialidad = new JPanel();
		pnlEspecialidad.setLayout(null);
		pnlEspecialidad.setBounds(20, 91, 392, 106);
		contentPane.add(pnlEspecialidad);
		
		JLabel lblEspecialidad = new JLabel("Elige una especialidad");
		lblEspecialidad.setBounds(43, 45, 122, 14);
		pnlEspecialidad.add(lblEspecialidad);
		
		cbxProgamacion = new JCheckBox("Programación");
		cbxProgamacion.setBounds(171, 17, 137, 23);
		pnlEspecialidad.add(cbxProgamacion);
		
		cbxAdmin = new JCheckBox("Administración");
		cbxAdmin.setBounds(171, 41, 137, 23);
		pnlEspecialidad.add(cbxAdmin);
		
		cbxDesigner = new JCheckBox("Diseño Gráfico");
		cbxDesigner.setBounds(171, 64, 137, 23);
		pnlEspecialidad.add(cbxDesigner);
		
		JLabel lblHoras = new JLabel("Cantidad de horas en el computador:");
		lblHoras.setBounds(65, 218, 187, 14);
		contentPane.add(lblHoras);
		
		txtHoras = new JTextField();
		txtHoras.setBounds(261, 215, 86, 20);
		contentPane.add(txtHoras);
		txtHoras.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new EventoBtnAceptar(txtHoras, rdbtnWindows, rdbtnMac,
			rdbtnLinux, cbxProgamacion, cbxAdmin, cbxDesigner));
		btnAceptar.setBounds(172, 258, 89, 23);
		contentPane.add(btnAceptar);
	}
}
