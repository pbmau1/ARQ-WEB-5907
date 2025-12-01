package pe.edu.upc.moneyproject.servicesinterfaces;

import pe.edu.upc.moneyproject.entities.Operacion;

import java.time.LocalDate;
import java.util.List;

public interface IOperacionService {

    // ADMIN y CLIENT pueden registrar
    void insert(Operacion operacion);

    // ADMIN puede listar todas las operaciones
    List<Operacion> findAll();

    // CLIENT solo lista sus operaciones
    List<Operacion> listarPorUsuario(int idUsuario);

    // Obtener por ID (ambos roles)
    Operacion listId(int id);

    // ADMIN puede actualizar cualquier operación
    void update(Operacion operacion);

    // CLIENT solo actualiza si es dueño
    boolean update(Operacion operacion, int idUsuario);

    // ADMIN puede eliminar cualquier operación
    void delete(int id);

    // CLIENT solo elimina si es dueño
    boolean delete(int idOperacion, int idUsuario);

    List<Operacion> findOperacionByCategoria(String categoria);

    List<Operacion> searchOp(LocalDate fecha);

    List<Object[]> sumaOperacionesPorUsuario();
}
