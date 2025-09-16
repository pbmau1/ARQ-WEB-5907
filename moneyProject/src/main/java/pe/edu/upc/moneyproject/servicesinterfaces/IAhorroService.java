package pe.edu.upc.moneyproject.servicesinterfaces;

import pe.edu.upc.moneyproject.entities.Ahorro;

import java.util.List;

public interface IAhorroService {
    public void insert(Ahorro ahorro);
    public List<Ahorro> findAll();

    public Ahorro listId(int idAhorro);

    void update(Ahorro ahorro);

    void delete(int id);
}