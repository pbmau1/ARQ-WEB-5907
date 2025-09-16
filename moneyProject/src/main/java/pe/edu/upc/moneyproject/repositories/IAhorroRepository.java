package pe.edu.upc.moneyproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.moneyproject.entities.Ahorro;

@Repository
public interface IAhorroRepository extends JpaRepository<Ahorro,Integer> {
}