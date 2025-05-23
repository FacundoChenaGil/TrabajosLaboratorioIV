package presentacion.vista;

import javax.swing.*;
import java.util.List;
import entidad.Persona;

public class PanelEliminarPersona extends JPanel {
    private JList<Persona> listaPersonas;
    private DefaultListModel<Persona> modeloLista;
    private JButton btnEliminar;
    private JLabel lblTitulo;

    public PanelEliminarPersona() {
        setLayout(null);

        int anchoPanel = 450;

        lblTitulo = new JLabel("Eliminar usuario");
        lblTitulo.setBounds(10, 10, 150, 25);
        add(lblTitulo);

        modeloLista = new DefaultListModel<>();
        listaPersonas = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaPersonas);
        scroll.setBounds(10, 40, 400, 150);
        add(scroll);

        btnEliminar = new JButton("Eliminar");
        int anchoBoton = 120;
        int xCentrado = (anchoPanel - anchoBoton) / 2;
        btnEliminar.setBounds(xCentrado, 210, anchoBoton, 30);
        add(btnEliminar);
    }

    public void cargarPersonas(List<Persona> personas) {
        modeloLista.clear();
        for (Persona p : personas) {
            modeloLista.addElement(p);
        }
    }

    public JList<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }
}