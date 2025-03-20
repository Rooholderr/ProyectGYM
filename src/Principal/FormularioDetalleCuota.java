package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class FormularioDetalleCuota extends JFrame implements ActionListener {

    private JTextField txtSecCuota, txtConceptoCuota, txtValorCuota;
    private JComboBox<Integer> cbIdCuota, cbIdCobroCuota;
    private JButton btnGuardar;

    public static ArrayList<DetalleCuota> listaDetalleCuotas = new ArrayList<>();
    private static final String ARCHIVO_DETALLE_CUOTAS = "detalle_cuotas.txt";

    public FormularioDetalleCuota() {
        setTitle("Gestión de Detalle de Cuotas");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ✅ Método agregarCampo corregido y agregado
        agregarCampo(panel, gbc, 0, "ID Cuota:", cbIdCuota = new JComboBox<>());
        agregarCampo(panel, gbc, 1, "Secuencia Cuota:", txtSecCuota = new JTextField(15));
        agregarCampo(panel, gbc, 2, "Concepto Cuota:", txtConceptoCuota = new JTextField(15));
        agregarCampo(panel, gbc, 3, "Valor Cuota:", txtValorCuota = new JTextField(15));
        agregarCampo(panel, gbc, 4, "ID Cobro:", cbIdCobroCuota = new JComboBox<>());

        // Botón Guardar
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(this);
        panel.add(btnGuardar, gbc);

        cargarDatos();
        cargarDetallesDesdeArchivo();

        add(panel);
        setVisible(true);
    }

    // ✅ Agregar el método que faltaba
    private void agregarCampo(JPanel panel, GridBagConstraints gbc, int y, String label, JComponent component) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        panel.add(component, gbc);
    }

    private void cargarDatos() {
        for (EncabezadoCuota cuota : FormularioEncabezadoCuota.listaEncabezadoCuotas) {
            cbIdCuota.addItem(cuota.getIdCuota());
        }
        for (Cobro cobro : FormularioCobros.listaCobros) {
            cbIdCobroCuota.addItem(cobro.getIdCobro());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            try {
                int idCuota = (int) cbIdCuota.getSelectedItem();
                int secCuota = Integer.parseInt(txtSecCuota.getText().trim());
                String conceptoCuota = txtConceptoCuota.getText().trim();
                double valorCuota = Double.parseDouble(txtValorCuota.getText().trim());
                int idCobro = (int) cbIdCobroCuota.getSelectedItem();

                if (conceptoCuota.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                DetalleCuota nuevoDetalle = new DetalleCuota(idCuota, secCuota, conceptoCuota, valorCuota, idCobro);
                listaDetalleCuotas.add(nuevoDetalle);
                guardarDetallesEnArchivo();

                JOptionPane.showMessageDialog(this, "Detalle de Cuota guardado correctamente.");
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error en el formato de los valores numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limpiarCampos() {
        txtSecCuota.setText("");
        txtConceptoCuota.setText("");
        txtValorCuota.setText("");
        cbIdCuota.setSelectedIndex(0);
        cbIdCobroCuota.setSelectedIndex(0);
    }

    // ✅ Guardar detalles de cuota en archivo
    private void guardarDetallesEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_DETALLE_CUOTAS))) {
            for (DetalleCuota detalle : listaDetalleCuotas) {
                bw.write(detalle.getIdCuota() + "," + detalle.getSecCuota() + "," +
                        detalle.getConceptoCuota() + "," + detalle.getValorCuota() + "," +
                        detalle.getIdCobroCuota());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los detalles de cuota.");
        }
    }

    // ✅ Cargar detalles de cuota desde archivo
    private void cargarDetallesDesdeArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_DETALLE_CUOTAS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    int idCuota = Integer.parseInt(datos[0]);
                    int secCuota = Integer.parseInt(datos[1]);
                    String conceptoCuota = datos[2];
                    double valorCuota = Double.parseDouble(datos[3]);
                    int idCobroCuota = Integer.parseInt(datos[4]);
                    listaDetalleCuotas.add(new DetalleCuota(idCuota, secCuota, conceptoCuota, valorCuota, idCobroCuota));
                }
            }
        } catch (IOException e) {
            System.out.println("No se encontró el archivo de detalles de cuota, se creará uno nuevo.");
        }
    }
}
