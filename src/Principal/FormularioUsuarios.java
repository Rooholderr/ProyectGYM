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
    private JButton btnGuardar, btnMostrar;

    // ✅ Lista de usuarios
    public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private static final String ARCHIVO_USUARIOS = "usuarios.txt";

    public FormularioUsuarios() {
        setTitle("Gestión de Usuarios");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // ✅ Cargar usuarios desde el archivo
        cargarUsuariosDesdeArchivo();

        // ✅ Agregar usuario ADMIN "Ronald" si no está en la lista
        agregarAdministradorFijo();

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("ID Usuario:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtIdUsuario = new JTextField(15);
        panel.add(txtIdUsuario, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtNombre = new JTextField(15);
        panel.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Apellidos:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtApellidos = new JTextField(15);
        panel.add(txtApellidos, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Correo Electrónico:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtCorreo = new JTextField(15);
        panel.add(txtCorreo, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtPassword = new JPasswordField(15);
        panel.add(txtPassword, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Nivel de Acceso:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        String[] niveles = {"0 - Administrador", "1 - Usuario Normal"};
        cbNivelAcceso = new JComboBox<>(niveles);
        panel.add(cbNivelAcceso, gbc);

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(this);
        panel.add(btnGuardar, gbc);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            try {
                int idUsuario = Integer.parseInt(txtIdUsuario.getText().trim());
                String nombre = txtNombre.getText().trim();
                String apellidos = txtApellidos.getText().trim();
                String correo = txtCorreo.getText().trim();
                String password = new String(txtPassword.getPassword()).trim();
                int nivelAcceso = cbNivelAcceso.getSelectedIndex(); // 0 o 1

                if (nombre.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Guardar usuario en la lista y archivo
                Usuario nuevoUsuario = new Usuario(idUsuario, nombre, apellidos, correo, password, nivelAcceso);
                listaUsuarios.add(nuevoUsuario);
                guardarUsuariosEnArchivo();
                JOptionPane.showMessageDialog(this, "Usuario guardado exitosamente.");
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limpiarCampos() {
        txtIdUsuario.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtCorreo.setText("");
        txtPassword.setText("");
        cbNivelAcceso.setSelectedIndex(0);
    }

    // ✅ Agregar usuario ADMIN "Ronald" si no está en la lista
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
            guardarUsuariosEnArchivo(); // ✅ Guardar en archivo si no existía
        }
    }

    // ✅ Guardar usuarios en el archivo
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

    // ✅ Cargar usuarios desde el archivo
    private void cargarUsuariosDesdeArchivo() {
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
