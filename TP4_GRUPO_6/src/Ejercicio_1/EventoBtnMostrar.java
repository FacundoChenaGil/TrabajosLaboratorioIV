package Ejercicio_1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class EventoBtnMostrar implements ActionListener{
	private JTextField txtNombre;
	private JLabel lblMensaje;
	
	public EventoBtnMostrar(JTextField txtNombre, JLabel lblMensaje) {
		this.txtNombre = txtNombre;
		
		this.lblMensaje = lblMensaje;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean camposIncompletos = false;
		String mensaje;
		//NOMBRE
		if(txtNombre.getText().isEmpty()) {
			txtNombre.setBackground(Color.red);
			camposIncompletos = true;
		}
		else {
			txtNombre.setBackground(Color.white);
		}
		
		//Continuar...
		
		if(!camposIncompletos) {
			mensaje = "Los datos ingresados fueron: " +
	                  "- Nombre: " + txtNombre.getText().trim();
			txtNombre.setText("");
		}
		else {
			mensaje = "";
		}
		lblMensaje.setText(mensaje);
		
	}
	
}
