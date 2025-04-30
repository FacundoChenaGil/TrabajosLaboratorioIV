package Ejercicio_1;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class validarCampos {
		 // Permite solo letras y espacios
	    public static void soloLetras(JTextField campo) {
	        campo.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyTyped(KeyEvent e) {
	                char c = e.getKeyChar();
	                if (!Character.isLetter(c) && c != ' ' && c != KeyEvent.VK_BACK_SPACE) {
	                    e.consume();
	                }
	            }
	        });
	    }
	
	    // Permite solo números
	    public static void soloNumeros(JTextField campo) {
	        campo.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyTyped(KeyEvent e) {
	                char c = e.getKeyChar();
	                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
	                    e.consume();
	                }
	            }
	        });
	    }
	

}
