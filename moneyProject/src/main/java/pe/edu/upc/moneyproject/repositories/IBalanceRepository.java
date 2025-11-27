package pe.edu.upc.moneyproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.moneyproject.entities.Balance;
import pe.edu.upc.moneyproject.entities.Usuario;

import java.util.List;

@Repository
public interface IBalanceRepository extends JpaRepository<Balance,Integer> {
    public List<Balance> findBalancesByMes(String mes);

    @Query(value = "select u.nombre, sum(b.total_ingreso)\n" +
            " from usuario u inner join balance b\n" +
            " on u.id_usuario = b.id_usuario\n" +
            " group by u.nombre", nativeQuery = true)
    public List<String[]> sumadetotalingr();

    @Query("SELECT COALESCE(SUM(o.monto) ,0)"
            + "FROM Operacion o "
            + "WHERE o.usuario.idUsuario = :idUsuario "
            + "AND o.tipo = 'Ingreso' "
            + "AND MONTH(o.fecha) = :mes "
            + "AND YEAR(o.fecha) = :anio")
    double obtenerTotalIngresos(int idUsuario, int mes, int anio);

    @Query("SELECT COALESCE(SUM(o.monto),0) "
            + "FROM Operacion o "
            + "WHERE o.usuario.idUsuario = :idUsuario "
            + "AND o.tipo = 'Gasto' "
            + "AND MONTH(o.fecha) = :mes "
            + "AND YEAR(o.fecha) = :anio")
    double obtenerTotalGastos(int idUsuario, int mes, int anio);

    @Query("select COALESCE(SUM(o.monto),0)"
            + "FROM Operacion o " + "WHERE o.usuario.idUsuario = :idUsuario "
            + "AND o.tipo = 'Ahorro' "
            + "AND MONTH(o.fecha) = :mes "
            + "AND YEAR(o.fecha) = :anio")
    double obtenerTotalAhorro(int idUsuario, int mes, int anio);
}
