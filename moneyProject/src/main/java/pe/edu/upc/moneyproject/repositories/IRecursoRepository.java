package pe.edu.upc.moneyproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.moneyproject.entities.Recurso;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IRecursoRepository extends JpaRepository<Recurso,Integer> {
   public List<Recurso> findRecursoByAutor(String autor);

    @Query("SELECT r from Recurso r where r.fechasubida=:fs group by r.idRecurso")
    public List<Recurso> obtenerRecursoPorDia(@Param("fs") LocalDate fechasubida);

}

