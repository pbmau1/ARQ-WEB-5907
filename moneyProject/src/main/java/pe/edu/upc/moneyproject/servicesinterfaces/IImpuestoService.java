package pe.edu.upc.moneyproject.servicesinterfaces;

import pe.edu.upc.moneyproject.entities.Impuesto;
import java.util.List;

public interface IImpuestoService {
    public Void Insert(Impuesto impuesto);

    void insert(Impuesto impuesto);

    public List<Impuesto> findAll();
}
