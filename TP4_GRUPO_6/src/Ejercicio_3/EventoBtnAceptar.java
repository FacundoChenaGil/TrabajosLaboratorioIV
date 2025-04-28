package Ejercicio_3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class EventoBtnAceptar implements ActionListener {
	private JTextField txtHoras;
    private JRadioButton rdbtnWindows;
    private JRadioButton rdbtnMac;
    private JRadioButton rdbtnLinux;
    private JCheckBox cbxProgamacion;
    private JCheckBox cbxAdmin;
    private JCheckBox cbxDesigner;
	
	public EventoBtnAceptar(JTextField txtHoras, JRadioButton rdbtnWindows, JRadioButton rdbtnMac,
			JRadioButton rdbtnLinux, JCheckBox cbxProgamacion, JCheckBox cbxAdmin, JCheckBox cbxDesigner) {
		super();
		this.txtHoras = txtHoras;
		this.rdbtnWindows = rdbtnWindows;
		this.rdbtnMac = rdbtnMac;
		this.rdbtnLinux = rdbtnLinux;
		this.cbxProgamacion = cbxProgamacion;
		this.cbxAdmin = cbxAdmin;
		this.cbxDesigner = cbxDesigner;
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
