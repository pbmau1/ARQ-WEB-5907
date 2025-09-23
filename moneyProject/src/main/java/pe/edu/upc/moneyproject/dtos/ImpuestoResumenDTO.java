package pe.edu.upc.moneyproject.dtos;

public class ImpuestoResumenDTO {
    private String tipo;
    private Double montoTotal;

    public ImpuestoResumenDTO(String tipo, Double montoTotal) {
        this.tipo = tipo;
        this.montoTotal = montoTotal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }
}


