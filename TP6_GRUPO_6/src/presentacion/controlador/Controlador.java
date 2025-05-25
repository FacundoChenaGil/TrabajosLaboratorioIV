package presentacion.controlador;

import presentacion.vista.VentanaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import entidad.Persona;
import negocio.PersonaNegocio;
import presentacion.vista.PanelAgregarPersona;
import presentacion.vista.PanelEliminarPersona;
import presentacion.vista.PanelModificarPersona;

public class Controlador implements ActionListener{
	private VentanaPrincipal ventana;
	private PanelAgregarPersona pnlAgregarPersona;
	private PanelModificarPersona pnlModificarPersona;
	private PanelEliminarPersona pnlEliminarPersona;
	private PersonaNegocio pNeg;
	
	public Controlador(VentanaPrincipal ventana, PersonaNegocio pNeg) {
		this.ventana = ventana;
		this.pNeg = pNeg;
		
		this.pnlAgregarPersona = new PanelAgregarPersona();
		this.pnlModificarPersona = new PanelModificarPersona();
		this.pnlEliminarPersona = new PanelEliminarPersona();  
		
		
		
		//Eventos del menu
		this.ventana.getMntmAgregar().addActionListener(a->EventoClickMenu_AbrirPanel_AgregarPersona(a));
		this.ventana.getMntmModificar().addActionListener(a->EventoClickMenu_AbrirPanel_ModificarPersona(a));
		 this.ventana.getMntmEliminar().addActionListener(a->EventoClickMenu_AbrirPanel_EliminarPersona(a)); 
		
		//Evento click del btnAceptar (Panel Agregar Persona)
		this.pnlAgregarPersona.getBtnAceptar().addActionListener(a->EventoClickBoton_AgregarPesona_PanelAgregarPersonas(a));
	
		// Evento al seleccionar un objeto de la lista de modificar
		EventoSeleccionlista_PanelModificarPersona();
		
		// Evento click del btnModificar (Panel Modificar Persona)
		this.pnlModificarPersona.getBtnModificar().addActionListener(a->EventoClickBoton_ModificarPersona_PanelModificarPersonas(a));
		
		this.pnlEliminarPersona.getBtnEliminar().addActionListener(a->EventoClickBoton_EliminarPersona_PanelEliminarPersona(a));
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
	
	public void EventoClickMenu_AbrirPanel_EliminarPersona(ActionEvent a) {
	    List<Persona> personas = pNeg.readAll();
	    pnlEliminarPersona.cargarPersonas(personas);  
	    ventana.getContentPane().removeAll();
	    ventana.getContentPane().add(pnlEliminarPersona);
	    ventana.getContentPane().repaint();
	    ventana.getContentPane().revalidate();
	}
	
	// Evento Click Boton Modificar en PanelModificarPersonas
	private void EventoClickBoton_ModificarPersona_PanelModificarPersonas(ActionEvent a) {
		if(this.pnlModificarPersona.getTxtNombre().getText().isEmpty() ||
			this.pnlModificarPersona.getTxtApellido().getText().isEmpty() ||
			this.pnlModificarPersona.getTxtDNI().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Complete todos los campos.");
		}
		
		String nombre = this.pnlModificarPersona.getTxtNombre().getText().trim();
		String apellido = this.pnlModificarPersona.getTxtApellido().getText().trim();
		String dni = this.pnlModificarPersona.getTxtDNI().getText().trim();
		
		Persona persona = new Persona(dni, nombre, apellido);
		
		boolean existe = pNeg.isExist(persona.getDni());
		
		if(existe) {
			boolean estado = pNeg.update(persona);
			
			if(estado==true)
			{
				JOptionPane.showMessageDialog(null, "Persona modificada exitosamente.");
				this.pnlModificarPersona.getTxtNombre().setText("");
				this.pnlModificarPersona.getTxtApellido().setText("");
				this.pnlModificarPersona.getTxtDNI().setText("");
				
				// Refrescar lista
				List<Persona> personas = pNeg.readAll();
				this.pnlModificarPersona.cargarPersonas(personas);
			}
			else {
				JOptionPane.showMessageDialog(null, "Persona no agregada.");
			}
		}
		
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
		
	// Evento Seleccion lista de panel modificar
		
	private void EventoSeleccionlista_PanelModificarPersona() {
		this.pnlModificarPersona.getListaPersonas().addListSelectionListener(e-> {
			if(!e.getValueIsAdjusting()) {
				Persona seleccionada = this.pnlModificarPersona.getListaPersonas().getSelectedValue();
				if(seleccionada != null) {
					this.pnlModificarPersona.getTxtNombre().setText(seleccionada.getNombre());
					this.pnlModificarPersona.getTxtApellido().setText(seleccionada.getApellido());
					this.pnlModificarPersona.getTxtDNI().setText(seleccionada.getDni());
					
				}
			}
		});
	}
	
	private void EventoClickBoton_EliminarPersona_PanelEliminarPersona(ActionEvent a) {
	    Persona seleccionada = pnlEliminarPersona.getListaPersonas().getSelectedValue();

	    if (seleccionada != null) {
	        int confirm = JOptionPane.showConfirmDialog(null,
	                "¿Estás seguro de eliminar a " + seleccionada + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

	        if (confirm == JOptionPane.YES_OPTION) {
	        	boolean eliminado = pNeg.delete(seleccionada);

	            if (eliminado) {
	                JOptionPane.showMessageDialog(null, "Persona eliminada correctamente.");

	                // Refrescar lista
	                List<Persona> personas = pNeg.readAll();
	                pnlEliminarPersona.cargarPersonas(personas);
	            } else {
	                JOptionPane.showMessageDialog(null, "Error al eliminar la persona.");
	            }
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Debe seleccionar una persona para eliminar.");
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
