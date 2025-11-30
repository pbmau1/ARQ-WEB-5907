package pe.edu.upc.moneyproject.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.moneyproject.entities.Usuario;
import pe.edu.upc.moneyproject.repositories.IUsuarioRepository;
import pe.edu.upc.moneyproject.servicesinterfaces.IUsuarioService;

import java.util.List;

@Service
public class UsuarioServiceImplement implements IUsuarioService {
    @Autowired //sirve para inyectar dependencias
    private IUsuarioRepository UR;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyectamos el encoder

    @Override
    public List<Usuario> list() {
        return UR.findAll();
    }

    @Override
    public void insert(Usuario usuario) {
        if (UR.findByCorreo(usuario.getCorreo()) != null) {
            throw new RuntimeException("correo_duplicado");
        }

        usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
        UR.save(usuario);
    }

    @Override
    public void delete(int id) {
        UR.deleteById(id);
    }

    @Override
    public void update(Usuario usuario) {
        UR.save(usuario);
    }

    @Override
    public Usuario listId(int id) {
        return UR.findById(id).orElse(null);
    }

    @Override
    public List<String[]> findUsuarios() {
        return UR.findUsuarios();
    }

    @Override
    public boolean existsByCorreo(String correo) {
        return UR.existsByCorreo(correo);
    }

    @Override
    public Usuario findByCorreo(String correo) {
        return UR.findByCorreo(correo);
    }

}
