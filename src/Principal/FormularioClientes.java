package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FormularioClientes extends JFrame implements ActionListener {

    private JTextField txtIdCliente, txtNombre, txtApellidoPat, txtApellidoMat, txtDireccion, txtFechaNac, txtTelefono, txtCelular, txtFechaIngreso, txtCorreo, txtBalance, txtCuota;
    private JComboBox<String> cbStatusCliente, cbTipoCliente;
    private JButton btnGuardar;

    // Lista de clientes
    public static ArrayList<Cliente> listaClientes = new ArrayList<>();

    public FormularioClientes() {
        setTitle("Gestión de Clientes");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // Espaciado
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels y Campos de Texto
        agregarCampo(panel, gbc, 0, "ID Cliente:", txtIdCliente = new JTextField(20));
        agregarCampo(panel, gbc, 1, "Nombre Cliente:", txtNombre = new JTextField(20));
        agregarCampo(panel, gbc, 2, "Apellido Paterno:", txtApellidoPat = new JTextField(20));
        agregarCampo(panel, gbc, 3, "Apellido Materno:", txtApellidoMat = new JTextField(20));
        agregarCampo(panel, gbc, 4, "Dirección:", txtDireccion = new JTextField(20));
        agregarCampo(panel, gbc, 5, "Fecha Nacimiento:", txtFechaNac = new JTextField(20));
        agregarCampo(panel, gbc, 6, "Teléfono:", txtTelefono = new JTextField(20));
        agregarCampo(panel, gbc, 7, "Celular:", txtCelular = new JTextField(20));
        agregarCampo(panel, gbc, 8, "Fecha Ingreso:", txtFechaIngreso = new JTextField(20));
        agregarCampo(panel, gbc, 9, "Correo:", txtCorreo = new JTextField(20));
        agregarCampo(panel, gbc, 10, "Balance Cliente:", txtBalance = new JTextField(20));
        agregarCampo(panel, gbc, 11, "Valor Cuota Cliente:", txtCuota = new JTextField(20));

        // ComboBox para Status Cliente
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Status Cliente:"), gbc);

        gbc.gridx = 1;
        cbStatusCliente = new JComboBox<>(new String[]{"Activo", "Inactivo"});
        panel.add(cbStatusCliente, gbc);

        // ComboBox para Tipo Cliente
        gbc.gridx = 0;
        gbc.gridy = 13;
        panel.add(new JLabel("Tipo Cliente:"), gbc);

        gbc.gridx = 1;
        cbTipoCliente = new JComboBox<>(new String[]{"Regular", "VIP"});
        panel.add(cbTipoCliente, gbc);

        // Botón Guardar
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(this);
        panel.add(btnGuardar, gbc);

        add(panel);
        setVisible(true);
    }

    // Método para agregar campos con alineación correcta
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
                int idCliente = Integer.parseInt(txtIdCliente.getText().trim());
                String nombre = txtNombre.getText().trim();
                String apellidoPat = txtApellidoPat.getText().trim();
                String apellidoMat = txtApellidoMat.getText().trim();
                String direccion = txtDireccion.getText().trim();
                String fechaNac = txtFechaNac.getText().trim();
                String telefono = txtTelefono.getText().trim();
                String celular = txtCelular.getText().trim();
                String fechaIngreso = txtFechaIngreso.getText().trim();
                String correo = txtCorreo.getText().trim();
                double balance = Double.parseDouble(txtBalance.getText().trim());
                double cuota = Double.parseDouble(txtCuota.getText().trim());
                String status = cbStatusCliente.getSelectedItem().toString();
                String tipo = cbTipoCliente.getSelectedItem().toString();

                Cliente nuevoCliente = new Cliente(idCliente, nombre, apellidoPat, apellidoMat, direccion, fechaNac, telefono, celular, fechaIngreso, correo, balance, cuota, status, tipo);
                listaClientes.add(nuevoCliente);
                JOptionPane.showMessageDialog(this, "Cliente guardado exitosamente.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error en los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
