package Principal;

public class Localizacion {
    private int idLocalizacion;
    private String tipo;

    public Localizacion(int idLocalizacion, String tipo) {
        this.idLocalizacion = idLocalizacion;
        this.tipo = tipo;
    }

    // Getters y Setters
    public int getIdLocalizacion() { return idLocalizacion; }
    public void setIdLocalizacion(int idLocalizacion) { this.idLocalizacion = idLocalizacion; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
