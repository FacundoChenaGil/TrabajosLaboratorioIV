package presentacion.controlador;

import presentacion.vista.VentanaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentacion.vista.PanelAgregarPersona;
import presentacion.vista.PanelModificarPersona;

public class Controlador implements ActionListener{
	private VentanaPrincipal ventana;
	private PanelAgregarPersona pnlAgregarPersona;
	private PanelModificarPersona pnlModificarPersona;
	
	public Controlador(VentanaPrincipal ventana) {
		this.ventana = ventana;
		
		this.pnlAgregarPersona = new PanelAgregarPersona();
		this.pnlModificarPersona = new PanelModificarPersona();
		
		//Eventos del menu
		this.ventana.getMntmAgregar().addActionListener(a->EventoClickMenu_AbrirPanel_AgregarPersona(a));
		this.ventana.getMntmModificar().addActionListener(a->EventoClickMenu_AbrirPanel_ModificarPersona(a));
	}
	
	//EventoClickMenu abrir PanelAgregarPersona
	public void  EventoClickMenu_AbrirPanel_AgregarPersona(ActionEvent a)
	{		
		ventana.getContentPane().removeAll();
		ventana.getContentPane().add(pnlAgregarPersona);
		ventana.getContentPane().repaint();
		ventana.getContentPane().revalidate();
	}
	
	//EventoClickMenu abrir PanelModificarPersona
	public void  EventoClickMenu_AbrirPanel_ModificarPersona(ActionEvent a)
	{		
		ventana.getContentPane().removeAll();
		ventana.getContentPane().add(pnlModificarPersona);
		ventana.getContentPane().repaint();
		ventana.getContentPane().revalidate();
	}

	public void inicializar() {
		this.ventana.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
