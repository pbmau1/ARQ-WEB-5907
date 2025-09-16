package pe.edu.upc.moneyproject.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.moneyproject.entities.Ahorro;
import pe.edu.upc.moneyproject.repositories.IAhorroRepository;
import pe.edu.upc.moneyproject.servicesinterfaces.IAhorroService;

import java.util.List;

@Service
public class AhorrroServiceImplement implements IAhorroService {
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
}
