package Principal;

public class Actividad {
    private int idActividad;
    private String nombreActividad;
    private String descripcionActividad;
    private int idLocalizacion;
    private int idEntrenador;

    public Actividad(int idActividad, String nombreActividad, String descripcionActividad, int idLocalizacion, int idEntrenador) {
        this.idActividad = idActividad;
        this.nombreActividad = nombreActividad;
        this.descripcionActividad = descripcionActividad;
        this.idLocalizacion = idLocalizacion;
        this.idEntrenador = idEntrenador;
    }

    // Getters y Setters
    public int getIdActividad() { return idActividad; }
    public void setIdActividad(int idActividad) { this.idActividad = idActividad; }

    public String getNombreActividad() { return nombreActividad; }
    public void setNombreActividad(String nombreActividad) { this.nombreActividad = nombreActividad; }

    public String getDescripcionActividad() { return descripcionActividad; }
    public void setDescripcionActividad(String descripcionActividad) { this.descripcionActividad = descripcionActividad; }

    public int getIdLocalizacion() { return idLocalizacion; }
    public void setIdLocalizacion(int idLocalizacion) { this.idLocalizacion = idLocalizacion; }

    public int getIdEntrenador() { return idEntrenador; }
    public void setIdEntrenador(int idEntrenador) { this.idEntrenador = idEntrenador; }
}
