package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FormularioCobros extends JFrame implements ActionListener {

    private JTextField txtIdCobro, txtFechaCobro, txtValorCobro, txtConceptoCobro;
    private JComboBox<Integer> cbIdCliente;
    private JCheckBox chkStatusCobro;
    private JButton btnGuardar;

    public static ArrayList<Cobro> listaCobros = new ArrayList<>();

    public FormularioCobros() {
        setTitle("Gestión de Cobros");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ✅ Método agregarCampo corregido y agregado
        agregarCampo(panel, gbc, 0, "ID Cobro:", txtIdCobro = new JTextField(15));
        agregarCampo(panel, gbc, 1, "Fecha Cobro:", txtFechaCobro = new JTextField(15));
        agregarCampo(panel, gbc, 2, "Valor Cobro:", txtValorCobro = new JTextField(15));
        agregarCampo(panel, gbc, 3, "Concepto Cobro:", txtConceptoCobro = new JTextField(15));

        // ComboBox de Clientes
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("ID Cliente:"), gbc);
        gbc.gridx = 1;
        cbIdCliente = new JComboBox<>();
        panel.add(cbIdCliente, gbc);

        // Checkbox Status
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Estado Cobro:"), gbc);
        gbc.gridx = 1;
        chkStatusCobro = new JCheckBox();
        panel.add(chkStatusCobro, gbc);

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

    // ✅ Agregar el método que faltaba
    private void agregarCampo(JPanel panel, GridBagConstraints gbc, int y, String label, JTextField textField) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        panel.add(textField, gbc);
    }

    private void cargarDatos() {
        for (Cliente cliente : FormularioClientes.listaClientes) {
            cbIdCliente.addItem(cliente.getIdCliente());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            JOptionPane.showMessageDialog(this, "Cobro guardado correctamente.");
        }
    }
}
