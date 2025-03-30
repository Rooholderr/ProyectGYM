package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class FormularioSalas extends JFrame implements ActionListener {

    private JTextField txtIdSala, txtNombreSala, txtDescripcionSala, txtIdLocalizacionSala;
    private JButton btnGuardar, btnMostrar;

    public static ArrayList<Sala> listaSalas = new ArrayList<>();
    private static final String ARCHIVO_SALAS = "salas.txt";
    private JLabel lblEstado;


    public FormularioSalas() {
        setTitle("Gestión de Salas");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        cargarSalasDesdeArchivo();

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(10, 25, 49));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        
        // Mensaje justo encima del campo ID Sala
        lblEstado = new JLabel(" ");
        lblEstado.setForeground(Color.YELLOW);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        panel.add(lblEstado, gbc);



        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lblIdSala = new JLabel("ID Sala:");
        lblIdSala.setForeground(Color.WHITE);
        panel.add(lblIdSala, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtIdSala = new JTextField(15);
        panel.add(txtIdSala, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lblNombre = new JLabel("Nombre Sala:");
        lblNombre.setForeground(Color.WHITE);
        panel.add(lblNombre, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtNombreSala = new JTextField(15);
        panel.add(txtNombreSala, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setForeground(Color.WHITE);
        panel.add(lblDescripcion, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtDescripcionSala = new JTextField(15);
        panel.add(txtDescripcionSala, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lblIdLoc = new JLabel("ID Localización Sala:");
        lblIdLoc.setForeground(Color.WHITE);
        panel.add(lblIdLoc, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtIdLocalizacionSala = new JTextField(15);
        panel.add(txtIdLocalizacionSala, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(255, 215, 0));
        btnGuardar.setForeground(Color.BLACK);
        btnGuardar.addActionListener(this);
        panel.add(btnGuardar, gbc);

        gbc.gridy = 6;
        btnMostrar = new JButton("Mostrar Salas");
        btnMostrar.setBackground(new Color(255, 215, 0));
        btnMostrar.setForeground(Color.BLACK);
        btnMostrar.addActionListener(this);
        panel.add(btnMostrar, gbc);
        
        txtIdSala.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            String textoId = txtIdSala.getText().trim();
            if (textoId.isEmpty()) {
                limpiarCampos();
                lblEstado.setText(" ");
                return;
            }
            try {
                int id = Integer.parseInt(textoId);
                boolean encontrado = false;
                for (Sala sala : listaSalas) {
                    if (sala.getIdSala() == id) {
                        txtNombreSala.setText(sala.getNombreSala());
                        txtDescripcionSala.setText(sala.getDescripcionSala());
                        txtIdLocalizacionSala.setText(String.valueOf(sala.getIdLocalizacionSala()));
                        lblEstado.setText("Modificando");
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    txtNombreSala.setText("");
                    txtDescripcionSala.setText("");
                    txtIdLocalizacionSala.setText("");
                    lblEstado.setText("Creando");
                }
            } catch (NumberFormatException ex) {
                lblEstado.setText(" ");
            }
        }
    });



    // Agregar el panel completo al frame
        add(panel);


        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            try {
                int idSala = Integer.parseInt(txtIdSala.getText().trim());
                String nombreSala = txtNombreSala.getText().trim();
                String descripcionSala = txtDescripcionSala.getText().trim();
                int idLocalizacionSala = Integer.parseInt(txtIdLocalizacionSala.getText().trim());

                if (nombreSala.isEmpty() || descripcionSala.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                for (Sala sala : listaSalas) {
                    if (sala.getIdSala() == idSala) {
                        JOptionPane.showMessageDialog(this, "La sala ya está registrada.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                listaSalas.add(new Sala(idSala, nombreSala, descripcionSala, idLocalizacionSala));
                guardarSalasEnArchivo();
                JOptionPane.showMessageDialog(this, "Sala guardada exitosamente.");
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Los ID deben ser números enteros.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnMostrar) {
            mostrarSalas();
        }
    }

    private void limpiarCampos() {
        txtIdSala.setText("");
        txtNombreSala.setText("");
        txtDescripcionSala.setText("");
        txtIdLocalizacionSala.setText("");
    }

    private void mostrarSalas() {
        if (listaSalas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay salas registradas.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder lista = new StringBuilder("Salas Registradas:\n");
        for (Sala sala : listaSalas) {
            lista.append("• ID: ").append(sala.getIdSala())
                 .append(" - Nombre: ").append(sala.getNombreSala())
                 .append(" - Descripción: ").append(sala.getDescripcionSala())
                 .append(" - ID Localización: ").append(sala.getIdLocalizacionSala()).append("\n");
        }

        JOptionPane.showMessageDialog(this, lista.toString(), "Lista de Salas", JOptionPane.INFORMATION_MESSAGE);
    }

    private void guardarSalasEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_SALAS))) {
            for (Sala sala : listaSalas) {
                bw.write(sala.getIdSala() + "," + sala.getNombreSala() + "," + sala.getDescripcionSala() + "," + sala.getIdLocalizacionSala());
                bw.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar las salas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarSalasDesdeArchivo() {
        File archivo = new File(ARCHIVO_SALAS);
        if (!archivo.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    int id = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    String descripcion = datos[2];
                    int idLoc = Integer.parseInt(datos[3]);
                    listaSalas.add(new Sala(id, nombre, descripcion, idLoc));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las salas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
