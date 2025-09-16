package pe.edu.upc.moneyproject.servicesinterfaces;

import pe.edu.upc.moneyproject.entities.Ahorro;

import java.util.List;

public interface IAhorroService {
    public void insert(Ahorro ahorro);
    public List<Ahorro> findAll();
}