package pe.edu.upc.moneyproject.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;

@Entity
@Table(name="Operacion")
public class Operacion {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idOperacion;

    @Column(name="categoriaOperacion", length = 100, nullable = false)
    private String categoria;

    @Column(name="montoOperacion", nullable = false)
    private int monto;

    @Column(name="tipoOperacion", nullable = false)
    private String tipo;

    @Column(name="detalleOperacion", nullable = false)
    private String detalle;

    // QUITAMOS @CreationTimestamp
    // Ahora la fecha puede ser enviada o editada libremente
    @Column(name="fechaOperacion", nullable = false)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name="idUsuario", nullable = false)
    @JsonIgnoreProperties({"operaciones"})
    private Usuario usuario;

    public Operacion() {}

    public Operacion(int idOperacion, String categoria, int monto, String tipo,
                     String detalle, LocalDate fecha, Usuario usuario) {
        this.idOperacion = idOperacion;
        this.categoria = categoria;
        this.monto = monto;
        this.tipo = tipo;
        this.detalle = detalle;
        this.fecha = fecha; // ✔ Se respeta la fecha enviada manualmente
        this.usuario = usuario;
    }

    public int getIdOperacion() { return idOperacion; }
    public void setIdOperacion(int idOperacion) { this.idOperacion = idOperacion; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public int getMonto() { return monto; }
    public void setMonto(int monto) { this.monto = monto; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDetalle() { return detalle; }
    public void setDetalle(String detalle) { this.detalle = detalle; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
