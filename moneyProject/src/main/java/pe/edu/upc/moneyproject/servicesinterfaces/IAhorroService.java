package pe.edu.upc.moneyproject.servicesinterfaces;

import pe.edu.upc.moneyproject.entities.Ahorro;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IAhorroService {

    void insert(Ahorro ahorro);

    // ADMIN
    List<Ahorro> findAll();

    // CLIENT
    List<Ahorro> listarPorUsuario(int idUsuario);

    // Ambos roles
    Ahorro listId(int id);

    // ADMIN
    void update(Ahorro ahorro);

    // CLIENT
    boolean update(Ahorro ahorro, int idUsuario);

    // ADMIN
    void delete(int id);

    // CLIENT
    boolean delete(int idAhorro, int idUsuario);

    // Filtros y reportes existentes
    List<Ahorro> findByPeriodo(LocalDate start, LocalDate end);
    List<String[]> AhorroTotal(Integer idUsuario);
}
