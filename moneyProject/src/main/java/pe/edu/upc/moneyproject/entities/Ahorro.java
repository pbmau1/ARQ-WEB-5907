package pe.edu.upc.moneyproject.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="Ahorro")
public class Ahorro {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idAhorro;

    @Column(name="objetivoAhorro",length = 100,nullable = false)
    private String objetivo;

    @Column(name="montoAhorro",nullable = false)
    private double monto_actual;

    @Column(name="fecha_inAhorro",length = 50,nullable = false)
    private LocalDate fecha_inicio;

    @Column(name="fecha_limAhorro",length = 50,nullable = false)
    private LocalDate fecha_limite;

    @ManyToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuario;

    public Ahorro(int idAhorro, String objetivo, double monto_actual, LocalDate fecha_inicio, LocalDate fecha_limite, Usuario usuario) {
        this.idAhorro = idAhorro;
        this.objetivo = objetivo;
        this.monto_actual = monto_actual;
        this.fecha_inicio = fecha_inicio;
        this.fecha_limite = fecha_limite;
        this.usuario = usuario;
    }

    public Ahorro() {

    }

    public int getIdAhorro() {
        return idAhorro;
    }

    public void setIdAhorro(int idAhorro) {
        this.idAhorro = idAhorro;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public double getMonto_actual() {
        return monto_actual;
    }

    public void setMonto_actual(double monto_actual) {
        this.monto_actual = monto_actual;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_limite() {
        return fecha_limite;
    }

    public void setFecha_limite(LocalDate fecha_limite) {
        this.fecha_limite = fecha_limite;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
