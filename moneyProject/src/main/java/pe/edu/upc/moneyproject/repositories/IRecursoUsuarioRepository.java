package pe.edu.upc.moneyproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.moneyproject.entities.RecursoUsuario;

public interface IRecursoUsuarioRepository extends JpaRepository<RecursoUsuario,Integer> {
}
