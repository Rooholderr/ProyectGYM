package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Login extends JFrame implements ActionListener {
    private JTextField txtNombre;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    private static final String ARCHIVO_USUARIOS = "usuarios.txt";

    public Login() {
        setTitle("Inicio de Sesión");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ✅ Asegurar que el usuario "Ronald" existe en el archivo antes de abrir el login
        verificarAdminEnArchivo();

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Nombre Usuario:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtNombre = new JTextField(15);
        panel.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        txtPassword = new JPasswordField(15);
        panel.add(txtPassword, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.addActionListener(this);
        panel.add(btnLogin, gbc);

        add(panel);
        setVisible(true);
    }

@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnLogin) {
        String nombre = txtNombre.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (nombre.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese su nombre y contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ✅ Validar usuario en archivo
        int nivelAcceso = validarUsuarioEnArchivo(nombre, password);
        if (nivelAcceso != -1) {
            JOptionPane.showMessageDialog(this, "Bienvenido " + nombre, "Acceso permitido", JOptionPane.INFORMATION_MESSAGE);
            this.dispose(); // Cerrar login
            new MenuPrincipal(nivelAcceso, nombre); // ✅ Pasamos el nombre al menú
        } else {
            JOptionPane.showMessageDialog(this, "Nombre o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            txtPassword.setText(""); // Limpiar campo contraseña
        }
    }
}


    // ✅ Validar usuario en archivo `usuarios.txt`
    private int validarUsuarioEnArchivo(String nombre, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 6) {
                    String nombreArchivo = datos[1].trim();
                    String passwordArchivo = datos[4].trim();
                    int nivelAcceso = Integer.parseInt(datos[5].trim());

                    if (nombreArchivo.equalsIgnoreCase(nombre) && passwordArchivo.equals(password)) {
                        return nivelAcceso; // ✅ Retorna el nivel de acceso si coincide
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de usuarios.");
        }
        return -1; // ❌ Usuario no encontrado
    }

    // ✅ Verificar si el administrador "Ronald" está en el archivo
    private void verificarAdminEnArchivo() {
        boolean existeAdmin = false;

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 6 && datos[1].trim().equalsIgnoreCase("Ronald") && datos[4].trim().equals("12345")) {
                    existeAdmin = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("El archivo de usuarios no existe, se creará uno nuevo.");
        }

        // ✅ Si Ronald no existe en el archivo, lo agregamos
        if (!existeAdmin) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_USUARIOS, true))) {
                bw.write("1,Ronald,Administrador,admin@gmail.com,12345,0");
                bw.newLine();
                System.out.println("Usuario Ronald agregado al archivo.");
            } catch (IOException e) {
                System.out.println("Error al escribir en el archivo.");
            }
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
