package pe.edu.upc.moneyproject.servicesinterfaces;

import pe.edu.upc.moneyproject.entities.RecursoUsuario;

import java.util.List;

public interface IRecursoUsuarioService {
    public List<RecursoUsuario> findAll();
    public RecursoUsuario insert(RecursoUsuario recursoUsuario);
    public void update(RecursoUsuario recursoUsuario);
    public void delete(int id);
    public RecursoUsuario listId(int id);
}
