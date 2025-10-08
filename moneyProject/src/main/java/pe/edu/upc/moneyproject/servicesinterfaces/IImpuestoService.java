package pe.edu.upc.moneyproject.servicesinterfaces;

import pe.edu.upc.moneyproject.dtos.ImpuestoResumenDTO;
import pe.edu.upc.moneyproject.entities.Impuesto;
import java.util.List;

public interface IImpuestoService {

    void insert(Impuesto impuesto);

    public List<Impuesto> findAll();

    Impuesto listId(int idImpuesto);

    void update(Impuesto impuesto);

    void delete(Integer id);

    List<ImpuestoResumenDTO> obtenerTotalesPorTipo();

    List<Object[]> obtenerPromediosPorTipo();}
