package presentacion.vista;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidad.Persona;

public class PanelListarPersonas extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTable tablaPersonas;
	
	public PanelListarPersonas() {
		inicializar();
	}
	
	public void cargarPersonas(List<Persona> personas) {
	    // Crear un modelo con columnas
		DefaultTableModel modelo = new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // Esto deshabilita la edición
	        }
	    };
	    
	    modelo.addColumn("Nombre");
	    modelo.addColumn("Apellido");
	    modelo.addColumn("DNI");

	    // Agregar las filas con los datos de las personas
	    for (Persona p : personas) {
	        Object[] fila = { p.getNombre(), p.getApellido(), p.getDni() };
	        modelo.addRow(fila);
	    }

	    // Asignar el modelo a la tabla
	    tablaPersonas.setModel(modelo);
	}
	
	private void inicializar() {
		setBounds(100, 100, 550, 250);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		// Crear la tabla con el modelo
		DefaultTableModel modelo = new DefaultTableModel();
		tablaPersonas = new JTable(modelo);

		// Usar JScrollPane para que se vea correctamente
		JScrollPane scrollPane = new JScrollPane(tablaPersonas);
		scrollPane.setBounds(30, 30, 480, 150);
		add(scrollPane);
}
}
