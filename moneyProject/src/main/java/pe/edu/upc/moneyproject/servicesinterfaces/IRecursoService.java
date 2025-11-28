package pe.edu.upc.moneyproject.servicesinterfaces;

import pe.edu.upc.moneyproject.entities.Recurso;

import java.time.LocalDate;
import java.util.List;


public interface IRecursoService {
    public void insert(Recurso recurso);
    public List<Recurso> findAll();
    public Recurso listId(int id);
    public void update(Recurso recurso);
    public void delete(int id);
    public List<Recurso> findRecursoByAutor(String autor);
    public List<Recurso>  findRecursoByFechaPublicacion(LocalDate fechaPublicacion);
}
