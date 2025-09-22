package pe.edu.upc.moneyproject.entities;

import jakarta.persistence.*;

@Entity
@Table(name="RecursoUsuario")
public class RecursoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRecursoUsuario;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_recurso", nullable = false)
    private Recurso recurso;

    public RecursoUsuario(int idRecursoUsuario, Usuario usuario, Recurso recurso) {
        this.idRecursoUsuario = idRecursoUsuario;
        this.usuario = usuario;
        this.recurso = recurso;
    }

    public RecursoUsuario() {

    }

    public int getIdRecursoUsuario() {
        return idRecursoUsuario;
    }

    public void setIdRecursoUsuario(int idRecursoUsuario) {
        this.idRecursoUsuario = idRecursoUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }
}
