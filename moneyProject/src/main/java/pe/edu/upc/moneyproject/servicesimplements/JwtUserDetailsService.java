package pe.edu.upc.moneyproject.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upc.moneyproject.entities.Usuario;
import pe.edu.upc.moneyproject.repositories.IUsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private IUsuarioRepository rep;


    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        Usuario usuario =rep.findOneByNombre(nombre);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        List<GrantedAuthority> roles = new ArrayList<>();

        usuario.getRoles().forEach(rol -> {
            roles.add(new SimpleGrantedAuthority("Role_"+ rol.getRol()));
        });

        UserDetails ud = new org.springframework.security.core.userdetails.User(usuario.getNombre(), usuario.getContrasenia(),true, true, true, true, roles);

        return ud;

    }
}
