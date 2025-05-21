package presentacion.controlador;

import presentacion.vista.VentanaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import entidad.Persona;
import negocio.PersonaNegocio;
import presentacion.vista.PanelAgregarPersona;
import presentacion.vista.PanelModificarPersona;

public class Controlador implements ActionListener{
	private VentanaPrincipal ventana;
	private PanelAgregarPersona pnlAgregarPersona;
	private PanelModificarPersona pnlModificarPersona;
	private PersonaNegocio pNeg;
	
	public Controlador(VentanaPrincipal ventana, PersonaNegocio pNeg) {
		this.ventana = ventana;
		this.pNeg = pNeg;
		
		this.pnlAgregarPersona = new PanelAgregarPersona();
		this.pnlModificarPersona = new PanelModificarPersona();
		
		//Eventos del menu
		this.ventana.getMntmAgregar().addActionListener(a->EventoClickMenu_AbrirPanel_AgregarPersona(a));
		this.ventana.getMntmModificar().addActionListener(a->EventoClickMenu_AbrirPanel_ModificarPersona(a));
		
		//Evento click del btnAceptar (Panel Agregar Persona)
		this.pnlAgregarPersona.getBtnAceptar().addActionListener(a->EventoClickBoton_AgregarPesona_PanelAgregarPersonas(a));
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
		List<Persona> personas = pNeg.readAll();
		
		pnlModificarPersona.cargarPersonas(personas);
		
		ventana.getContentPane().removeAll();
		ventana.getContentPane().add(pnlModificarPersona);
		ventana.getContentPane().repaint();
		ventana.getContentPane().revalidate();
	}
	
	//Evento Click Boton Aceptar en PanelAgregarPersonas
		private void EventoClickBoton_AgregarPesona_PanelAgregarPersonas(ActionEvent a) {
			
			if(this.pnlAgregarPersona.getTxtNombre().getText().isEmpty() || 
			   this.pnlAgregarPersona.getTxtApellido().getText().isEmpty() || 
			   this.pnlAgregarPersona.getTxtDNI().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Complete todos los campos.");
			}
			else {
				String nombre = this.pnlAgregarPersona.getTxtNombre().getText().trim();
				String apellido = this.pnlAgregarPersona.getTxtApellido().getText().trim();
				String dni = this.pnlAgregarPersona.getTxtDNI().getText().trim();
				
				Persona persona = new Persona(dni, nombre, apellido);
				
				boolean existe = pNeg.isExist(persona.getDni());
				
				if(existe) {
					JOptionPane.showMessageDialog(null, "Ya existe una persona con el dni " + persona.getDni());
				}
				else {
					boolean estado = pNeg.insert(persona);
					
					if(estado==true)
					{
						JOptionPane.showMessageDialog(null, "Persona agregada exitosamente.");
						this.pnlAgregarPersona.getTxtNombre().setText("");
						this.pnlAgregarPersona.getTxtApellido().setText("");
						this.pnlAgregarPersona.getTxtDNI().setText("");
					}
					else {
						JOptionPane.showMessageDialog(null, "Persona no agregada.");
					}
				}
			}
		}

	public void inicializar() {
		this.ventana.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
