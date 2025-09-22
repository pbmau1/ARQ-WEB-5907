package pe.edu.upc.moneyproject.dtos;

import pe.edu.upc.moneyproject.entities.Recurso;
import pe.edu.upc.moneyproject.entities.Usuario;

public class RecursoUsuarioDTO {
    private int idRecursoUsuario;
    private Usuario usuario;
    private Recurso recurso;

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
