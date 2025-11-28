package pe.edu.upc.moneyproject.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.moneyproject.entities.Recurso;
import pe.edu.upc.moneyproject.entities.Usuario;
import pe.edu.upc.moneyproject.repositories.IRecursoRepository;
import pe.edu.upc.moneyproject.servicesinterfaces.IRecursoService;

import java.time.LocalDate;
import java.util.List;

@Service
public class RecursoServiceImplement implements IRecursoService {
    @Autowired
    private IRecursoRepository rR;

    @Override
    public void insert(Recurso recurso) {rR.save(recurso);}

    @Override
    public List<Recurso> findAll() {return rR.findAll();}

    @Override
    public Recurso listId(int id) {
        return rR.findById(id).orElse(null);
    }

    @Override
    public void update(Recurso recurso) {rR.save(recurso);}

    @Override
    public void delete(int id){
        rR.deleteById(id);
    }

     @Override
    public List<Recurso> findRecursoByAutor(String autor){return rR.findRecursoByAutor(autor);}

    @Override
    public List<Recurso>findRecursoByFechaPublicacion(LocalDate fechaPublicacion) {return rR.findByFechapublicacion(fechaPublicacion);};
}
