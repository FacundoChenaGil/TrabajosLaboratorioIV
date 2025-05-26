package presentacion.vista;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import entidad.Persona;

public class PanelEliminarPersona extends JPanel {
    private JList<Persona> listaPersonas;
    private DefaultListModel<Persona> modeloLista;
    private JButton btnEliminar;
    private JLabel lblTitulo;

    public PanelEliminarPersona() {
    	setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Título
        lblTitulo = new JLabel("Eliminar usuario");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblTitulo, gbc);

        // Lista con scroll
        modeloLista = new DefaultListModel<>();
        listaPersonas = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaPersonas);
        scroll.setPreferredSize(new Dimension(400, 150));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 20, 10, 20);
        add(scroll, gbc);

        // Botón Eliminar
        btnEliminar = new JButton("Eliminar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnEliminar, gbc);
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