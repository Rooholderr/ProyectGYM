package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FormularioEstadoReservas extends JFrame implements ActionListener {

    private JTextField txtIdEstado, txtEstado;
    private JCheckBox chkActivo;
    private JButton btnGuardar;
    
    // Lista de estados de reserva
    public static ArrayList<EstadoReserva> listaEstados = new ArrayList<>();

    public FormularioEstadoReservas() {
        setTitle("Gestión de Estados de Reservas");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos
        agregarCampo(panel, gbc, 0, "ID Estado:", txtIdEstado = new JTextField(15));
        agregarCampo(panel, gbc, 1, "Estado:", txtEstado = new JTextField(15));

        // Checkbox Activo
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Activo:"), gbc);

        gbc.gridx = 1;
        chkActivo = new JCheckBox();
        panel.add(chkActivo, gbc);

        // Botón Guardar
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(this);
        panel.add(btnGuardar, gbc);

        add(panel);
        setVisible(true);
    }

    // Método para agregar campos al panel
    private void agregarCampo(JPanel panel, GridBagConstraints gbc, int y, String label, JTextField textField) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        panel.add(textField, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            try {
                int idEstado = Integer.parseInt(txtIdEstado.getText().trim());
                String estado = txtEstado.getText().trim();
                boolean activo = chkActivo.isSelected();

                if (estado.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar un estado.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Guardar estado de reserva en la lista
                EstadoReserva nuevoEstado = new EstadoReserva(idEstado, estado, activo);
                listaEstados.add(nuevoEstado);
                JOptionPane.showMessageDialog(this, "Estado de Reserva guardado exitosamente.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
