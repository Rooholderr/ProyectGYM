package Principal;

public class Sala {
    private int idSala;
    private String nombreSala;
    private String descripcionSala;
    private int idLocalizacionSala;

    public Sala(int idSala, String nombreSala, String descripcionSala, int idLocalizacionSala) {
        this.idSala = idSala;
        this.nombreSala = nombreSala;
        this.descripcionSala = descripcionSala;
        this.idLocalizacionSala = idLocalizacionSala;
    }

    // Getters y Setters
    public int getIdSala() { return idSala; }
    public void setIdSala(int idSala) { this.idSala = idSala; }

    public String getNombreSala() { return nombreSala; }
    public void setNombreSala(String nombreSala) { this.nombreSala = nombreSala; }

    public String getDescripcionSala() { return descripcionSala; }
    public void setDescripcionSala(String descripcionSala) { this.descripcionSala = descripcionSala; }

    public int getIdLocalizacionSala() { return idLocalizacionSala; }
    public void setIdLocalizacionSala(int idLocalizacionSala) { this.idLocalizacionSala = idLocalizacionSala; }
}
