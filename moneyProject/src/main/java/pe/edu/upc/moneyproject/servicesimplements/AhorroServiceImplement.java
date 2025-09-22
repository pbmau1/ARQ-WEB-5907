package pe.edu.upc.moneyproject.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.moneyproject.entities.Ahorro;
import pe.edu.upc.moneyproject.repositories.IAhorroRepository;
import pe.edu.upc.moneyproject.servicesinterfaces.IAhorroService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AhorroServiceImplement implements IAhorroService {
    @Autowired
    private IAhorroRepository aR;

    @Override
    public void insert(Ahorro ahorro) {
        aR.save(ahorro);
    }

    @Override
    public List<Ahorro> findAll() {
        return aR.findAll();
    }

    @Override
    public Ahorro listId(int id) {
        return aR.findById(id).orElse(null);
    }

    @Override
    public void update(Ahorro ahorro) {
        aR.save(ahorro);
    }

    @Override
    public void delete(int id) {
        aR.deleteById(id);
    }

    @Override
    public List<Ahorro> findByPeriodo(LocalDate start, LocalDate end) {
        return aR.findByPeriodo(start,end);
    }
}
