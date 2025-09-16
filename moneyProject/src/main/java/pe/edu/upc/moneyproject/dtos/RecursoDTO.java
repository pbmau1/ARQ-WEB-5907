package pe.edu.upc.moneyproject.dtos;


import java.time.LocalDate;

public class RecursoDTO {
    private int idRecurso;
    private String titulo;
    private String descripcion;
    private String tipo;
    private String autor;
    private String fuente;
    private String url;
    private LocalDate fechapublicacion;
    private LocalDate fechasubida;

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
