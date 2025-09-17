package pe.edu.upc.moneyproject.servicesinterfaces;

import pe.edu.upc.moneyproject.entities.Ahorro;
import pe.edu.upc.moneyproject.entities.Usuario;

import java.util.List;

public interface IAhorroService {
    public void insert(Ahorro ahorro);
    public List<Ahorro> findAll();
    public Ahorro listId(int id);
    public void update(Ahorro ahorro);
    public void delete(int id);
}
