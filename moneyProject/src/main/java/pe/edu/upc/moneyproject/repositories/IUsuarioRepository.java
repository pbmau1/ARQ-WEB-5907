package pe.edu.upc.moneyproject.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.moneyproject.entities.Usuario;

import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {
    public Usuario findOneByNombre(String nombre);

    //INSERTAR ROLES
    @Transactional
    @Modifying
    @Query(value = "insert into roles (rol, user_id) VALUES (:rol, :user_id)", nativeQuery = true)
    public void insRol(@Param("rol") String authority, @Param("user_id") Integer user_id);

    @Query(value = "Select u.id_usuario, u.nombre, u.correo\n" +
            "from usuario u", nativeQuery = true)
    List<String[]> findUsuarios();

    boolean existsByCorreo(String correo);
    Usuario findByCorreo(String correo);
}
