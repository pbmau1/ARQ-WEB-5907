package pe.edu.upc.moneyproject.dtos;

public class obtenerImpuestosIngresosDTO {
    private String categoriaDeImpuesto;
    private double tasa;
    private String tipoIngreso;
    private String categoriaDeOperacion;
    private double montoOperacion;
    private double montoFinal;

    public obtenerImpuestosIngresosDTO() {
    }

    public obtenerImpuestosIngresosDTO(String categoriaDeImpuesto, double tasa, String tipoIngreso, String categoriaDeOperacion, double montoOperacion, double montoFinal) {
        this.categoriaDeImpuesto = categoriaDeImpuesto;
        this.tasa = tasa;
        this.tipoIngreso = tipoIngreso;
        this.categoriaDeOperacion = categoriaDeOperacion;
        this.montoOperacion = montoOperacion;
        this.montoFinal = montoFinal;
    }

    public String getCategoriaDeImpuesto() {
        return categoriaDeImpuesto;
    }

    public void setCategoriaDeImpuesto(String categoriaDeImpuesto) {
        this.categoriaDeImpuesto = categoriaDeImpuesto;
    }

    public double getTasa() {
        return tasa;
    }

    public void setTasa(double tasa) {
        this.tasa = tasa;
    }

    public String getTipoIngreso() {
        return tipoIngreso;
    }

    public void setTipoIngreso(String tipoIngreso) {
        this.tipoIngreso = tipoIngreso;
    }

    public String getCategoriaDeOperacion() {
        return categoriaDeOperacion;
    }

    public void setCategoriaDeOperacion(String categoriaDeOperacion) {
        this.categoriaDeOperacion = categoriaDeOperacion;
    }

    public double getMontoOperacion() {
        return montoOperacion;
    }

    public void setMontoOperacion(double montoOperacion) {
        this.montoOperacion = montoOperacion;
    }

    public double getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(double montoFinal) {
        this.montoFinal = montoFinal;
    }
}
