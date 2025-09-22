package pe.edu.upc.moneyproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.moneyproject.entities.Ahorro;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IAhorroRepository extends JpaRepository<Ahorro,Integer> {
    @Query("select a " +
            "from Ahorro a where a.fecha_inicio BETWEEN :start AND :end")
    List<Ahorro> findByPeriodo(@Param("start") LocalDate start, @Param("end") LocalDate end);
}