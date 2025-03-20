package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FormularioHorariosActividades extends JFrame implements ActionListener {

    private JTextField txtIdHorario, txtHora;
    private JComboBox<String> cbDia;
    private JComboBox<Integer> cbIdActividad;
    private JButton btnGuardar;

    // Lista de horarios de actividades
    public static ArrayList<HorarioActividad> listaHorarios = new ArrayList<>();

    public FormularioHorariosActividades() {
        setTitle("Gestión de Horarios de Actividades");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos
        agregarCampo(panel, gbc, 0, "ID Horario:", txtIdHorario = new JTextField(15));

        // ComboBox de Días
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Día:"), gbc);
        gbc.gridx = 1;
        cbDia = new JComboBox<>(new String[]{"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"});
        panel.add(cbDia, gbc);

        // Campo Hora
        agregarCampo(panel, gbc, 2, "Hora:", txtHora = new JTextField(15));

        // ComboBox de Actividades
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("ID Actividad:"), gbc);
        gbc.gridx = 1;
        cbIdActividad = new JComboBox<>();
        panel.add(cbIdActividad, gbc);

        // Botón Guardar
        gbc.gridx = 0; gbc.gridy = 4;
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

    // Cargar IDs de Actividades
    private void cargarDatos() {
        // Cargar IDs de Actividades (Simulado)
        for (int i = 1; i <= 5; i++) {
            cbIdActividad.addItem(i);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            try {
                int idHorario = Integer.parseInt(txtIdHorario.getText().trim());
                String dia = cbDia.getSelectedItem().toString();
                String hora = txtHora.getText().trim();
                int idActividad = (int) cbIdActividad.getSelectedItem();

                if (hora.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar una hora válida.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Guardar horario de actividad en la lista
                HorarioActividad nuevoHorario = new HorarioActividad(idHorario, dia, hora, idActividad);
                listaHorarios.add(nuevoHorario);
                JOptionPane.showMessageDialog(this, "Horario de Actividad guardado exitosamente.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
