package Principal;

import javax.swing.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal(int nivelAcceso, String usuarioLogueado) {
        setTitle("Sistema de Gestión Gym");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        // ✅ Menú "Mantenimientos"
        JMenu menuMantenimiento = new JMenu("Mantenimientos");
        JMenuItem itemUsuarios = new JMenuItem("Usuarios");
        JMenuItem itemEntrenadores = new JMenuItem("Entrenador");
        JMenuItem itemLocalizacion = new JMenuItem("Localización");
        JMenuItem itemSalas = new JMenuItem("Salas");
        JMenuItem itemActividades = new JMenuItem("Actividades");
        JMenuItem itemHorariosActividades = new JMenuItem("Horarios Actividades");
        JMenuItem itemClientes = new JMenuItem("Clientes");
        JMenuItem itemEstadoReservas = new JMenuItem("Estado Reservas");
        JMenuItem itemReservas = new JMenuItem("Reservas");
        JMenuItem itemReservaActividades = new JMenuItem("Reserva Actividades");

        // ✅ Agregar elementos al menú Mantenimiento
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

        // ✅ Menú "Movimientos"
        JMenu menuMovimientos = new JMenu("Movimientos");
        JMenuItem itemCuotas = new JMenuItem("Cuotas");
        itemCuotas.addActionListener(e -> new FormularioEncabezadoCuota()); // Se vincula con el formulario de cuotas
        menuMovimientos.add(itemCuotas);

        // ✅ Menú "Procesos"
        JMenu menuProcesos = new JMenu("Procesos");
        JMenuItem itemGenerarCobro = new JMenuItem("Generar Cobro");
        JMenuItem itemReversarCobro = new JMenuItem("Reversar Cobro");
        JMenuItem itemActualizarCuota = new JMenuItem("Actualizar Cuota");

        // ✅ Conectar los formularios correspondientes
        itemGenerarCobro.addActionListener(e -> new FormularioCobros());
        itemReversarCobro.addActionListener(e -> JOptionPane.showMessageDialog(null, "Funcionalidad de reversar cobro en desarrollo."));
        itemActualizarCuota.addActionListener(e -> JOptionPane.showMessageDialog(null, "Funcionalidad de actualizar cuota en desarrollo."));

        menuProcesos.add(itemGenerarCobro);
        menuProcesos.add(itemReversarCobro);
        menuProcesos.add(itemActualizarCuota);

        // ✅ Menú "Consultas"
        JMenu menuConsultas = new JMenu("Consultas");
        JMenuItem itemConsultaUsuarios = new JMenuItem("Consulta de Usuarios");
        JMenuItem itemConsultaEntrenadores = new JMenuItem("Consulta de Entrenadores");
        JMenuItem itemConsultaLocalizacion = new JMenuItem("Consulta de Localización");
        JMenuItem itemConsultaSalas = new JMenuItem("Consulta de Salas");
        JMenuItem itemConsultaActividades = new JMenuItem("Consulta de Actividades");
        JMenuItem itemConsultaHorarios = new JMenuItem("Consulta de Horarios Actividades");
        JMenuItem itemConsultaCobroFecha = new JMenuItem("Cobro por Rango de Fecha");
        JMenuItem itemConsultaCobroCliente = new JMenuItem("Cobro por Cliente");
        JMenuItem itemConsultaCuotaFecha = new JMenuItem("Cuota por Fecha");
        JMenuItem itemConsultaCuotaCliente = new JMenuItem("Cuota por Cliente");
        JMenuItem itemConsultaClientes = new JMenuItem("Consulta de Clientes");
        JMenuItem itemConsultaClientesBalance = new JMenuItem("Clientes con Balance Pendiente");

        // ✅ Conectar clases con el menú
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

        // ✅ Agregar elementos al menú Consultas
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

        // ✅ Verificar si es "Ronald" para darle acceso total
        boolean esRonald = usuarioLogueado.equalsIgnoreCase("Ronald");

        if (!esRonald && nivelAcceso == 1) { // 🔒 Usuarios normales no pueden ver estos menús
            menuMantenimiento.setEnabled(false);
            itemGenerarCobro.setEnabled(false);
            itemReversarCobro.setEnabled(false);
            itemActualizarCuota.setEnabled(false);
        }

        menuBar.add(menuMantenimiento);
        menuBar.add(menuMovimientos);
        menuBar.add(menuProcesos);
        menuBar.add(menuConsultas);
        setJMenuBar(menuBar);
        setVisible(true);
    }
}
