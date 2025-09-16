package pe.edu.upc.moneyproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.moneyproject.entities.Operacion;

@Repository
public interface IOperacionRepository extends JpaRepository<Operacion,Integer> {
}
