package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FormularioReservaActividades extends JFrame implements ActionListener {

    private JTextField txtIdReservaActividad, txtFechaReserva, txtFechaBaja;
    private JComboBox<Integer> cbIdEstadoReserva, cbIdCliente, cbIdActividad, cbIdHorarioActividad;
    private JButton btnGuardar;

    // Lista de reservas de actividades
    public static ArrayList<ReservaActividad> listaReservaActividades = new ArrayList<>();

    public FormularioReservaActividades() {
        setTitle("Gestión de Reserva de Actividades");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos
        agregarCampo(panel, gbc, 0, "ID Reserva Actividad:", txtIdReservaActividad = new JTextField(15));
        agregarCampo(panel, gbc, 1, "Fecha Reserva:", txtFechaReserva = new JTextField(15));
        agregarCampo(panel, gbc, 2, "Fecha Baja:", txtFechaBaja = new JTextField(15));

        // ComboBox de Estado de Reserva
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Estado Reserva:"), gbc);
        gbc.gridx = 1;
        cbIdEstadoReserva = new JComboBox<>();
        panel.add(cbIdEstadoReserva, gbc);

        // ComboBox de Clientes
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("ID Cliente:"), gbc);
        gbc.gridx = 1;
        cbIdCliente = new JComboBox<>();
        panel.add(cbIdCliente, gbc);

        // ComboBox de Actividades
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("ID Actividad:"), gbc);
        gbc.gridx = 1;
        cbIdActividad = new JComboBox<>();
        panel.add(cbIdActividad, gbc);

        // ComboBox de Horarios de Actividad
        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("ID Horario Actividad:"), gbc);
        gbc.gridx = 1;
        cbIdHorarioActividad = new JComboBox<>();
        panel.add(cbIdHorarioActividad, gbc);

        // Botón Guardar
        gbc.gridx = 0; gbc.gridy = 7;
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
        // Cargar IDs de Estado de Reserva
        for (EstadoReserva estado : FormularioEstadoReservas.listaEstados) {
            cbIdEstadoReserva.addItem(estado.getIdEstadoReserva());
        }

        // Cargar IDs de Clientes
        for (Cliente cliente : FormularioClientes.listaClientes) {
            cbIdCliente.addItem(cliente.getIdCliente());
        }

        // Cargar IDs de Actividades (Simulado)
        for (int i = 1; i <= 5; i++) {
            cbIdActividad.addItem(i);
        }

        // Cargar IDs de Horarios de Actividades (Simulado)
        for (int i = 1; i <= 5; i++) {
            cbIdHorarioActividad.addItem(i);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            try {
                int idReservaActividad = Integer.parseInt(txtIdReservaActividad.getText().trim());
                String fechaReserva = txtFechaReserva.getText().trim();
                String fechaBaja = txtFechaBaja.getText().trim();
                int idEstadoReserva = (int) cbIdEstadoReserva.getSelectedItem();
                int idCliente = (int) cbIdCliente.getSelectedItem();
                int idActividad = (int) cbIdActividad.getSelectedItem();
                int idHorarioActividad = (int) cbIdHorarioActividad.getSelectedItem();

                if (fechaReserva.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar una fecha de reserva.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Guardar reserva de actividad en la lista
                ReservaActividad nuevaReserva = new ReservaActividad(idReservaActividad, fechaReserva, fechaBaja, idEstadoReserva, idCliente, idActividad, idHorarioActividad);
                listaReservaActividades.add(nuevaReserva);
                JOptionPane.showMessageDialog(this, "Reserva de Actividad guardada exitosamente.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
