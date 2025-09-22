package pe.edu.upc.moneyproject.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.moneyproject.entities.Balance;
import pe.edu.upc.moneyproject.repositories.IBalanceRepository;
import pe.edu.upc.moneyproject.servicesinterfaces.IBalanceService;

import java.util.List;

@Service
public class BalanceServiceImplement implements IBalanceService {
    @Autowired
    private IBalanceRepository BR;

    @Override
    public void insert(Balance balance) {
        BR.save(balance);
    }

    @Override
    public List<Balance> findAll() {
        return BR.findAll();
    }

    @Override
    public void delete(int id) {
        BR.deleteById(id);
    }

    @Override
    public Balance listId(int id) {
        return BR.findById(id).orElse(null);
    }

    @Override
    public void update(Balance balance) { BR.save(balance); }

    @Override
    public List<Balance> findBalancesByMes(String mes){return BR.findBalancesByMes(mes);}

    @Override
    public List<String[]> sumadetotalingr(){return BR.sumadetotalingr();}


}
