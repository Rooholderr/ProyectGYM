package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class FormularioEntrenadores extends JFrame implements ActionListener {

    private JTextField txtIdEntrenador, txtNombre, txtApellido, txtTelefono, txtCorreo;
    private JButton btnGuardar, btnMostrar, btnModificar;
    private JLabel lblEstado;

    public static ArrayList<Entrenador> listaEntrenadores = new ArrayList<>();
    private static final String ARCHIVO_ENTRENADORES = "entrenadores.txt";

    public FormularioEntrenadores() {
        setTitle("Gestión de Entrenadores");
        setSize(450, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        cargarEntrenadoresDesdeArchivo();

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(10, 25, 49));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        // Imagen superior
        ImageIcon icono = new ImageIcon("logo.png");
        JLabel lblImagen = new JLabel(icono);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblImagen, gbc);

        // Estado
        lblEstado = new JLabel(" ");
        lblEstado.setForeground(Color.YELLOW);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        panel.add(lblEstado, gbc);

        gbc.gridwidth = 1;

        // Campo ID
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(crearLabel("ID Entrenador:"), gbc);
        gbc.gridx = 1;
        txtIdEntrenador = new JTextField();
        txtIdEntrenador.setPreferredSize(new Dimension(200, 25));
        panel.add(txtIdEntrenador, gbc);

        // Campo Nombre
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(crearLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField();
        txtNombre.setPreferredSize(new Dimension(200, 25));
        panel.add(txtNombre, gbc);

        // Campo Apellido
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(crearLabel("Apellido:"), gbc);
        gbc.gridx = 1;
        txtApellido = new JTextField();
        txtApellido.setPreferredSize(new Dimension(200, 25));
        panel.add(txtApellido, gbc);

        // Campo Teléfono
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(crearLabel("Teléfono:"), gbc);
        gbc.gridx = 1;
        txtTelefono = new JTextField();
        txtTelefono.setPreferredSize(new Dimension(200, 25));
        panel.add(txtTelefono, gbc);

        // Campo Correo
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(crearLabel("Correo Electrónico:"), gbc);
        gbc.gridx = 1;
        txtCorreo = new JTextField();
        txtCorreo.setPreferredSize(new Dimension(200, 25));
        panel.add(txtCorreo, gbc);

        // Panel de botones horizontal
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelBotones.setBackground(new Color(10, 25, 49));

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(255, 215, 0));
        btnGuardar.setForeground(Color.BLACK);
        btnGuardar.addActionListener(this);

        btnModificar = new JButton("Modificar");
        btnModificar.setBackground(new Color(255, 215, 0));
        btnModificar.setForeground(Color.BLACK);
        btnModificar.addActionListener(this);

        btnMostrar = new JButton("Mostrar Entrenadores");
        btnMostrar.setBackground(new Color(255, 215, 0));
        btnMostrar.setForeground(Color.BLACK);
        btnMostrar.addActionListener(this);

        panelBotones.add(btnGuardar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnMostrar);

        panel.add(panelBotones, gbc);

        // Eventos de teclado
        txtIdEntrenador.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String textoId = txtIdEntrenador.getText().trim();
                if (textoId.isEmpty()) {
                    limpiarCampos();
                    lblEstado.setText(" ");
                    return;
                }
                try {
                    int id = Integer.parseInt(textoId);
                    boolean encontrado = false;
                    for (Entrenador ent : listaEntrenadores) {
                        if (ent.getIdEntrenador() == id) {
                            txtNombre.setText(ent.getNombre());
                            txtApellido.setText(ent.getApellido());
                            txtTelefono.setText(ent.getTelefono());
                            txtCorreo.setText(ent.getCorreo());
                            lblEstado.setText("Modificando");
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        limpiarCampos();
                        txtIdEntrenador.setText(textoId);
                        lblEstado.setText("Creando");
                    }
                } catch (NumberFormatException ex) {
                    lblEstado.setText(" ");
                }
            }
        });

        txtNombre.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String nombreIngresado = txtNombre.getText().trim();
                if (nombreIngresado.isEmpty()) {
                    limpiarCampos();
                    return;
                }
                for (Entrenador ent : listaEntrenadores) {
                    if (ent.getNombre().equalsIgnoreCase(nombreIngresado)) {
                        txtIdEntrenador.setText(String.valueOf(ent.getIdEntrenador()));
                        txtApellido.setText(ent.getApellido());
                        txtTelefono.setText(ent.getTelefono());
                        txtCorreo.setText(ent.getCorreo());
                        lblEstado.setText("Modificando");
                        return;
                    }
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            try {
                int idEntrenador = Integer.parseInt(txtIdEntrenador.getText().trim());
                String nombre = txtNombre.getText().trim();
                String apellido = txtApellido.getText().trim();
                String telefono = txtTelefono.getText().trim();
                String correo = txtCorreo.getText().trim();

                if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                for (Entrenador ent : listaEntrenadores) {
                    if (ent.getIdEntrenador() == idEntrenador) {
                        JOptionPane.showMessageDialog(this, "El ID de entrenador ya está registrado.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                listaEntrenadores.add(new Entrenador(idEntrenador, nombre, apellido, telefono, correo));
                guardarEntrenadoresEnArchivo();
                JOptionPane.showMessageDialog(this, "Entrenador guardado exitosamente.");
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == btnModificar) {
            try {
                int idEntrenador = Integer.parseInt(txtIdEntrenador.getText().trim());
                String nombre = txtNombre.getText().trim();
                String apellido = txtApellido.getText().trim();
                String telefono = txtTelefono.getText().trim();
                String correo = txtCorreo.getText().trim();

                boolean encontrado = false;
                for (Entrenador ent : listaEntrenadores) {
                    if (ent.getIdEntrenador() == idEntrenador) {
                        ent.setNombre(nombre);
                        ent.setApellido(apellido);
                        ent.setTelefono(telefono);
                        ent.setCorreo(correo);
                        encontrado = true;
                        break;
                    }
                }

                if (encontrado) {
                    guardarEntrenadoresEnArchivo();
                    JOptionPane.showMessageDialog(this, "Entrenador modificado exitosamente.");
                    limpiarCampos();
                    lblEstado.setText(" ");
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró el entrenador con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == btnMostrar) {
            mostrarEntrenadores();
        }
    }

    private void limpiarCampos() {
        txtIdEntrenador.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        lblEstado.setText(" ");
    }

    private void mostrarEntrenadores() {
        if (listaEntrenadores.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay entrenadores registrados.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder lista = new StringBuilder("Entrenadores Registrados:\n");
        for (Entrenador ent : listaEntrenadores) {
            lista.append("\u2022 ID: ").append(ent.getIdEntrenador())
                 .append(" - Nombre: ").append(ent.getNombre()).append(" ").append(ent.getApellido())
                 .append(" - Tel: ").append(ent.getTelefono())
                 .append(" - Correo: ").append(ent.getCorreo()).append("\n");
        }

        JOptionPane.showMessageDialog(this, lista.toString(), "Lista de Entrenadores", JOptionPane.INFORMATION_MESSAGE);
    }

    private void guardarEntrenadoresEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_ENTRENADORES))) {
            for (Entrenador ent : listaEntrenadores) {
                bw.write(ent.getIdEntrenador() + "," + ent.getNombre() + "," + ent.getApellido() + "," +
                         ent.getTelefono() + "," + ent.getCorreo());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar entrenadores.");
        }
    }

    private void cargarEntrenadoresDesdeArchivo() {
        listaEntrenadores.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_ENTRENADORES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    int id = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    String apellido = datos[2];
                    String telefono = datos[3];
                    String correo = datos[4];
                    listaEntrenadores.add(new Entrenador(id, nombre, apellido, telefono, correo));
                }
            }
        } catch (IOException e) {
            System.out.println("No se encontró el archivo de entrenadores, se creará uno nuevo.");
        }
    }
}
