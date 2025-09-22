package pe.edu.upc.moneyproject.dtos;

public class SumaOpPorUsDTO {
    private int idUsuario;
    private String nombre;
    private double totalOperaciones;

    public SumaOpPorUsDTO() {
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

    public double getTotalOperaciones() {
        return totalOperaciones;
    }

    public void setTotalOperaciones(double totalOperaciones) {
        this.totalOperaciones = totalOperaciones;
    }
}
