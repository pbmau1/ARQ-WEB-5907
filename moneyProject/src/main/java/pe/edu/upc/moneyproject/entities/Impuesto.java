package pe.edu.upc.moneyproject.entities;

import jakarta.persistence.*;
import pe.edu.upc.moneyproject.entities.Usuario;

import java.time.LocalDate;

@Entity
@Table(name = "Impuesto")
public class Impuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idImpuesto;

    @Column(name = "tipoImpuesto", length = 100, nullable = false)
    private String tipo;

    @Column(name = "montoBase", nullable = false)
    private double montoBase;

    @Column(name = "tasa", nullable = false)
    private double tasa;

    @Column(name = "montoCalculado", nullable = false)
    private double montoCalculado;

    @Column(name = "fechaCalculo", nullable = false)
    private LocalDate fechaCalculo;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    // Constructor con parámetros
    public Impuesto(int idImpuesto, String tipo, double montoBase, double tasa,
                    double montoCalculado, LocalDate fechaCalculo, Usuario usuario) {
        this.idImpuesto = idImpuesto;
        this.tipo = tipo;
        this.montoBase = montoBase;
        this.tasa = tasa;
        this.montoCalculado = montoCalculado;
        this.fechaCalculo = fechaCalculo;
        this.usuario = usuario;
    }

    // Constructor vacío
    public Impuesto() {
    }

    // Getters y Setters
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

    public double getMontoBase() {
        return montoBase;
    }

    public void setMontoBase(double montoBase) {
        this.montoBase = montoBase;
    }

    public double getTasa() {
        return tasa;
    }

    public void setTasa(double tasa) {
        this.tasa = tasa;
    }

    public double getMontoCalculado() {
        return montoCalculado;
    }

    public void setMontoCalculado(double montoCalculado) {
        this.montoCalculado = montoCalculado;
    }

    public LocalDate getFechaCalculo() {
        return fechaCalculo;
    }

    public void setFechaCalculo(LocalDate fechaCalculo) {
        this.fechaCalculo = fechaCalculo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

