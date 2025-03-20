package Principal;

public class DetalleCuota {
    private int idCuota;
    private int secCuota;
    private String conceptoCuota;
    private double valorCuota;
    private int idCobroCuota;

    public DetalleCuota(int idCuota, int secCuota, String conceptoCuota, double valorCuota, int idCobroCuota) {
        this.idCuota = idCuota;
        this.secCuota = secCuota;
        this.conceptoCuota = conceptoCuota;
        this.valorCuota = valorCuota;
        this.idCobroCuota = idCobroCuota;
    }

    // Getters y Setters
    public int getIdCuota() { return idCuota; }
    public void setIdCuota(int idCuota) { this.idCuota = idCuota; }

    public int getSecCuota() { return secCuota; }
    public void setSecCuota(int secCuota) { this.secCuota = secCuota; }

    public String getConceptoCuota() { return conceptoCuota; }
    public void setConceptoCuota(String conceptoCuota) { this.conceptoCuota = conceptoCuota; }

    public double getValorCuota() { return valorCuota; }
    public void setValorCuota(double valorCuota) { this.valorCuota = valorCuota; }

    public int getIdCobroCuota() { return idCobroCuota; }
    public void setIdCobroCuota(int idCobroCuota) { this.idCobroCuota = idCobroCuota; }
}
