package pe.edu.upc.moneyproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.moneyproject.entities.Role;

public interface IRolRepository extends JpaRepository<Role,Integer> {
}
