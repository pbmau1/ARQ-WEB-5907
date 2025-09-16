package pe.edu.upc.moneyproject.entities;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Table(name="Recurso")
public class Recurso {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idRecurso;

    @Column(name="Titulo",length = 100,nullable = false)
    private String titulo;

    @Column(name="Descripcion",length = 100, nullable = false)
    private String descripcion;

    @Column(name="Tipo",length = 100,nullable = false)
    private String tipo;

    @Column(name="Autor",length = 100,nullable = false)
    private String autor;

    @Column(name="Fuente",length = 100, nullable = false)
    private String fuente;

    @Column(name="URL",length = 100,nullable = false)
    private String url;

    @Column(name="FechaPublicacion",length = 100,nullable = false)
    private LocalDate fechapublicacion;

    @Column(name="FechaSubida",length = 100,nullable = false)
    private LocalDate fechasubida;


    public Recurso(int idRecurso, String titulo, String descripcion, String tipo, String autor, String fuente, String url, LocalDate fechapublicacion, LocalDate fechasubida) {
        this.idRecurso = idRecurso;
        this.titulo = titulo;
        this.descripcion= descripcion;
        this.tipo = tipo;
        this.autor = autor;
        this.fuente = fuente;
        this.url=url;
        this.fechapublicacion=fechapublicacion;
        this.fechasubida=fechasubida;
    }

    public Recurso() {
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDate getFechapublicacion() {
        return fechapublicacion;
    }

    public void setFechapublicacion(LocalDate fechapublicacion) {
        this.fechapublicacion = fechapublicacion;
    }

    public LocalDate getFechasubida() {
        return fechasubida;
    }

    public void setFechasubida(LocalDate fechasubida) {
        this.fechasubida = fechasubida;
    }
}

