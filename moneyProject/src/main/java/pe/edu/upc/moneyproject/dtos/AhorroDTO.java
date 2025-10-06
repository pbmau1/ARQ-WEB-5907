package pe.edu.upc.moneyproject.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pe.edu.upc.moneyproject.entities.Usuario;

import java.time.LocalDate;
public class AhorroDTO {
    private int idAhorro;
    private String objetivo;
    private double monto_actual;
    private LocalDate fecha_inicio;
    private LocalDate fecha_limite;
    @JsonIgnoreProperties({"roles"})
    private Usuario usuario;


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
