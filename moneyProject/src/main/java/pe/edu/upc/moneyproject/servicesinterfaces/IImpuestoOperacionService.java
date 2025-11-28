package pe.edu.upc.moneyproject.servicesinterfaces;

import pe.edu.upc.moneyproject.entities.ImpuestoOperacion;

import java.util.List;

public interface IImpuestoOperacionService {
    public List<ImpuestoOperacion> findAll();
    public ImpuestoOperacion insert(ImpuestoOperacion impuestoOperacion);
    public void update(ImpuestoOperacion impuestoOperacion);
    public void delete(int id);
    public ImpuestoOperacion listId(int id);
    public List<Object[]> obtenerImpuestosIngresos();
}
