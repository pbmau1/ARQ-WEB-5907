package pe.edu.upc.moneyproject.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.moneyproject.entities.Usuario;
import pe.edu.upc.moneyproject.repositories.IUsuarioRepository;
import pe.edu.upc.moneyproject.servicesinterfaces.IUsuarioService;

import java.util.List;

@Service
public class UsuarioServiceImplement implements IUsuarioService {
    @Autowired //sirve para inyectar dependencias
    private IUsuarioRepository UR;

    @Override
    public List<Usuario> list() {
        return UR.findAll();
    }

    @Override
    public void insert(Usuario usuario) {
        UR.save(usuario);
    }

    @Override
    public void delete(int id) {
        UR.deleteById(id);
    }

    @Override
    public Usuario listId(int id) {
        return UR.findById(id).orElse(null);
    }

    @Override
    public List<String[]> findUsuarios() {
        return UR.findUsuarios();
    }

}
