package Ejercicio_2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class EventoBtnCalcular implements ActionListener{
	private JTextField txtNota1;
	private JTextField txtNota2;
	private JTextField txtNota3;
	private JComboBox<String> cbAprobacion;
	private JTextField txtPromedio;
	private JTextField txtCondicion;
	


	public EventoBtnCalcular(JTextField txtNota1, JTextField txtNota2, JTextField txtNota3,
			JComboBox<String> cbAprobacion, JTextField txtPromedio, JTextField txtCondicion) {
		super();
		this.txtNota1 = txtNota1;
		this.txtNota2 = txtNota2;
		this.txtNota3 = txtNota3;
		this.cbAprobacion = cbAprobacion;
		this.txtPromedio = txtPromedio;
		this.txtCondicion = txtCondicion;
	}

	private boolean notaValida(double nota) {
		if(nota >= 1 && nota <= 10) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean verificarNotaMenorASeis(double nota1, double nota2, double nota3) {
		
		if(nota1 < 6 || nota2 < 6 || nota3 < 6) {
			return true;
		}
		
		return false;
	}
	
	private boolean verificarPromocion(double nota1, double nota2, double nota3, boolean estado) {
		
		if(nota1 >= 8 && nota2 >= 8 && nota3 >= 8 && estado) {
			return true;
		}
		
		return false;
	}
	
	private boolean verificarTP(JComboBox<String> cb) {
		if (cb.getSelectedItem().equals("Aprobado")) {
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
			double n2 = Double.parseDouble(txtNota2.getText().trim());
			double n3 = Double.parseDouble(txtNota3.getText().trim());
			
			if(!notaValida(n1) || !notaValida(n2) || !notaValida(n3)) {
				throw new NumberFormatException();
			}

			double promedio = (n1 + n2 + n3) / 3;
			txtPromedio.setText(String.format("%.2f", promedio));
			
			boolean estado = verificarTP(cbAprobacion);

			if(!estado) {
				txtCondicion.setText("Libre");
			}
			else if (verificarNotaMenorASeis(n1, n2, n3)) {
				txtCondicion.setText("Libre");
			}
			else if (verificarPromocion(n1, n2, n3, estado)) {
				txtCondicion.setText("Promociona");
			}
			else {
				txtCondicion.setText("Regular");
			}
		}
		catch(NumberFormatException e1) {
			javax.swing.JOptionPane.showMessageDialog(null, "Ingrese una nota valida (números del 1 al 10).", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	
}
