package pe.edu.upc.moneyproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.moneyproject.dtos.ImpuestoResumenDTO;
import pe.edu.upc.moneyproject.entities.Impuesto;

import java.util.List;


@Repository
public interface IImpuestoRepository extends JpaRepository<Impuesto, Integer> {

    @Query("SELECT new pe.edu.upc.moneyproject.dtos.ImpuestoResumenDTO(i.tipo, SUM(i.tasa)) " +
            "FROM Impuesto i " +
            "GROUP BY i.tipo " +
            "ORDER BY SUM(i.tasa) DESC")
    List<ImpuestoResumenDTO> obtenerTotalesPorTipo();

    @Query("SELECT i.tipo, AVG(i.tasa) " +
            "FROM Impuesto i " +
            "GROUP BY i.tipo " +
            "ORDER BY AVG(i.tasa) DESC")
    List<Object[]> obtenerPromediosPorTipo();

}
