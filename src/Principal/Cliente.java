package Principal;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String apellidoPat;
    private String apellidoMat;
    private String direccion;
    private String fechaNacimiento;
    private String telefono;
    private String celular;
    private String fechaIngreso;
    private String correo;
    private double balance;
    private double cuota;
    private String status;
    private String tipo;

    public Cliente(int idCliente, String nombre, String apellidoPat, String apellidoMat, String direccion, 
                   String fechaNacimiento, String telefono, String celular, String fechaIngreso, 
                   String correo, double balance, double cuota, String status, String tipo) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidoPat = apellidoPat;
        this.apellidoMat = apellidoMat;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.celular = celular;
        this.fechaIngreso = fechaIngreso;
        this.correo = correo;
        this.balance = balance;
        this.cuota = cuota;
        this.status = status;
        this.tipo = tipo;
    }

    // Getters y Setters
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidoPat() { return apellidoPat; }
    public void setApellidoPat(String apellidoPat) { this.apellidoPat = apellidoPat; }

    public String getApellidoMat() { return apellidoMat; }
    public void setApellidoMat(String apellidoMat) { this.apellidoMat = apellidoMat; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }

    public String getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(String fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public double getCuota() { return cuota; }
    public void setCuota(double cuota) { this.cuota = cuota; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
