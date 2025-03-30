package Principal;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal(int nivelAcceso, String usuarioLogueado) {
        setTitle("IronZone - Sistema de Gestión");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Imagen de fondo (2046x1184 ajustada a 900x600)
        ImageIcon fondoOriginal = new ImageIcon(getClass().getResource("/Principal/real.png"));
        Image imagenEscalada = fondoOriginal.getImage().getScaledInstance(900, 600, Image.SCALE_SMOOTH);
        JLabel fondo = new JLabel(new ImageIcon(imagenEscalada));
        fondo.setLayout(new BorderLayout());
        setContentPane(fondo);

        // Menú bar modernizado
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(30, 30, 30));
        menuBar.setForeground(Color.WHITE);
        menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        // Menús principales
        JMenu menuMantenimiento = crearMenu("Mantenimientos");
        JMenu menuMovimientos = crearMenu("Movimientos");
        JMenu menuProcesos = crearMenu("Procesos");
        JMenu menuConsultas = crearMenu("Consultas");

        // Ítems de Mantenimiento
        JMenuItem itemUsuarios = crearItem("Usuarios");
        JMenuItem itemEntrenadores = crearItem("Entrenador");
        JMenuItem itemLocalizacion = crearItem("Localización");
        JMenuItem itemSalas = crearItem("Salas");
        JMenuItem itemActividades = crearItem("Actividades");
        JMenuItem itemHorariosActividades = crearItem("Horarios Actividades");
        JMenuItem itemClientes = crearItem("Clientes");
        JMenuItem itemEstadoReservas = crearItem("Estado Reservas");
        JMenuItem itemReservas = crearItem("Reservas");
        JMenuItem itemReservaActividades = crearItem("Reserva Actividades");

        menuMantenimiento.add(itemUsuarios);
        menuMantenimiento.add(itemEntrenadores);
        menuMantenimiento.add(itemLocalizacion);
        menuMantenimiento.add(itemSalas);
        menuMantenimiento.add(itemActividades);
        menuMantenimiento.add(itemHorariosActividades);
        menuMantenimiento.add(itemClientes);
        menuMantenimiento.add(itemEstadoReservas);
        menuMantenimiento.add(itemReservas);
        menuMantenimiento.add(itemReservaActividades);

        // Ítems de Movimientos
        JMenuItem itemCuotas = crearItem("Cuotas");
        itemCuotas.addActionListener(e -> new FormularioEncabezadoCuota());
        menuMovimientos.add(itemCuotas);

        // Ítems de Procesos
        JMenuItem itemGenerarCobro = crearItem("Generar Cobro");
        JMenuItem itemReversarCobro = crearItem("Reversar Cobro");
        JMenuItem itemActualizarCuota = crearItem("Actualizar Cuota");

        itemGenerarCobro.addActionListener(e -> new FormularioCobros());
        itemReversarCobro.addActionListener(e -> JOptionPane.showMessageDialog(null, "Funcionalidad de reversar cobro en desarrollo."));
        itemActualizarCuota.addActionListener(e -> JOptionPane.showMessageDialog(null, "Funcionalidad de actualizar cuota en desarrollo."));

        menuProcesos.add(itemGenerarCobro);
        menuProcesos.add(itemReversarCobro);
        menuProcesos.add(itemActualizarCuota);

        // Ítems de Consultas
        JMenuItem itemConsultaUsuarios = crearItem("Consulta de Usuarios");
        JMenuItem itemConsultaEntrenadores = crearItem("Consulta de Entrenadores");
        JMenuItem itemConsultaLocalizacion = crearItem("Consulta de Localización");
        JMenuItem itemConsultaSalas = crearItem("Consulta de Salas");
        JMenuItem itemConsultaActividades = crearItem("Consulta de Actividades");
        JMenuItem itemConsultaHorarios = crearItem("Consulta de Horarios Actividades");
        JMenuItem itemConsultaCobroFecha = crearItem("Cobro por Rango de Fecha");
        JMenuItem itemConsultaCobroCliente = crearItem("Cobro por Cliente");
        JMenuItem itemConsultaCuotaFecha = crearItem("Cuota por Fecha");
        JMenuItem itemConsultaCuotaCliente = crearItem("Cuota por Cliente");
        JMenuItem itemConsultaClientes = crearItem("Consulta de Clientes");
        JMenuItem itemConsultaClientesBalance = crearItem("Clientes con Balance Pendiente");

        menuConsultas.add(itemConsultaUsuarios);
        menuConsultas.add(itemConsultaEntrenadores);
        menuConsultas.add(itemConsultaLocalizacion);
        menuConsultas.add(itemConsultaSalas);
        menuConsultas.add(itemConsultaActividades);
        menuConsultas.add(itemConsultaHorarios);
        menuConsultas.add(itemConsultaCobroFecha);
        menuConsultas.add(itemConsultaCobroCliente);
        menuConsultas.add(itemConsultaCuotaFecha);
        menuConsultas.add(itemConsultaCuotaCliente);
        menuConsultas.add(itemConsultaClientes);
        menuConsultas.add(itemConsultaClientesBalance);

        // Acciones (conexiones)
        itemHorariosActividades.addActionListener(e -> new FormularioHorariosActividades());
        itemActividades.addActionListener(e -> new FormularioActividades());
        itemSalas.addActionListener(e -> new FormularioSalas());
        itemLocalizacion.addActionListener(e -> new FormularioLocalizacion());
        itemEntrenadores.addActionListener(e -> new FormularioEntrenadores());
        itemUsuarios.addActionListener(e -> new FormularioUsuarios());
        itemClientes.addActionListener(e -> new FormularioClientes());
        itemEstadoReservas.addActionListener(e -> new FormularioEstadoReservas());
        itemReservas.addActionListener(e -> new FormularioReservas());
        itemReservaActividades.addActionListener(e -> new FormularioReservaActividades());

        // Control de acceso
        boolean esRonald = usuarioLogueado.equalsIgnoreCase("Ronald");
        if (!esRonald && nivelAcceso == 1) {
            menuMantenimiento.setEnabled(false);
            itemGenerarCobro.setEnabled(false);
            itemReversarCobro.setEnabled(false);
            itemActualizarCuota.setEnabled(false);
        }

        // Agregar menús a la barra
        menuBar.add(menuMantenimiento);
        menuBar.add(menuMovimientos);
        menuBar.add(menuProcesos);
        menuBar.add(menuConsultas);
        setJMenuBar(menuBar);

        setVisible(true);
    }

    // Métodos auxiliares para crear menús con estilo
    private JMenu crearMenu(String texto) {
        JMenu menu = new JMenu(texto);
        menu.setFont(new Font("Segoe UI", Font.BOLD, 16));
        menu.setForeground(Color.WHITE);
        return menu;
    }

    private JMenuItem crearItem(String texto) {
        JMenuItem item = new JMenuItem(texto);
        item.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return item;
    }
}
