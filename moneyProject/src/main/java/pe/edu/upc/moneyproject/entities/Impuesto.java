package pe.edu.upc.moneyproject.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Impuesto")
public class Impuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idImpuesto;

    @Column(name = "tipoImpuesto", length = 100, nullable = false)
    private String tipo;

    @Column(name = "tasa", nullable = false)
    private double tasa;

    public Impuesto(int idImpuesto, String tipo, double tasa) {
        this.idImpuesto = idImpuesto;
        this.tipo = tipo;
        this.tasa = tasa;
    }

    public Impuesto() {

    }

    public int getIdImpuesto() {
        return idImpuesto;
    }

    public void setIdImpuesto(int idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getTasa() {
        return tasa;
    }

    public void setTasa(double tasa) {
        this.tasa = tasa;
    }
}

