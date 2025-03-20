package Principal;

public class Reserva {
    private int idReserva;
    private int idSala;
    private int idCliente;
    private String fechaReserva;
    private int idHorarioReserva;
    private int idEstadoReserva;

    public Reserva(int idReserva, int idSala, int idCliente, String fechaReserva, int idHorarioReserva, int idEstadoReserva) {
        this.idReserva = idReserva;
        this.idSala = idSala;
        this.idCliente = idCliente;
        this.fechaReserva = fechaReserva;
        this.idHorarioReserva = idHorarioReserva;
        this.idEstadoReserva = idEstadoReserva;
    }

    // Getters y Setters
    public int getIdReserva() { return idReserva; }
    public void setIdReserva(int idReserva) { this.idReserva = idReserva; }

    public int getIdSala() { return idSala; }
    public void setIdSala(int idSala) { this.idSala = idSala; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(String fechaReserva) { this.fechaReserva = fechaReserva; }

    public int getIdHorarioReserva() { return idHorarioReserva; }
    public void setIdHorarioReserva(int idHorarioReserva) { this.idHorarioReserva = idHorarioReserva; }

    public int getIdEstadoReserva() { return idEstadoReserva; }
    public void setIdEstadoReserva(int idEstadoReserva) { this.idEstadoReserva = idEstadoReserva; }
}
