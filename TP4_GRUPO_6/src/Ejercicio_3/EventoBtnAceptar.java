package Ejercicio_3;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class EventoBtnAceptar implements ActionListener {
	private JTextField txtHoras;
    private JRadioButton rdbtnWindows;
    private JRadioButton rdbtnMac;
    private JRadioButton rdbtnLinux;
    private JCheckBox cbxProgramacion;
    private JCheckBox cbxAdmin;
    private JCheckBox cbxDesigner;
	
	public EventoBtnAceptar(JTextField txtHoras, JRadioButton rdbtnWindows, JRadioButton rdbtnMac,
			JRadioButton rdbtnLinux, JCheckBox cbxProgramacion, JCheckBox cbxAdmin, JCheckBox cbxDesigner) {
		super();
		this.txtHoras = txtHoras;
		this.rdbtnWindows = rdbtnWindows;
		this.rdbtnMac = rdbtnMac;
		this.rdbtnLinux = rdbtnLinux;
		this.cbxProgramacion = cbxProgramacion;
		this.cbxAdmin = cbxAdmin;
		this.cbxDesigner = cbxDesigner;
		
		this.txtHoras.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (!Character.isDigit(c) && c != ':' && c != '\b') {
		            e.consume(); 
		        }
		    }
		});
		
		
	}
	
	private boolean ValidarEspecialidad() {
		if(!cbxProgramacion.isSelected() && !cbxAdmin.isSelected() && !cbxDesigner.isSelected()) {
			return false;
		}
		return true;
	}
	
	private String ObtenerEspecialidades() {
		String especialidades = "";

		if (cbxProgramacion.isSelected()) {
			especialidades += "Programación ";
		}
		if (cbxAdmin.isSelected()) {
			especialidades += "Administración ";
		}
		if (cbxDesigner.isSelected()) {
			especialidades += "Diseño Gráfico ";
		}
		return especialidades;
	}
	
	private boolean ValidarSistemaOperativo() {
		if(!rdbtnWindows.isSelected() && !rdbtnLinux.isSelected() && !rdbtnMac.isSelected()) {
			return false;
		}
		return true;
	}
	
	private String ObtenerSistemaOperativo() {
		if(rdbtnWindows.isSelected()) {
			return "Windows";
		}
		else if(rdbtnLinux.isSelected()) {
			return "Linux";
		}
		else {
			return "Mac";
		}
	}
	
	private boolean ValidarHoras() {
		String texto = txtHoras.getText().trim();
		if (texto.isEmpty()) {
			return false;
		}
		try {
			Integer.parseInt(texto);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	private String ObtenerHoras() {
		return txtHoras.getText().trim();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(!ValidarSistemaOperativo()) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un sistema operativo.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
				
		if(!ValidarEspecialidad()) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar una Especialidad.", "Error", 
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(!ValidarHoras()) {
			JOptionPane.showMessageDialog(null, "Debe ingresar las horas.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String so = ObtenerSistemaOperativo();
		String especialidades = ObtenerEspecialidades();
		String horas = ObtenerHoras();
		
		JOptionPane.showMessageDialog(null, so + " - " + especialidades + "- " + horas + " Hs", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
	}

}
