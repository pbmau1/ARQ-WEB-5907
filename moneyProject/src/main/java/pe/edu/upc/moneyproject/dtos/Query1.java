package pe.edu.upc.moneyproject.dtos;

public class Query1 {
    public String nombre;
    private double total_ahorro;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTotal_ahorro() {
        return total_ahorro;
    }

    public void setTotal_ahorro(double total_ahorro) {
        this.total_ahorro = total_ahorro;
    }
}
