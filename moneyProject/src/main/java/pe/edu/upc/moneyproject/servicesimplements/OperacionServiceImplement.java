package pe.edu.upc.moneyproject.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.moneyproject.entities.Operacion;
import pe.edu.upc.moneyproject.repositories.IOperacionRepository;
import pe.edu.upc.moneyproject.servicesinterfaces.IAhorroService;
import pe.edu.upc.moneyproject.servicesinterfaces.IOperacionService;

import java.util.List;

@Service
public class OperacionServiceImplement implements IOperacionService {
    @Autowired
    private IOperacionRepository oR;

    @Override
    public void insert(Operacion operacion) {
        oR.save(operacion);
    }

    @Override
    public List<Operacion> findAll() {
        return oR.findAll();
    }

    @Override
    public Operacion listId(int id) {
        return oR.findById(id).orElse(null);
    }

    @Override
    public void update(Operacion operacion) {
        oR.save(operacion);
    }

    @Override
    public void delete(int id) {
        oR.deleteById(id);
    }
     @Override
    public List<Operacion> findOperacionByCategoria(String Categoria){return oR.findOperacionByCategoria(Categoria);}

}
