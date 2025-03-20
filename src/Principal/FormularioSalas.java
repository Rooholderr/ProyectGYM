package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FormularioSalas extends JFrame implements ActionListener {

    private JTextField txtIdSala, txtNombreSala, txtDescripcionSala, txtIdLocalizacionSala;
    private JButton btnGuardar, btnMostrar;

    // Lista de salas
    public static ArrayList<Sala> listaSalas = new ArrayList<>();

    public FormularioSalas() {
        setTitle("Gestión de Salas");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("ID Sala:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtIdSala = new JTextField(15);
        panel.add(txtIdSala, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Nombre Sala:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtNombreSala = new JTextField(15);
        panel.add(txtNombreSala, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtDescripcionSala = new JTextField(15);
        panel.add(txtDescripcionSala, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("ID Localización Sala:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtIdLocalizacionSala = new JTextField(15);
        panel.add(txtIdLocalizacionSala, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(this);
        panel.add(btnGuardar, gbc);

        gbc.gridy = 5;
        btnMostrar = new JButton("Mostrar Salas");
        btnMostrar.addActionListener(this);
        panel.add(btnMostrar, gbc);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            try {
                int idSala = Integer.parseInt(txtIdSala.getText().trim());
                String nombreSala = txtNombreSala.getText().trim();
                String descripcionSala = txtDescripcionSala.getText().trim();
                int idLocalizacionSala = Integer.parseInt(txtIdLocalizacionSala.getText().trim());

                if (nombreSala.isEmpty() || descripcionSala.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verificar si la sala ya existe
                for (Sala sala : listaSalas) {
                    if (sala.getIdSala() == idSala) {
                        JOptionPane.showMessageDialog(this, "La sala ya está registrada.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Agregar nueva sala
                listaSalas.add(new Sala(idSala, nombreSala, descripcionSala, idLocalizacionSala));
                JOptionPane.showMessageDialog(this, "Sala guardada exitosamente.");
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Los ID deben ser números enteros.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnMostrar) {
            mostrarSalas();
        }
    }

    private void limpiarCampos() {
        txtIdSala.setText("");
        txtNombreSala.setText("");
        txtDescripcionSala.setText("");
        txtIdLocalizacionSala.setText("");
    }

    private void mostrarSalas() {
        if (listaSalas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay salas registradas.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder lista = new StringBuilder("Salas Registradas:\n");
        for (Sala sala : listaSalas) {
            lista.append("• ID: ").append(sala.getIdSala())
                 .append(" - Nombre: ").append(sala.getNombreSala())
                 .append(" - Descripción: ").append(sala.getDescripcionSala())
                 .append(" - ID Localización: ").append(sala.getIdLocalizacionSala()).append("\n");
        }

        JOptionPane.showMessageDialog(this, lista.toString(), "Lista de Salas", JOptionPane.INFORMATION_MESSAGE);
    }
}
