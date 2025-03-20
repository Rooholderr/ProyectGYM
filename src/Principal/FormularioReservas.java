package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FormularioReservas extends JFrame implements ActionListener {

    private JTextField txtIdReserva, txtFechaReserva;
    private JComboBox<Integer> cbIdSala, cbIdCliente, cbIdHorarioReserva, cbIdEstadoReserva;
    private JButton btnGuardar;

    // Lista de reservas
    public static ArrayList<Reserva> listaReservas = new ArrayList<>();

    public FormularioReservas() {
        setTitle("Gestión de Reservas");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos
        agregarCampo(panel, gbc, 0, "ID Reserva:", txtIdReserva = new JTextField(15));
        agregarCampo(panel, gbc, 1, "Fecha Reserva:", txtFechaReserva = new JTextField(15));

        // ComboBox de Salas
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("ID Sala:"), gbc);
        gbc.gridx = 1;
        cbIdSala = new JComboBox<>();
        panel.add(cbIdSala, gbc);

        // ComboBox de Clientes
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("ID Cliente:"), gbc);
        gbc.gridx = 1;
        cbIdCliente = new JComboBox<>();
        panel.add(cbIdCliente, gbc);

        // ComboBox de Horarios de Actividades
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("ID Horario Reserva:"), gbc);
        gbc.gridx = 1;
        cbIdHorarioReserva = new JComboBox<>();
        panel.add(cbIdHorarioReserva, gbc);

        // ComboBox de Estado de Reserva
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Estado Reserva:"), gbc);
        gbc.gridx = 1;
        cbIdEstadoReserva = new JComboBox<>();
        panel.add(cbIdEstadoReserva, gbc);

        // Botón Guardar
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(this);
        panel.add(btnGuardar, gbc);

        cargarDatos();

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

    // Cargar IDs de otras tablas
    private void cargarDatos() {
        // Cargar IDs de Salas (Simulado)
        for (int i = 1; i <= 5; i++) {
            cbIdSala.addItem(i);
        }

        // Cargar IDs de Clientes (Simulado)
        for (Cliente cliente : FormularioClientes.listaClientes) {
            cbIdCliente.addItem(cliente.getIdCliente());
        }

        // Cargar IDs de Horarios de Actividades (Simulado)
        for (int i = 1; i <= 5; i++) {
            cbIdHorarioReserva.addItem(i);
        }

        // Cargar IDs de Estado de Reserva
        for (EstadoReserva estado : FormularioEstadoReservas.listaEstados) {
            cbIdEstadoReserva.addItem(estado.getIdEstadoReserva());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            try {
                int idReserva = Integer.parseInt(txtIdReserva.getText().trim());
                String fechaReserva = txtFechaReserva.getText().trim();
                int idSala = (int) cbIdSala.getSelectedItem();
                int idCliente = (int) cbIdCliente.getSelectedItem();
                int idHorarioReserva = (int) cbIdHorarioReserva.getSelectedItem();
                int idEstadoReserva = (int) cbIdEstadoReserva.getSelectedItem();

                if (fechaReserva.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar una fecha de reserva.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Guardar reserva en la lista
                Reserva nuevaReserva = new Reserva(idReserva, idSala, idCliente, fechaReserva, idHorarioReserva, idEstadoReserva);
                listaReservas.add(nuevaReserva);
                JOptionPane.showMessageDialog(this, "Reserva guardada exitosamente.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
