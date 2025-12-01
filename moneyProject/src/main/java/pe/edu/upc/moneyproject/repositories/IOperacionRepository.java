package pe.edu.upc.moneyproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.moneyproject.entities.Operacion;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IOperacionRepository extends JpaRepository<Operacion,Integer> {

    // Buscar por categoría
    List<Operacion> findOperacionByCategoria(String Categoria);

    // Buscar por fecha
    @Query("SELECT o FROM Operacion o WHERE o.fecha = :fecha")
    List<Operacion> buscar(@Param("fecha") LocalDate fecha);

    // Listar operaciones del usuario logueado
    @Query("SELECT o FROM Operacion o WHERE o.usuario.idUsuario = :idUsuario ORDER BY o.fecha DESC")
    List<Operacion> listarPorUsuario(@Param("idUsuario") int idUsuario);

    // Obtener operación solo si pertenece al usuario
    @Query("SELECT o FROM Operacion o WHERE o.idOperacion = :idOperacion AND o.usuario.idUsuario = :idUsuario")
    Operacion findByIdAndUsuario(@Param("idOperacion") int idOperacion,
                                 @Param("idUsuario") int idUsuario);

    // Reporte nativo por usuario
    @Query(value = """
        SELECT u.id_usuario, u.nombre,
               SUM(CASE WHEN o.tipo_operacion = 'Ingreso' THEN o.monto_operacion ELSE 0 END) AS total_ingresos,
               SUM(CASE WHEN o.tipo_operacion = 'Gasto' THEN o.monto_operacion ELSE 0 END) AS total_gastos
        FROM operacion o
        INNER JOIN usuario u ON o.id_usuario = u.id_usuario
        GROUP BY u.id_usuario, u.nombre
        """, nativeQuery = true)
    List<Object[]> sumaOperacionesPorUsuario();
}
