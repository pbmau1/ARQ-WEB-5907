package pe.edu.upc.moneyproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.moneyproject.dtos.JwtRequestDTO;
import pe.edu.upc.moneyproject.dtos.JwtResponseDTO;
import pe.edu.upc.moneyproject.entities.Usuario;
import pe.edu.upc.moneyproject.repositories.IUsuarioRepository;
import pe.edu.upc.moneyproject.securities.JwtTokenUtil;
import pe.edu.upc.moneyproject.servicesimplements.JwtUserDetailsService;

@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody JwtRequestDTO req) throws Exception {

        authenticate(req.getCorreo(), req.getContrasenia());

        // cargar UserDetails
        UserDetails userDetails = userDetailsService.loadUserByUsername(req.getCorreo());

        // cargar usuario para obtener ID
        Usuario usuario = usuarioRepository.findByCorreo(req.getCorreo());

        // generar token con ID
        String token = jwtTokenUtil.generateToken(userDetails, usuario.getIdUsuario());

        // devolver token + id
        JwtResponseDTO response = new JwtResponseDTO(token, usuario.getIdUsuario());

        return ResponseEntity.ok(response);
    }

    private void authenticate(String correo, String contrasenia) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(correo, contrasenia)
            );
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
