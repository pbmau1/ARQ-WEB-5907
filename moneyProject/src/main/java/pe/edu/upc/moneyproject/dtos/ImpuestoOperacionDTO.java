package pe.edu.upc.moneyproject.dtos;

import pe.edu.upc.moneyproject.entities.Impuesto;
import pe.edu.upc.moneyproject.entities.Operacion;

public class ImpuestoOperacionDTO {
    private int idImpuestoOperacion;
    private Operacion operacion;
    private Impuesto impuesto;

    public int getIdImpuestoOperacion() {
        return idImpuestoOperacion;
    }

    public void setIdImpuestoOperacion(int idImpuestoOperacion) {
        this.idImpuestoOperacion = idImpuestoOperacion;
    }

    public Operacion getOperacion() {
        return operacion;
    }

    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }

    public Impuesto getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuesto impuesto) {
        this.impuesto = impuesto;
    }
}
