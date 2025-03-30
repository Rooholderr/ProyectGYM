package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class FormularioActividades extends JFrame implements ActionListener {

    private JTextField txtIdActividad, txtNombreActividad, txtDescripcionActividad, txtIdLocalizacion, txtIdEntrenador;
    private JButton btnGuardar, btnMostrar;
    private JLabel lblEstado;

    public static ArrayList<Actividad> listaActividades = new ArrayList<>();

    public FormularioActividades() {
        setTitle("Gestión de Actividades");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(10, 25, 49));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        // Mensaje encima del campo ID
        lblEstado = new JLabel(" ");
        lblEstado.setForeground(Color.YELLOW);
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.NORTHEAST;
        panel.add(lblEstado, gbc);
        gbc.gridwidth = 1;

        // ID Actividad
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lblId = new JLabel("ID Actividad:");
        lblId.setForeground(Color.WHITE);
        panel.add(lblId, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtIdActividad = new JTextField(15);
        panel.add(txtIdActividad, gbc);

        // Nombre Actividad
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lblNombre = new JLabel("Nombre Actividad:");
        lblNombre.setForeground(Color.WHITE);
        panel.add(lblNombre, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtNombreActividad = new JTextField(15);
        panel.add(txtNombreActividad, gbc);

        // Descripción
        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lblDesc = new JLabel("Descripción:");
        lblDesc.setForeground(Color.WHITE);
        panel.add(lblDesc, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtDescripcionActividad = new JTextField(15);
        panel.add(txtDescripcionActividad, gbc);

        // ID Localización
        gbc.gridx = 0; gbc.gridy = 4; gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lblLoc = new JLabel("ID Localización:");
        lblLoc.setForeground(Color.WHITE);
        panel.add(lblLoc, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtIdLocalizacion = new JTextField(15);
        panel.add(txtIdLocalizacion, gbc);

        // ID Entrenador
        gbc.gridx = 0; gbc.gridy = 5; gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lblEnt = new JLabel("ID Entrenador:");
        lblEnt.setForeground(Color.WHITE);
        panel.add(lblEnt, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtIdEntrenador = new JTextField(15);
        panel.add(txtIdEntrenador, gbc);

        // Botón Guardar
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(255, 215, 0));
        btnGuardar.setForeground(Color.BLACK);
        btnGuardar.addActionListener(this);
        panel.add(btnGuardar, gbc);

        // Botón Mostrar
        gbc.gridy = 7;
        btnMostrar = new JButton("Mostrar Actividades");
        btnMostrar.setBackground(new Color(255, 215, 0));
        btnMostrar.setForeground(Color.BLACK);
        btnMostrar.addActionListener(this);
        panel.add(btnMostrar, gbc);

        // Evento: buscar actividad al escribir el ID
        txtIdActividad.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String idTexto = txtIdActividad.getText().trim();
                if (idTexto.isEmpty()) {
                    limpiarCampos();
                    lblEstado.setText(" ");
                    return;
                }
                try {
                    int id = Integer.parseInt(idTexto);
                    boolean encontrado = false;
                    for (Actividad act : listaActividades) {
                        if (act.getIdActividad() == id) {
                            txtNombreActividad.setText(act.getNombreActividad());
                            txtDescripcionActividad.setText(act.getDescripcionActividad());
                            txtIdLocalizacion.setText(String.valueOf(act.getIdLocalizacion()));
                            txtIdEntrenador.setText(String.valueOf(act.getIdEntrenador()));
                            lblEstado.setText("Modificando");
                            lblEstado.setForeground(Color.GREEN);
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        txtNombreActividad.setText("");
                        txtDescripcionActividad.setText("");
                        txtIdLocalizacion.setText("");
                        txtIdEntrenador.setText("");
                        lblEstado.setText("Creando");
                        lblEstado.setForeground(new Color(255, 215, 0));
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
                int idActividad = Integer.parseInt(txtIdActividad.getText().trim());
                String nombre = txtNombreActividad.getText().trim();
                String descripcion = txtDescripcionActividad.getText().trim();
                int idLoc = Integer.parseInt(txtIdLocalizacion.getText().trim());
                int idEnt = Integer.parseInt(txtIdEntrenador.getText().trim());

                if (nombre.isEmpty() || descripcion.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!existeIdEnArchivo("localizaciones.txt", idLoc)) {
                    JOptionPane.showMessageDialog(this, "ID de Localización no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!existeIdEnArchivo("entrenadores.txt", idEnt)) {
                    JOptionPane.showMessageDialog(this, "ID de Entrenador no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                for (Actividad act : listaActividades) {
                    if (act.getIdActividad() == idActividad) {
                        JOptionPane.showMessageDialog(this, "La actividad ya está registrada.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                Actividad nueva = new Actividad(idActividad, nombre, descripcion, idLoc, idEnt);
                listaActividades.add(nueva);
                guardarActividadEnArchivo(nueva);
                JOptionPane.showMessageDialog(this, "Actividad guardada exitosamente.");
                limpiarCampos();
                lblEstado.setText(" ");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Los ID deben ser números enteros.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnMostrar) {
            mostrarActividades();
        }
    }

    private boolean existeIdEnArchivo(String archivo, int idBuscado) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length > 0 && Integer.parseInt(datos[0]) == idBuscado) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private void guardarActividadEnArchivo(Actividad act) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("actividades.txt", true))) {
            bw.write(act.getIdActividad() + "," + act.getNombreActividad() + "," + act.getDescripcionActividad()
                    + "," + act.getIdLocalizacion() + "," + act.getIdEntrenador());
            bw.newLine();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar la actividad.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtIdActividad.setText("");
        txtNombreActividad.setText("");
        txtDescripcionActividad.setText("");
        txtIdLocalizacion.setText("");
        txtIdEntrenador.setText("");
    }

    private void mostrarActividades() {
        if (listaActividades.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay actividades registradas.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder lista = new StringBuilder("Actividades Registradas:\n");
        for (Actividad act : listaActividades) {
            lista.append("• ID: ").append(act.getIdActividad())
                 .append(" - Nombre: ").append(act.getNombreActividad())
                 .append(" - Descripción: ").append(act.getDescripcionActividad())
                 .append(" - ID Localización: ").append(act.getIdLocalizacion())
                 .append(" - ID Entrenador: ").append(act.getIdEntrenador()).append("\n");
        }

        JOptionPane.showMessageDialog(this, lista.toString(), "Lista de Actividades", JOptionPane.INFORMATION_MESSAGE);
    }
}
