package pe.edu.upc.moneyproject.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.moneyproject.dtos.ImpuestoResumenDTO;
import pe.edu.upc.moneyproject.entities.Impuesto;
import pe.edu.upc.moneyproject.repositories.IImpuestoRepository;
import pe.edu.upc.moneyproject.servicesinterfaces.IImpuestoService;

import java.util.List;

@Service
public class ImpuestoServiceImplement implements IImpuestoService
{

   @Autowired
      private IImpuestoRepository iR;

    @Override
      public void insert(Impuesto impuesto) {iR.save(impuesto);}
   @Override
       public List<Impuesto> findAll() {return  iR.findAll();}

    @Override
    public Impuesto listId(int idImpuesto) {
        return iR.findById(idImpuesto).orElse(null);
    }

    @Override
    public void update(Impuesto impuesto) {
        iR.save(impuesto);
    }

    @Override
    public void delete(Integer id) {
        iR.deleteById(id);
    }
    @Autowired
    private IImpuestoRepository repo;

    @Override
    public List<ImpuestoResumenDTO> obtenerTotalesPorTipo() {
        return iR.obtenerTotalesPorTipo();}

    @Override
    public List<Object[]> obtenerPromediosPorTipo() {
        return iR.obtenerPromediosPorTipo();
    }

}
