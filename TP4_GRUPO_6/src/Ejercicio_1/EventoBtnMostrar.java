package Ejercicio_1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class EventoBtnMostrar implements ActionListener{
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtNacimiento;
	private JLabel lblMensaje;
	
	public EventoBtnMostrar(JTextField txtNombre, JTextField txtApellido,JTextField txtTelefono, JTextField txtNacimiento, JLabel lblMensaje) {
		this.txtNombre = txtNombre;
		this.txtApellido = txtApellido;
		this.txtTelefono = txtTelefono;
		this.txtNacimiento = txtNacimiento;
		
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
		
		//APELLIDO
		if(txtApellido.getText().isEmpty()) {
			txtApellido.setBackground(Color.red);
			camposIncompletos = true;
		}
		else {
			txtApellido.setBackground(Color.white);
		}
		
		//TELEFONO
				if(txtTelefono.getText().isEmpty()) {
					txtTelefono.setBackground(Color.red);
					camposIncompletos = true;
				}
				else {
					txtTelefono.setBackground(Color.white);
				}
				
	   //NACIMIENTO
	          if(txtNacimiento.getText().isEmpty()) {
					txtNacimiento.setBackground(Color.red);
					camposIncompletos = true;
				}
				else {
					txtNacimiento.setBackground(Color.white);
				}
	
		
	       // Si todos los campos están completos, mostrar mensaje
	  		
	          if (!camposIncompletos) {
	        		mensaje = "<html>Los datos ingresados fueron:<br>" +
	        		          "- Nombre: " + txtNombre.getText().trim() + "<br>" +
	        		          "- Apellido: " + txtApellido.getText().trim() + "<br>" +
	        		          "- Teléfono: " + txtTelefono.getText().trim() + "<br>" +
	        		          "- Fecha Nac.: " + txtNacimiento.getText().trim() + "</html>";

	        		// Limpiar campos
	        		txtNombre.setText("");
	        		txtApellido.setText("");
	        		txtTelefono.setText("");
	        		txtNacimiento.setText("");
	        	} else {
	        		mensaje = "Debe completar todos los campos";
	        	}

	        	// Mostrar mensaje
	        	lblMensaje.setText(mensaje);   
  }
}
