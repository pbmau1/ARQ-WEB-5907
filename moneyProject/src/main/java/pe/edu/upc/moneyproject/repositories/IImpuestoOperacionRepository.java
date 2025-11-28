package pe.edu.upc.moneyproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upc.moneyproject.entities.ImpuestoOperacion;

import java.util.List;

public interface IImpuestoOperacionRepository extends JpaRepository<ImpuestoOperacion,Integer> {
    @Query(value = """
        SELECT 
            i.tipo_impuesto AS categoriaDeImpuesto,
            i.tasa AS tasa,
            o.tipo_operacion AS tipoIngreso,
            o.categoria_operacion AS categoriaDeOperacion,
            o.monto_operacion AS montoOperacion,
            (o.monto_operacion * (i.tasa / 100.0)) AS montoFinal
        FROM impuesto_operacion io
        JOIN operacion o 
            ON io.id_operacion = o.id_operacion
        JOIN impuesto i 
            ON io.id_impuesto = i.id_impuesto
        WHERE o.tipo_operacion = 'Ingreso'
        """,
            nativeQuery = true)
    List<Object[]> obtenerImpuestosIngresos();
}
