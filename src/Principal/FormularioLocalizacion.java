package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FormularioLocalizacion extends JFrame implements ActionListener {

    private JTextField txtIdLocalizacion, txtTipo, txtDescripcion;
    private JButton btnGuardar, btnMostrar;
    
    // Lista de localizaciones
    public static ArrayList<Localizacion> listaLocalizaciones = new ArrayList<>();

    public FormularioLocalizacion() {
        setTitle("Gestión de Localización");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("ID Localización:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtIdLocalizacion = new JTextField(15);
        panel.add(txtIdLocalizacion, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtTipo = new JTextField(15);
        panel.add(txtTipo, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtDescripcion = new JTextField(15);
        panel.add(txtDescripcion, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(this);
        panel.add(btnGuardar, gbc);

        gbc.gridy = 4;
        btnMostrar = new JButton("Mostrar Localizaciones");
        btnMostrar.addActionListener(this);
        panel.add(btnMostrar, gbc);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            try {
                int id = Integer.parseInt(txtIdLocalizacion.getText().trim());
                String tipo = txtTipo.getText().trim();
                String descripcion = txtDescripcion.getText().trim();

                if (tipo.isEmpty() || descripcion.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verificar si la localización ya existe
                for (Localizacion loc : listaLocalizaciones) {
                    if (loc.getIdLocalizacion() == id) {
                        JOptionPane.showMessageDialog(this, "La localización ya está registrada.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Agregar nueva localización
                listaLocalizaciones.add(new Localizacion(id, tipo));
                JOptionPane.showMessageDialog(this, "Localización guardada exitosamente.");
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnMostrar) {
            mostrarLocalizaciones();
        }
    }

    private void limpiarCampos() {
        txtIdLocalizacion.setText("");
        txtTipo.setText("");
        txtDescripcion.setText("");
    }

    private void mostrarLocalizaciones() {
        if (listaLocalizaciones.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay localizaciones registradas.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder lista = new StringBuilder("Localizaciones Registradas:\n");
        for (Localizacion loc : listaLocalizaciones) {
            lista.append("• ID: ").append(loc.getIdLocalizacion())
                 .append(" - Tipo: ").append(loc.getTipo()).append("\n");

        }

        JOptionPane.showMessageDialog(this, lista.toString(), "Lista de Localizaciones", JOptionPane.INFORMATION_MESSAGE);
    }
}
