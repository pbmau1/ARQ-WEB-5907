package pe.edu.upc.moneyproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import pe.edu.upc.moneyproject.entities.Recurso;

@Repository
public interface IRecursoRepository extends JpaRepository<Recurso,Integer> {

}

