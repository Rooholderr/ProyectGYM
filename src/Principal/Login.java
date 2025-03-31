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
        setTitle("IRON ZONE GYM");
        setSize(450, 600); // Tamaño aumentado
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        verificarAdminEnArchivo();

        // Panel principal con fondo blanco
        JPanel contenedor = new JPanel();
        contenedor.setLayout(null);
        contenedor.setBackground(Color.WHITE);
        setContentPane(contenedor);

        // Imagen superior (450 x 230)
        ImageIcon iconoPesas = new ImageIcon(getClass().getResource("/Principal/zone2.png")); // imagen
        Image imagen = iconoPesas.getImage().getScaledInstance(450, 230, Image.SCALE_SMOOTH);
        JLabel imagenArriba = new JLabel(new ImageIcon(imagen));
        imagenArriba.setBounds(0, 0, 450, 230);
        contenedor.add(imagenArriba);
        // Título centrado
        JLabel lblTitulo = new JLabel("INICIAR SESIÓN", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 23)); // Más grande
        lblTitulo.setForeground(Color.BLACK); // Letras negras
        lblTitulo.setBounds(0, 240, 450, 40); // Centrado horizontal total
        contenedor.add(lblTitulo);

        contenedor.add(lblTitulo);

        // USUARIO
        JLabel lblUsuario = new JLabel("USUARIO");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        lblUsuario.setBounds(50, 280, 200, 20);
        contenedor.add(lblUsuario);

        txtNombre = new JTextField();
        txtNombre.setBounds(50, 305, 330, 30);
        txtNombre.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        txtNombre.setForeground(Color.GRAY);
        txtNombre.setText("Ingrese su nombre de usuario");
        txtNombre.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (txtNombre.getText().equals("Ingrese su nombre de usuario")) {
                    txtNombre.setText("");
                    txtNombre.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (txtNombre.getText().isEmpty()) {
                    txtNombre.setForeground(Color.GRAY);
                    txtNombre.setText("Ingrese su nombre de usuario");
                }
            }
        });
        contenedor.add(txtNombre);

        // CONTRASEÑA
        JLabel lblPassword = new JLabel("CONTRASEÑA");
        lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
        lblPassword.setBounds(50, 350, 200, 20);
        contenedor.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(50, 375, 330, 30);
        txtPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        contenedor.add(txtPassword);

        // BOTÓN
        btnLogin = new JButton("Entrar");
        btnLogin.setBounds(170, 440, 100, 35);
        btnLogin.setBackground(Color.DARK_GRAY);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.addActionListener(this);
        contenedor.add(btnLogin);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            String nombre = txtNombre.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();

            if (nombre.isEmpty() || nombre.equals("Ingrese su nombre de usuario") || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese su nombre y contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            File archivo = new File(ARCHIVO_USUARIOS);
            if (!archivo.exists() || archivo.length() == 0) {
                JOptionPane.showMessageDialog(this, "No hay usuarios registrados en el sistema.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                boolean usuarioEncontrado = false;
                boolean passwordCorrecta = false;
                int nivelAcceso = -1;

                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length == 6) {
                        String nombreArchivo = datos[1].trim();
                        String passwordArchivo = datos[4].trim();

                        if (nombreArchivo.equalsIgnoreCase(nombre)) {
                            usuarioEncontrado = true;

                            if (passwordArchivo.equals(password)) {
                                passwordCorrecta = true;
                                nivelAcceso = Integer.parseInt(datos[5].trim());
                                break;
                            }
                        }
                    }
                }

                if (!usuarioEncontrado) {
                    JOptionPane.showMessageDialog(this, "El usuario no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                    txtNombre.requestFocus();
                } else if (!passwordCorrecta) {
                    JOptionPane.showMessageDialog(this, "Contraseña incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
                    txtPassword.setText("");
                    txtPassword.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(this, "Bienvenido " + nombre, "Acceso permitido", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    new MenuPrincipal(nivelAcceso, nombre);
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al leer el archivo de usuarios.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

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
