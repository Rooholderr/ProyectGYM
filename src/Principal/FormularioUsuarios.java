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
    private JButton btnGuardar, btnEliminar, btnLimpiar;
    private JLabel lblEstado;

    public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private static final String ARCHIVO_USUARIOS = "usuarios.txt";

    public FormularioUsuarios() {
        setTitle("Gestión de Usuarios");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Mostrar MenuPrincipal al cerrar
        addWindowListener(new WindowAdapter() {
    @Override
    public void windowClosed(WindowEvent e) {
        setVisible(false); // oculta esta ventana
    }
});

        cargarUsuariosDesdeArchivo();
        agregarAdministradorFijo();

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(15, 15, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

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
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(255, 215, 0));
        btnEliminar.setForeground(Color.BLACK);
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBackground(new Color(255, 215, 0));
        btnLimpiar.setForeground(Color.BLACK);

        btnGuardar.addActionListener(this);
        btnEliminar.addActionListener(this);
        btnLimpiar.addActionListener(this);

        panelBotones.add(btnGuardar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        panel.add(panelBotones, gbc);

        txtIdUsuario.addKeyListener(new KeyAdapter() {
    @Override
    public void keyReleased(KeyEvent e) {
        String textoId = txtIdUsuario.getText().trim();
        if (textoId.isEmpty()) {
            lblEstado.setText(" ");
            return;
        }
        try {
            int id = Integer.parseInt(textoId);
            boolean encontrado = false;
            for (Usuario u : listaUsuarios) {
                if (u.getIdUsuario() == id) {
                    lblEstado.setText("Modificando");
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                lblEstado.setText("Creando");
            }
        } catch (NumberFormatException ex) {
            lblEstado.setText(" ");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String textoId = txtIdUsuario.getText().trim();
            if (textoId.isEmpty()) return;
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
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    limpiarCampos();
                    txtIdUsuario.setText(textoId);
                }
            } catch (NumberFormatException ex) {
                limpiarCampos();
            }
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
                String nombre = txtNombre.getText().trim();
                String apellidos = txtApellidos.getText().trim();
                String correo = txtCorreo.getText().trim();
                String password = new String(txtPassword.getPassword()).trim();
                int nivelAcceso = cbNivelAcceso.getSelectedIndex();

                if (nombre.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean existe = false;
                for (int i = 0; i < listaUsuarios.size(); i++) {
                    Usuario u = listaUsuarios.get(i);
                    if (u.getIdUsuario() == idUsuario) {
                        u.setNombre(nombre);
                        u.setApellidos(apellidos);
                        u.setCorreo(correo);
                        u.setPassword(password);
                        u.setNivelAcceso(nivelAcceso);
                        existe = true;
                        break;
                    }
                }

                if (!existe) {
                    Usuario nuevoUsuario = new Usuario(idUsuario, nombre, apellidos, correo, password, nivelAcceso);
                    listaUsuarios.add(nuevoUsuario);
                    JOptionPane.showMessageDialog(this, "Usuario guardado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Usuario modificado exitosamente.");
                }

                guardarUsuariosEnArchivo();
                limpiarCampos();
            }

            if (e.getSource() == btnEliminar) {
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

            if (e.getSource() == btnLimpiar) {
                limpiarCampos();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
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
