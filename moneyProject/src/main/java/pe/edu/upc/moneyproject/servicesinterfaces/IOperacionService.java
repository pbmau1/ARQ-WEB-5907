package pe.edu.upc.moneyproject.servicesinterfaces;

import pe.edu.upc.moneyproject.entities.Operacion;

import java.util.List;

public interface IOperacionService {
    public void insert(Operacion operacion);
    public List<Operacion> findAll();
}
