package Ejercicio_2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

public class EventoBotones implements ActionListener {
	
	 private JTextField txtNota1, txtNota2, txtNota3;
	    private JButton btnNuevo, btnSalir;
	    private JFrame ventana;

	    public EventoBotones(JTextField txtNota1, JTextField txtNota2, JTextField txtNota3,
	                               JButton btnNuevo, JButton btnSalir, JFrame ventana) {
	        this.txtNota1 = txtNota1;
	        this.txtNota2 = txtNota2;
	        this.txtNota3 = txtNota3;
	        this.btnNuevo = btnNuevo;
	        this.btnSalir = btnSalir;
	        this.ventana = ventana;
	    }

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        Object source = e.getSource();

	        if (source == btnNuevo) {
	            txtNota1.setText("");
	            txtNota2.setText("");
	            txtNota3.setText("");
	        } 
	        else if (source == btnSalir) {
	            ventana.dispose();  // Cierra solo esta ventana
	        }
	    }
	

}
