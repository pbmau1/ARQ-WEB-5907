package pe.edu.upc.moneyproject.servicesinterfaces;

import org.springframework.data.repository.query.Param;
import pe.edu.upc.moneyproject.entities.Ahorro;

import java.time.LocalDate;
import java.util.List;

public interface IAhorroService {
    public void insert(Ahorro ahorro);
    public List<Ahorro> findAll();

    void update(Ahorro ah);

    Ahorro listId(int id);

    void delete(int id);

    List<Ahorro> findByPeriodo(LocalDate start, LocalDate end);

    List<String[]> AhorroTotal(@Param("idUsuario") Integer idUsuario);
}