package pe.edu.upc.moneyproject.dtos;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import pe.edu.upc.moneyproject.entities.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OperacionDTO {
    private int idOperacion;
    private String categoria;
    private String monto;
    private String tipo;
    private String detalle;
    private LocalDateTime fecha;
    private Usuario usuario;

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
