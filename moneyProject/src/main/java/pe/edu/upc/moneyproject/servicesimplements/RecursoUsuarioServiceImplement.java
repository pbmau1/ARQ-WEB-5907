package pe.edu.upc.moneyproject.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.moneyproject.entities.RecursoUsuario;
import pe.edu.upc.moneyproject.repositories.IRecursoUsuarioRepository;
import pe.edu.upc.moneyproject.servicesinterfaces.IRecursoUsuarioService;

import java.util.List;

@Service
public class RecursoUsuarioServiceImplement implements IRecursoUsuarioService {
    @Autowired
    private IRecursoUsuarioRepository irR;

    @Override
    public List<RecursoUsuario> findAll() {
        return irR.findAll();
    }

    @Override
    public RecursoUsuario insert(RecursoUsuario recursoUsuario) {
        return irR.save(recursoUsuario);
    }

    @Override
    public void update(RecursoUsuario recursoUsuario) {
        irR.save(recursoUsuario);
    }

    @Override
    public void delete(int id) {
        irR.deleteById(id);
    }

    @Override
    public RecursoUsuario listId(int id) {
        return irR.findById(id).orElse(null);
    }
}
