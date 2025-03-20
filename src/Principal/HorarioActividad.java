package Principal;

public class HorarioActividad {
    private int idHorarioActividad;
    private String diaActividad;
    private String horaActividad;
    private int idActividad;

    public HorarioActividad(int idHorarioActividad, String diaActividad, String horaActividad, int idActividad) {
        this.idHorarioActividad = idHorarioActividad;
        this.diaActividad = diaActividad;
        this.horaActividad = horaActividad;
        this.idActividad = idActividad;
    }

    // Getters y Setters
    public int getIdHorarioActividad() { return idHorarioActividad; }
    public void setIdHorarioActividad(int idHorarioActividad) { this.idHorarioActividad = idHorarioActividad; }

    public String getDiaActividad() { return diaActividad; }
    public void setDiaActividad(String diaActividad) { this.diaActividad = diaActividad; }

    public String getHoraActividad() { return horaActividad; }
    public void setHoraActividad(String horaActividad) { this.horaActividad = horaActividad; }

    public int getIdActividad() { return idActividad; }
    public void setIdActividad(int idActividad) { this.idActividad = idActividad; }
}
