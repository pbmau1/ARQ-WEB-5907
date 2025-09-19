package pe.edu.upc.moneyproject.servicesinterfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import pe.edu.upc.moneyproject.dtos.BalanceDTO;
import pe.edu.upc.moneyproject.entities.Balance;

import java.util.List;

public interface IBalanceService {
    public void insert(Balance balance);
    public List<Balance> findAll();
    public void delete(int id);
    public Balance listId(int id);
    public void update(Balance balance);
    List<Balance> findBalancesByMes(String mes);
    public List<String[]> sumadetotalingr();


}
