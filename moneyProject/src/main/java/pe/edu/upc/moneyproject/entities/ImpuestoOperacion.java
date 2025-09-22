package pe.edu.upc.moneyproject.entities;

import jakarta.persistence.*;

@Entity
@Table(name="ImpuestoOperacion")
public class ImpuestoOperacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idImpuestoOperacion;

    @ManyToOne
    @JoinColumn(name = "id_operacion", nullable = false)
    private Operacion operacion;

    @ManyToOne
    @JoinColumn(name = "id_impuesto", nullable = false)
    private Impuesto impuesto;

    public ImpuestoOperacion(int idImpuestoOperacion, Operacion operacion, Impuesto impuesto) {
        this.idImpuestoOperacion = idImpuestoOperacion;
        this.operacion = operacion;
        this.impuesto = impuesto;
    }

    public ImpuestoOperacion() {

    }

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
