package Ejercicio_3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		// TODO Auto-generated method stub
		
	}

}
