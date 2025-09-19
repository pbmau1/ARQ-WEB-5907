package pe.edu.upc.moneyproject.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="Operacion")

public class Operacion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idOperacion;

    @Column(name="categoriaOperacion",length = 100,nullable = false)
    private String categoria;

    @Column(name="montoOperacion",nullable = false)
    private String monto;

    @Column(name="tipoOperacion",nullable = false)
    private String tipo;

    @Column(name="detalleOperacion",nullable = false)
    private String detalle;

    @CreationTimestamp
    @Column(name="fechaOperacion", nullable = false, updatable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuario;

    public Operacion(int idOperacion, String categoria, String monto, String tipo, String detalle, LocalDate fecha, Usuario usuario) {
        this.idOperacion = idOperacion;
        this.categoria = categoria;
        this.monto = monto;
        this.tipo = tipo;
        this.detalle = detalle;
        this.fecha = LocalDateTime.now();
        this.usuario = usuario;
    }

    public Operacion() {

    }

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
