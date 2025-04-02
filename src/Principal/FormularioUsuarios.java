package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class FormularioUsuarios extends JFrame implements ActionListener {

    private JTextField txtIdUsuario, txtNombre, txtApellidos, txtCorreo;
    private JPasswordField txtPassword;
    private JComboBox<String> cbNivelAcceso;
    private JButton btnGuardar, btnMostrar, btnEliminar, btnModificar;
    private JLabel lblEstado;

    public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private static final String ARCHIVO_USUARIOS = "usuarios.txt";

    public FormularioUsuarios() {
        setTitle("Gestión de Usuarios");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        cargarUsuariosDesdeArchivo();
        agregarAdministradorFijo();

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(15, 15, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        // Letrero de estado (Creando / Modificando)
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        lblEstado = new JLabel(" ");
        lblEstado.setForeground(Color.YELLOW);
        panel.add(lblEstado, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lblId = new JLabel("ID Usuario:");
        lblId.setForeground(Color.WHITE);
        panel.add(lblId, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtIdUsuario = new JTextField(15);
        panel.add(txtIdUsuario, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(Color.WHITE);
        panel.add(lblNombre, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtNombre = new JTextField(15);
        panel.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setForeground(Color.WHITE);
        panel.add(lblApellidos, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtApellidos = new JTextField(15);
        panel.add(txtApellidos, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lblCorreo = new JLabel("Correo Electrónico:");
        lblCorreo.setForeground(Color.WHITE);
        panel.add(lblCorreo, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtCorreo = new JTextField(15);
        panel.add(txtCorreo, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setForeground(Color.WHITE);
        panel.add(lblPassword, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtPassword = new JPasswordField(15);
        panel.add(txtPassword, gbc);

        gbc.gridx = 0; gbc.gridy = 6; gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lblNivel = new JLabel("Nivel de Acceso:");
        lblNivel.setForeground(Color.WHITE);
        panel.add(lblNivel, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        String[] niveles = {"0 - Administrador", "1 - Usuario Normal"};
        cbNivelAcceso = new JComboBox<>(niveles);
        panel.add(cbNivelAcceso, gbc);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelBotones.setBackground(new Color(15, 15, 30));

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(255, 215, 0));
        btnGuardar.setForeground(Color.BLACK);

        btnModificar = new JButton("Modificar");
        btnModificar.setBackground(new Color(255, 215, 0));
        btnModificar.setForeground(Color.BLACK);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(255, 215, 0));
        btnEliminar.setForeground(Color.BLACK);

        btnMostrar = new JButton("Mostrar");
        btnMostrar.setBackground(new Color(255, 215, 0));
        btnMostrar.setForeground(Color.BLACK);

        btnGuardar.addActionListener(this);
        btnModificar.addActionListener(this);
        btnEliminar.addActionListener(this);
        btnMostrar.addActionListener(this);

        panelBotones.add(btnGuardar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnMostrar);

        panel.add(panelBotones, gbc);

        txtIdUsuario.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String textoId = txtIdUsuario.getText().trim();
                if (textoId.isEmpty()) {
                    limpiarCampos();
                    return;
                }
                try {
                    int id = Integer.parseInt(textoId);
                    boolean encontrado = false;
                    for (Usuario u : listaUsuarios) {
                        if (u.getIdUsuario() == id) {
                            txtNombre.setText(u.getNombre());
                            txtApellidos.setText(u.getApellidos());
                            txtCorreo.setText(u.getCorreo());
                            txtPassword.setText(u.getPassword());
                            cbNivelAcceso.setSelectedIndex(u.getNivelAcceso());
                            lblEstado.setText("Modificando");
                            encontrado = true;
                            return;
                        }
                    }
                    if (!encontrado) {
                        lblEstado.setText("Creando");
                    }
                } catch (NumberFormatException ex) {
                    limpiarCampos();
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int idUsuario = Integer.parseInt(txtIdUsuario.getText().trim());

            if (e.getSource() == btnGuardar) {
                for (Usuario u : listaUsuarios) {
                    if (u.getIdUsuario() == idUsuario) {
                        JOptionPane.showMessageDialog(this, "Ya existe un usuario con este ID.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                String nombre = txtNombre.getText().trim();
                String apellidos = txtApellidos.getText().trim();
                String correo = txtCorreo.getText().trim();
                String password = new String(txtPassword.getPassword()).trim();
                int nivelAcceso = cbNivelAcceso.getSelectedIndex();

                if (nombre.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Usuario nuevoUsuario = new Usuario(idUsuario, nombre, apellidos, correo, password, nivelAcceso);
                listaUsuarios.add(nuevoUsuario);
                guardarUsuariosEnArchivo();
                lblEstado.setText("Creando...");
                JOptionPane.showMessageDialog(this, "Usuario guardado exitosamente.");
                limpiarCampos();
            } else if (e.getSource() == btnModificar) {
                String nombre = txtNombre.getText().trim();
                String apellidos = txtApellidos.getText().trim();
                String correo = txtCorreo.getText().trim();
                String password = new String(txtPassword.getPassword()).trim();
                int nivelAcceso = cbNivelAcceso.getSelectedIndex();

                if (nombre.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean encontrado = false;
                for (Usuario u : listaUsuarios) {
                    if (u.getIdUsuario() == idUsuario) {
                        u.setNombre(nombre);
                        u.setApellidos(apellidos);
                        u.setCorreo(correo);
                        u.setPassword(password);
                        u.setNivelAcceso(nivelAcceso);
                        encontrado = true;
                        break;
                    }
                }

                if (encontrado) {
                    guardarUsuariosEnArchivo();
                    lblEstado.setText("Modificando...");
                    JOptionPane.showMessageDialog(this, "Usuario modificado exitosamente.");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró un usuario con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == btnEliminar) {
                boolean eliminado = listaUsuarios.removeIf(u -> u.getIdUsuario() == idUsuario && !u.getNombre().equalsIgnoreCase("Ronald"));
                if (eliminado) {
                    guardarUsuariosEnArchivo();
                    lblEstado.setText("Modificando...");
                    JOptionPane.showMessageDialog(this, "Usuario eliminado.");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "No se puede eliminar este usuario o no existe.");
                }
            }

        } catch (NumberFormatException ex) {
            if (e.getSource() != btnMostrar) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (e.getSource() == btnMostrar) {
            if (listaUsuarios.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay usuarios registrados.");
                return;
            }

            StringBuilder sb = new StringBuilder();
            for (Usuario u : listaUsuarios) {
                sb.append("ID: ").append(u.getIdUsuario())
                  .append(", Nombre: ").append(u.getNombre())
                  .append(", Apellidos: ").append(u.getApellidos())
                  .append(", Correo: ").append(u.getCorreo())
                  .append(", Nivel: ").append(u.getNivelAcceso())
                  .append("\n");
            }

            JTextArea area = new JTextArea(sb.toString());
            area.setEditable(false);
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new Dimension(400, 200));
            JOptionPane.showMessageDialog(this, scroll, "Lista de Usuarios", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtIdUsuario.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtCorreo.setText("");
        txtPassword.setText("");
        cbNivelAcceso.setSelectedIndex(0);
        lblEstado.setText(" ");
    }

    private void agregarAdministradorFijo() {
        boolean existeAdmin = false;
        for (Usuario u : listaUsuarios) {
            if (u.getNombre().equalsIgnoreCase("Ronald") && u.getPassword().equals("12345")) {
                existeAdmin = true;
                break;
            }
        }
        if (!existeAdmin) {
            Usuario admin = new Usuario(1, "Ronald", "Administrador", "admin@gmail.com", "12345", 0);
            listaUsuarios.add(admin);
            guardarUsuariosEnArchivo();
        }
    }

    private void guardarUsuariosEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_USUARIOS))) {
            for (Usuario u : listaUsuarios) {
                bw.write(u.getIdUsuario() + "," + u.getNombre() + "," + u.getApellidos() + "," +
                        u.getCorreo() + "," + u.getPassword() + "," + u.getNivelAcceso());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo.");
        }
    }

    private void cargarUsuariosDesdeArchivo() {
        listaUsuarios.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 6) {
                    int idUsuario = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    String apellidos = datos[2];
                    String correo = datos[3];
                    String password = datos[4];
                    int nivelAcceso = Integer.parseInt(datos[5]);
                    listaUsuarios.add(new Usuario(idUsuario, nombre, apellidos, correo, password, nivelAcceso));
                }
            }
        } catch (IOException e) {
            System.out.println("No se encontró el archivo de usuarios, se creará uno nuevo.");
        }
    }
}
