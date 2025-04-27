package Ejercicio_3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class EventoBtnAceptar implements ActionListener {
	private JRadioButton rdbtnWindows;
	private JRadioButton rdbtnMac;
	private JRadioButton rdbtnLinux;
	
	public EventoBtnAceptar(JRadioButton windows, JRadioButton mac, JRadioButton linux) {
		this.rdbtnWindows = windows;
		this.rdbtnMac = mac;
		this.rdbtnLinux = linux;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//SISTEMA OPERATIVO
		String so = "";
				
		if(rdbtnWindows.isSelected()) {
			so = "Windows";
		}
		else if(rdbtnLinux.isSelected()) {
			so = "Linux";
		}
		else if(rdbtnMac.isSelected()) {
			so = "Mac";
		}
		else {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un sistema operativo.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
	}

}
