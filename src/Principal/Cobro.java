package Principal;

public class Cobro {
    private int idCobro;
    private String fechaCobro;
    private int idClienteCobro;
    private double valorCobro;
    private String conceptoCobro;
    private boolean statusCobro;

    public Cobro(int idCobro, String fechaCobro, int idClienteCobro, double valorCobro, String conceptoCobro, boolean statusCobro) {
        this.idCobro = idCobro;
        this.fechaCobro = fechaCobro;
        this.idClienteCobro = idClienteCobro;
        this.valorCobro = valorCobro;
        this.conceptoCobro = conceptoCobro;
        this.statusCobro = statusCobro;
    }

    // Getters y Setters
    public int getIdCobro() { return idCobro; }
    public void setIdCobro(int idCobro) { this.idCobro = idCobro; }

    public String getFechaCobro() { return fechaCobro; }
    public void setFechaCobro(String fechaCobro) { this.fechaCobro = fechaCobro; }

    public int getIdClienteCobro() { return idClienteCobro; }
    public void setIdClienteCobro(int idClienteCobro) { this.idClienteCobro = idClienteCobro; }

    public double getValorCobro() { return valorCobro; }
    public void setValorCobro(double valorCobro) { this.valorCobro = valorCobro; }

    public String getConceptoCobro() { return conceptoCobro; }
    public void setConceptoCobro(String conceptoCobro) { this.conceptoCobro = conceptoCobro; }

    public boolean isStatusCobro() { return statusCobro; }
    public void setStatusCobro(boolean statusCobro) { this.statusCobro = statusCobro; }
}
