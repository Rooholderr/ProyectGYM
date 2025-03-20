package Principal;

public class EstadoReserva {
    private int idEstadoReserva;
    private String estado;
    private boolean activo;

    public EstadoReserva(int idEstadoReserva, String estado, boolean activo) {
        this.idEstadoReserva = idEstadoReserva;
        this.estado = estado;
        this.activo = activo;
    }

    // Getters y Setters
    public int getIdEstadoReserva() { return idEstadoReserva; }
    public void setIdEstadoReserva(int idEstadoReserva) { this.idEstadoReserva = idEstadoReserva; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
