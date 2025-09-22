package pe.edu.upc.moneyproject.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.moneyproject.entities.ImpuestoOperacion;
import pe.edu.upc.moneyproject.repositories.IImpuestoOperacionRepository;
import pe.edu.upc.moneyproject.servicesinterfaces.IImpuestoOperacionService;

import java.util.List;

@Service
public class ImpuestoOperacionServiceImplement implements IImpuestoOperacionService {
    @Autowired
    private IImpuestoOperacionRepository ioR;

    @Override
    public List<ImpuestoOperacion> findAll() {
        return ioR.findAll();
    }

    @Override
    public ImpuestoOperacion insert(ImpuestoOperacion impuestoOperacion) {
        return ioR.save(impuestoOperacion);
    }

    @Override
    public void update(ImpuestoOperacion impuestoOperacion) {
        ioR.save(impuestoOperacion);
    }

    @Override
    public void delete(int id) {
        ioR.deleteById(id);
    }

    @Override
    public ImpuestoOperacion listId(int id) {
        return ioR.findById(id).orElse(null);
    }

}
