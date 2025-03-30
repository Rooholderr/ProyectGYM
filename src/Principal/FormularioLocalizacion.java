package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class FormularioLocalizacion extends JFrame implements ActionListener {

    private JTextField txtIdLocalizacion, txtTipo, txtDescripcion;
    private JButton btnGuardar, btnMostrar;

    public static ArrayList<Localizacion> listaLocalizaciones = new ArrayList<>();
    private static final String ARCHIVO_LOCALIZACIONES = "localizaciones.txt";
    private JLabel lblEstado;


    public FormularioLocalizacion() {
        setTitle("Gestión de Localización");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        cargarLocalizacionesDesdeArchivo();

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(10, 25, 49));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lblId = new JLabel("ID Localización:");
        lblId.setForeground(Color.WHITE);
        panel.add(lblId, gbc);
        
        lblEstado = new JLabel(" ");
        lblEstado.setForeground(Color.YELLOW);
        gbc.gridx = 1;
        gbc.gridy = -1;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        panel.add(lblEstado, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtIdLocalizacion = new JTextField(15);
        panel.add(txtIdLocalizacion, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setForeground(Color.WHITE);
        panel.add(lblTipo, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtTipo = new JTextField(15);
        panel.add(txtTipo, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setForeground(Color.WHITE);
        panel.add(lblDescripcion, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtDescripcion = new JTextField(15);
        panel.add(txtDescripcion, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(255, 215, 0));
        btnGuardar.setForeground(Color.BLACK);
        btnGuardar.addActionListener(this);
        panel.add(btnGuardar, gbc);

        gbc.gridy = 4;
        btnMostrar = new JButton("Mostrar Localizaciones");
        btnMostrar.setBackground(new Color(255, 215, 0));
        btnMostrar.setForeground(Color.BLACK);
        btnMostrar.addActionListener(this);
        panel.add(btnMostrar, gbc);
        
        txtIdLocalizacion.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            String textoId = txtIdLocalizacion.getText().trim();
            if (textoId.isEmpty()) {
                limpiarCampos();
                lblEstado.setText(" ");
                return;
            }
            try {
                int id = Integer.parseInt(textoId);
                boolean encontrado = false;
                for (Localizacion loc : listaLocalizaciones) {
                    if (loc.getIdLocalizacion() == id) {
                        txtTipo.setText(loc.getTipo());
                        lblEstado.setText("Modificando");
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    txtTipo.setText("");
                    lblEstado.setText("Creando");
                }
            } catch (NumberFormatException ex) {
                lblEstado.setText(" ");
            }
        }
    });


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

                for (Localizacion loc : listaLocalizaciones) {
                    if (loc.getIdLocalizacion() == id) {
                        JOptionPane.showMessageDialog(this, "La localización ya está registrada.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                listaLocalizaciones.add(new Localizacion(id, tipo));
                guardarLocalizacionesEnArchivo();
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

    private void guardarLocalizacionesEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_LOCALIZACIONES))) {
            for (Localizacion loc : listaLocalizaciones) {
                bw.write(loc.getIdLocalizacion() + "," + loc.getTipo());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de localizaciones.");
        }
    }

    private void cargarLocalizacionesDesdeArchivo() {
        listaLocalizaciones.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_LOCALIZACIONES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 2) {
                    int id = Integer.parseInt(datos[0]);
                    String tipo = datos[1];
                    listaLocalizaciones.add(new Localizacion(id, tipo));
                }
            }
        } catch (IOException e) {
            System.out.println("No se encontró el archivo de localizaciones, se creará uno nuevo.");
        }
    }
}
