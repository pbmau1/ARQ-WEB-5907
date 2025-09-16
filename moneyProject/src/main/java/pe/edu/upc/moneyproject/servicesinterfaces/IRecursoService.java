package pe.edu.upc.moneyproject.servicesinterfaces;

import pe.edu.upc.moneyproject.entities.Recurso;
import pe.edu.upc.moneyproject.entities.Usuario;

import java.util.List;


public interface IRecursoService {
    public void insert(Recurso recurso);
    public List<Recurso> findAll();
    public Recurso listId(int id);
    public void update(Recurso recurso);
    public void delete(int id);
}
