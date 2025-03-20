package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class FormularioEncabezadoCuota extends JFrame implements ActionListener {

    private JTextField txtIdCuota, txtFechaCuota, txtValorCobro;
    private JComboBox<Integer> cbIdCliente;
    private JCheckBox chkStatusCuota;
    private JButton btnGuardar;

    public static ArrayList<EncabezadoCuota> listaEncabezadoCuotas = new ArrayList<>();
    private static final String ARCHIVO_ENCABEZADO_CUOTAS = "encabezado_cuotas.txt";

    public FormularioEncabezadoCuota() {
        setTitle("Gestión de Encabezado de Cuotas");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ✅ Métodos para agregar los campos
        agregarCampo(panel, gbc, 0, "ID Cuota:", txtIdCuota = new JTextField(15));
        agregarCampo(panel, gbc, 1, "Fecha Cuota:", txtFechaCuota = new JTextField(15));
        agregarCampo(panel, gbc, 2, "Valor Cobro:", txtValorCobro = new JTextField(15));

        // ComboBox de Clientes
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("ID Cliente:"), gbc);
        gbc.gridx = 1;
        cbIdCliente = new JComboBox<>();
        panel.add(cbIdCliente, gbc);

        // Checkbox Status
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Estado Cuota:"), gbc);
        gbc.gridx = 1;
        chkStatusCuota = new JCheckBox();
        panel.add(chkStatusCuota, gbc);

        // Botón Guardar
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(this);
        panel.add(btnGuardar, gbc);

        cargarClientes(); // ✅ Cargar lista de clientes antes de mostrar el formulario
        cargarCuotasDesdeArchivo();

        add(panel);
        setVisible(true);
    }

    // ✅ Método para agregar campos
    private void agregarCampo(JPanel panel, GridBagConstraints gbc, int y, String label, JTextField textField) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        panel.add(textField, gbc);
    }

    // ✅ Método para cargar clientes en el ComboBox
    private void cargarClientes() {
        cbIdCliente.removeAllItems(); // Asegurar que se borren elementos previos

        if (FormularioClientes.listaClientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay clientes registrados. Agregue clientes antes de generar una cuota.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            for (Cliente cliente : FormularioClientes.listaClientes) {
                cbIdCliente.addItem(cliente.getIdCliente());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            try {
                int idCuota = Integer.parseInt(txtIdCuota.getText().trim());
                String fechaCuota = txtFechaCuota.getText().trim();
                double valorCobro = Double.parseDouble(txtValorCobro.getText().trim());
                int idCliente = (int) cbIdCliente.getSelectedItem();
                boolean statusCuota = chkStatusCuota.isSelected();

                if (fechaCuota.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                EncabezadoCuota nuevaCuota = new EncabezadoCuota(idCuota, fechaCuota, (int) valorCobro, idCliente, statusCuota);
                listaEncabezadoCuotas.add(nuevaCuota);
                guardarCuotasEnArchivo();

                JOptionPane.showMessageDialog(this, "Encabezado de Cuota guardado correctamente.");
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error en el formato de los valores numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limpiarCampos() {
        txtIdCuota.setText("");
        txtFechaCuota.setText("");
        txtValorCobro.setText("");
        cbIdCliente.setSelectedIndex(0);
        chkStatusCuota.setSelected(false);
    }

    // ✅ Guardar encabezado de cuota en archivo
    private void guardarCuotasEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_ENCABEZADO_CUOTAS))) {
            for (EncabezadoCuota cuota : listaEncabezadoCuotas) {
                bw.write(cuota.getIdCuota() + "," + cuota.getFechaCuota() + "," +
                        cuota.getValorCobro() + "," + cuota.getIdCliente() + "," +
                        cuota.isStatusCuota());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los encabezados de cuota.");
        }
    }

    // ✅ Cargar encabezados de cuota desde archivo
    private void cargarCuotasDesdeArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_ENCABEZADO_CUOTAS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    int idCuota = Integer.parseInt(datos[0]);
                    String fechaCuota = datos[1];
                    double valorCobro = Double.parseDouble(datos[2]);
                    int idCliente = Integer.parseInt(datos[3]);
                    boolean statusCuota = Boolean.parseBoolean(datos[4]);
                    listaEncabezadoCuotas.add(new EncabezadoCuota(idCuota, fechaCuota, (int) valorCobro, idCliente, statusCuota));
                }
            }
        } catch (IOException e) {
            System.out.println("No se encontró el archivo de encabezado de cuota, se creará uno nuevo.");
        }
    }
}
