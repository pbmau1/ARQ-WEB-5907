package pe.edu.upc.moneyproject.servicesinterfaces;

import org.springframework.data.repository.query.Param;
import pe.edu.upc.moneyproject.entities.Usuario;

import java.util.List;

public interface IUsuarioService {
    public List<Usuario> list();
    public void insert(Usuario usuario);
    public void delete(int id);
    public void update(Usuario usuario);
    public Usuario listId(int id);
    List<String[]> findUsuarios();
}
