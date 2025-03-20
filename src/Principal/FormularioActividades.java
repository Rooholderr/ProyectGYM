package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FormularioActividades extends JFrame implements ActionListener {

    private JTextField txtIdActividad, txtNombreActividad, txtDescripcionActividad, txtIdLocalizacion, txtIdEntrenador;
    private JButton btnGuardar, btnMostrar;

    // Lista de actividades
    public static ArrayList<Actividad> listaActividades = new ArrayList<>();

    public FormularioActividades() {
        setTitle("Gestión de Actividades");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("ID Actividad:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtIdActividad = new JTextField(15);
        panel.add(txtIdActividad, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Nombre Actividad:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtNombreActividad = new JTextField(15);
        panel.add(txtNombreActividad, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtDescripcionActividad = new JTextField(15);
        panel.add(txtDescripcionActividad, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("ID Localización:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtIdLocalizacion = new JTextField(15);
        panel.add(txtIdLocalizacion, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("ID Entrenador:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtIdEntrenador = new JTextField(15);
        panel.add(txtIdEntrenador, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(this);
        panel.add(btnGuardar, gbc);

        gbc.gridy = 6;
        btnMostrar = new JButton("Mostrar Actividades");
        btnMostrar.addActionListener(this);
        panel.add(btnMostrar, gbc);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            try {
                int idActividad = Integer.parseInt(txtIdActividad.getText().trim());
                String nombreActividad = txtNombreActividad.getText().trim();
                String descripcionActividad = txtDescripcionActividad.getText().trim();
                int idLocalizacion = Integer.parseInt(txtIdLocalizacion.getText().trim());
                int idEntrenador = Integer.parseInt(txtIdEntrenador.getText().trim());

                if (nombreActividad.isEmpty() || descripcionActividad.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verificar si la actividad ya existe
                for (Actividad act : listaActividades) {
                    if (act.getIdActividad() == idActividad) {
                        JOptionPane.showMessageDialog(this, "La actividad ya está registrada.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Agregar nueva actividad
                listaActividades.add(new Actividad(idActividad, nombreActividad, descripcionActividad, idLocalizacion, idEntrenador));
                JOptionPane.showMessageDialog(this, "Actividad guardada exitosamente.");
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Los ID deben ser números enteros.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnMostrar) {
            mostrarActividades();
        }
    }

    private void limpiarCampos() {
        txtIdActividad.setText("");
        txtNombreActividad.setText("");
        txtDescripcionActividad.setText("");
        txtIdLocalizacion.setText("");
        txtIdEntrenador.setText("");
    }

    private void mostrarActividades() {
        if (listaActividades.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay actividades registradas.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder lista = new StringBuilder("Actividades Registradas:\n");
        for (Actividad act : listaActividades) {
            lista.append("• ID: ").append(act.getIdActividad())
                 .append(" - Nombre: ").append(act.getNombreActividad())
                 .append(" - Descripción: ").append(act.getDescripcionActividad())
                 .append(" - ID Localización: ").append(act.getIdLocalizacion())
                 .append(" - ID Entrenador: ").append(act.getIdEntrenador()).append("\n");
        }

        JOptionPane.showMessageDialog(this, lista.toString(), "Lista de Actividades", JOptionPane.INFORMATION_MESSAGE);
    }
}
