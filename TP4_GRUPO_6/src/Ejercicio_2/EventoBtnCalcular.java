package Ejercicio_2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class EventoBtnCalcular implements ActionListener{
	private JTextField txtNota1;
	
	
	public EventoBtnCalcular(JTextField nota1) {
		this.txtNota1 = nota1;
	}
	
	public boolean notaValida(double nota) {
		if(nota >= 1 && nota <= 10) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			double n1 = Double.parseDouble(txtNota1.getText().trim());
			
			if(!notaValida(n1)) {
				throw new NumberFormatException();
			}
		}catch(NumberFormatException e1) {
			javax.swing.JOptionPane.showMessageDialog(null, "Ingrese una nota valida (números del 1 al 10).", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	
}
