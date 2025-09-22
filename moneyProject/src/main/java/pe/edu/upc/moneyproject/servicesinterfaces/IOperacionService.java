package pe.edu.upc.moneyproject.servicesinterfaces;

import pe.edu.upc.moneyproject.entities.Ahorro;
import pe.edu.upc.moneyproject.entities.Operacion;

import java.time.LocalDate;
import java.util.List;

public interface IOperacionService {
    public void insert(Operacion operacion);
    public List<Operacion> findAll();
    public Operacion listId(int id);
    public void update(Operacion operacion);
    public void delete(int id);
    public List<Operacion> findOperacionByCategoria(String Categoria);
    public List<Operacion>searchOp(LocalDate fecha);
    public List<Object[]> sumaOperacionesPorUsuario();
}
