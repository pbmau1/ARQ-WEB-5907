package pe.edu.upc.moneyproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.moneyproject.entities.Operacion;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IOperacionRepository extends JpaRepository<Operacion,Integer> {
    public List<Operacion> findOperacionByCategoria(String Categoria);

    @Query("Select o from Operacion o where o.fecha = :fecha")
    public List<Operacion> buscar(@Param("fecha") LocalDate fecha);
}
