package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FormularioEntrenadores extends JFrame implements ActionListener {

    private JTextField txtIdEntrenador, txtNombre, txtApellido, txtTelefono, txtCorreo;
    private JButton btnGuardar, btnMostrar;

    // Lista de entrenadores
    public static ArrayList<Entrenador> listaEntrenadores = new ArrayList<>();

    public FormularioEntrenadores() {
        setTitle("Gestión de Entrenadores");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("ID Entrenador:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtIdEntrenador = new JTextField(15);
        panel.add(txtIdEntrenador, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtNombre = new JTextField(15);
        panel.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Apellido:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtApellido = new JTextField(15);
        panel.add(txtApellido, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtTelefono = new JTextField(15);
        panel.add(txtTelefono, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Correo Electrónico:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtCorreo = new JTextField(15);
        panel.add(txtCorreo, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(this);
        panel.add(btnGuardar, gbc);

        gbc.gridy = 6;
        btnMostrar = new JButton("Mostrar Entrenadores");
        btnMostrar.addActionListener(this);
        panel.add(btnMostrar, gbc);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            try {
                int idEntrenador = Integer.parseInt(txtIdEntrenador.getText().trim());
                String nombre = txtNombre.getText().trim();
                String apellido = txtApellido.getText().trim();
                String telefono = txtTelefono.getText().trim();
                String correo = txtCorreo.getText().trim();

                if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verificar si el entrenador ya existe
                for (Entrenador ent : listaEntrenadores) {
                    if (ent.getIdEntrenador() == idEntrenador) {
                        JOptionPane.showMessageDialog(this, "El ID de entrenador ya está registrado.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Agregar entrenador a la lista
                listaEntrenadores.add(new Entrenador(idEntrenador, nombre, apellido, telefono, correo));
                JOptionPane.showMessageDialog(this, "Entrenador guardado exitosamente.");
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnMostrar) {
            mostrarEntrenadores();
        }
    }

    private void limpiarCampos() {
        txtIdEntrenador.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
    }

    private void mostrarEntrenadores() {
        if (listaEntrenadores.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay entrenadores registrados.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder lista = new StringBuilder("Entrenadores Registrados:\n");
        for (Entrenador ent : listaEntrenadores) {
            lista.append("• ID: ").append(ent.getIdEntrenador())
                 .append(" - Nombre: ").append(ent.getNombre()).append(" ").append(ent.getApellido())
                 .append(" - Tel: ").append(ent.getTelefono())
                 .append(" - Correo: ").append(ent.getCorreo()).append("\n");
        }

        JOptionPane.showMessageDialog(this, lista.toString(), "Lista de Entrenadores", JOptionPane.INFORMATION_MESSAGE);
    }
}
