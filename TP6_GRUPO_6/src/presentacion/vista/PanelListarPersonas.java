package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class PanelListarPersonas extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTable tablaPersonas;
	
	public PanelListarPersonas() {
		inicializar();
	}
	
	private void inicializar() {
		setBounds(100, 100, 550, 250);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		// Columnas y datos
		String[] columnas = {"Nombre", "Apellido", "DNI"};
		Object[][] datos = {
		    {"Juan", "Pérez", "12345678"},
		    {"Ana", "Gómez", "23456789"},
		    {"Luis", "Martínez", "34567890"}
		};

		// Crear la tabla con el modelo
		DefaultTableModel modelo = new DefaultTableModel(datos, columnas);
		tablaPersonas = new JTable(modelo);

		// Usar JScrollPane para que se vea correctamente
		JScrollPane scrollPane = new JScrollPane(tablaPersonas);
		scrollPane.setBounds(30, 30, 480, 150);
		add(scrollPane);
}
}
