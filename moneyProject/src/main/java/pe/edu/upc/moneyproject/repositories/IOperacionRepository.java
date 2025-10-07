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
    public List<Operacion> findOperacionByCategoria(String Categoria);

    @Query("Select o from Operacion o where o.fecha = :fecha")
    public List<Operacion> buscar(@Param("fecha") LocalDate fecha);

    @Query(value = "SELECT u.id_usuario, u.nombre, " +
            "SUM(CASE WHEN o.tipo_operacion = 'Ingreso' THEN o.monto_operacion ELSE 0 END) AS total_ingresos, " +
            "SUM(CASE WHEN o.tipo_operacion = 'Gasto' THEN o.monto_operacion ELSE 0 END) AS total_gastos " +
            "FROM operacion o " +
            "INNER JOIN usuario u ON o.id_usuario = u.id_usuario " +
            "GROUP BY u.id_usuario, u.nombre",
            nativeQuery = true)
    List<Object[]> sumaOperacionesPorUsuario();
}
