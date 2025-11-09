package pe.edu.upc.moneyproject.dtos;

public class SumaOpPorUsDTO {

    private int idUsuario;
    private String nombre;
    private double totalIngresos;
    private double totalGastos;

    public SumaOpPorUsDTO() {
    }

    public SumaOpPorUsDTO(int idUsuario, String nombre, double totalIngresos, double totalGastos) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.totalIngresos = totalIngresos;
        this.totalGastos = totalGastos;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(double totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public double getTotalGastos() {
        return totalGastos;
    }

    public void setTotalGastos(double totalGastos) {
        this.totalGastos = totalGastos;
    }
}