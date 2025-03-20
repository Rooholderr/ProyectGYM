package Principal;

public class EncabezadoCuota {
    private int idCuota;
    private String fechaCuota;
    private int idClienteCuota;
    private double valorCobro;
    private boolean statusCuota;

    public EncabezadoCuota(int idCuota, String fechaCuota, int idClienteCuota, double valorCobro, boolean statusCuota) {
        this.idCuota = idCuota;
        this.fechaCuota = fechaCuota;
        this.idClienteCuota = idClienteCuota;
        this.valorCobro = valorCobro;
        this.statusCuota = statusCuota;
    }

    // Getters y Setters
    public int getIdCuota() { return idCuota; }
    public void setIdCuota(int idCuota) { this.idCuota = idCuota; }

    public String getFechaCuota() { return fechaCuota; }
    public void setFechaCuota(String fechaCuota) { this.fechaCuota = fechaCuota; }

    public int getIdClienteCuota() { return idClienteCuota; }
    public void setIdClienteCuota(int idClienteCuota) { this.idClienteCuota = idClienteCuota; }

    public double getValorCobro() { return valorCobro; }
    public void setValorCobro(double valorCobro) { this.valorCobro = valorCobro; }

    public boolean isStatusCuota() { return statusCuota; }
    public void setStatusCuota(boolean statusCuota) { this.statusCuota = statusCuota; }

    String getIdCliente() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
