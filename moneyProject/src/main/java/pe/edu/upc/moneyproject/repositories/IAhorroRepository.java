package pe.edu.upc.moneyproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.moneyproject.entities.Ahorro;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IAhorroRepository extends JpaRepository<Ahorro, Integer> {

    // Buscar por periodo (TU QUERY original — NO TOCADO)
    @Query("select a from Ahorro a where a.fecha_inicio BETWEEN :start AND :end")
    List<Ahorro> findByPeriodo(@Param("start") LocalDate start,
                               @Param("end") LocalDate end);

    // Reporte original (NO TOCADO)
    @Query("select a.usuario.idUsuario, a.usuario.nombre, sum(a.monto_actual) " +
            "from Ahorro a " +
            "where a.usuario.idUsuario = :idUsuario " +
            "group by a.usuario.idUsuario, a.usuario.nombre")
    List<String[]> AhorroTotal(@Param("idUsuario") Integer idUsuario);

    // ============================================================
    // MÉTODOS NUEVOS (NECESARIOS PARA CLIENT / ADMIN)
    // ============================================================

    // CLIENT — listar solo sus ahorros
    @Query("SELECT a FROM Ahorro a WHERE a.usuario.idUsuario = :idUsuario ORDER BY a.fecha_inicio DESC")
    List<Ahorro> listarPorUsuario(@Param("idUsuario") int idUsuario);

    // CLIENT — verificar que el ahorro pertenece al usuario (update/delete seguro)
    @Query("SELECT a FROM Ahorro a WHERE a.idAhorro = :idAhorro AND a.usuario.idUsuario = :idUsuario")
    Ahorro findByIdAndUsuario(@Param("idAhorro") int idAhorro,
                              @Param("idUsuario") int idUsuario);
}
