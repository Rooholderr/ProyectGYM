package Principal;

public class ReservaActividad {
    private int idReservaActividad;
    private String fechaReserva;
    private String fechaBaja;
    private int idEstadoReserva;
    private int idCliente;
    private int idActividad;
    private int idHorarioActividad;

    public ReservaActividad(int idReservaActividad, String fechaReserva, String fechaBaja, int idEstadoReserva, 
                            int idCliente, int idActividad, int idHorarioActividad) {
        this.idReservaActividad = idReservaActividad;
        this.fechaReserva = fechaReserva;
        this.fechaBaja = fechaBaja;
        this.idEstadoReserva = idEstadoReserva;
        this.idCliente = idCliente;
        this.idActividad = idActividad;
        this.idHorarioActividad = idHorarioActividad;
    }

    // Getters y Setters
    public int getIdReservaActividad() { return idReservaActividad; }
    public void setIdReservaActividad(int idReservaActividad) { this.idReservaActividad = idReservaActividad; }

    public String getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(String fechaReserva) { this.fechaReserva = fechaReserva; }

    public String getFechaBaja() { return fechaBaja; }
    public void setFechaBaja(String fechaBaja) { this.fechaBaja = fechaBaja; }

    public int getIdEstadoReserva() { return idEstadoReserva; }
    public void setIdEstadoReserva(int idEstadoReserva) { this.idEstadoReserva = idEstadoReserva; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdActividad() { return idActividad; }
    public void setIdActividad(int idActividad) { this.idActividad = idActividad; }

    public int getIdHorarioActividad() { return idHorarioActividad; }
    public void setIdHorarioActividad(int idHorarioActividad) { this.idHorarioActividad = idHorarioActividad; }
}
