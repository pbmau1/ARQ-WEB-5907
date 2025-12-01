package pe.edu.upc.moneyproject.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.moneyproject.dtos.OperacionDTO;
import pe.edu.upc.moneyproject.entities.Operacion;
import pe.edu.upc.moneyproject.entities.Usuario;
import pe.edu.upc.moneyproject.servicesinterfaces.IOperacionService;
import pe.edu.upc.moneyproject.servicesinterfaces.IUsuarioService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/operacion")
public class OperacionController {

    @Autowired
    private IOperacionService oS;

    @Autowired
    private IUsuarioService uS;


    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("/me")
    public ResponseEntity<?> listarMisOperaciones(Principal principal) {

        int idUsuario = extraerIdUsuario(principal);

        List<Operacion> lista = oS.listarPorUsuario(idUsuario);

        List<OperacionDTO> dto = lista.stream()
                .map(x -> new ModelMapper().map(x, OperacionDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dto);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/all")
    public ResponseEntity<?> listarTodas() {

        List<Operacion> lista = oS.findAll();

        List<OperacionDTO> dto = lista.stream()
                .map(x -> new ModelMapper().map(x, OperacionDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dto);
    }


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id, Principal principal) {

        Operacion op = oS.listId(id);
        if (op == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe operación con ID: " + id);
        }

        int idUsuario = extraerIdUsuario(principal);
        boolean isAdmin = tieneRol("ADMIN");

        if (!isAdmin && op.getUsuario().getIdUsuario() != idUsuario) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("No puedes ver una operación que no es tuya");
        }

        OperacionDTO dto = new ModelMapper().map(op, OperacionDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @PostMapping("/register")
    public ResponseEntity<?> insert(@RequestBody OperacionDTO operacionDTO, Principal principal) {

        ModelMapper m = new ModelMapper();
        Operacion operacion = m.map(operacionDTO, Operacion.class);

        int idUsuarioReal = extraerIdUsuario(principal);

        Usuario u = new Usuario();
        u.setIdUsuario(idUsuarioReal);
        operacion.setUsuario(u);

        oS.insert(operacion);

        return ResponseEntity.ok("Operación registrada correctamente");
    }


    @PreAuthorize("hasAuthority('CLIENT')")
    @PutMapping("/me/{id}")
    public ResponseEntity<?> updateMiOperacion(@PathVariable("id") Integer id,
                                               @RequestBody OperacionDTO dto,
                                               Principal principal) {

        int idUsuario = extraerIdUsuario(principal);

        Operacion op = oS.listId(id);
        if (op == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Operación no encontrada");
        }

        if (op.getUsuario().getIdUsuario() != idUsuario) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("No puedes modificar una operación que no es tuya");
        }

        actualizarCampos(op, dto);
        oS.update(op);

        return ResponseEntity.ok("Operación actualizada correctamente");
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/admin/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable("id") Integer id,
                                         @RequestBody OperacionDTO dto) {

        Operacion op = oS.listId(id);
        if (op == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Operación no encontrada");
        }

        actualizarCampos(op, dto);
        oS.update(op);

        return ResponseEntity.ok("Operación modificada por ADMIN");
    }


    @PreAuthorize("hasAuthority('CLIENT')")
    @DeleteMapping("/me/{id}")
    public ResponseEntity<?> deleteMiOperacion(@PathVariable("id") Integer id,
                                               Principal principal) {

        int idUsuario = extraerIdUsuario(principal);

        Operacion op = oS.listId(id);
        if (op == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Operación no encontrada");
        }

        if (op.getUsuario().getIdUsuario() != idUsuario) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("No puedes eliminar una operación que no es tuya");
        }

        oS.delete(id);
        return ResponseEntity.ok("Operación eliminada correctamente");
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable("id") Integer id) {

        Operacion op = oS.listId(id);
        if (op == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Operación no encontrada");
        }

        oS.delete(id);
        return ResponseEntity.ok("Operación eliminada por ADMIN");
    }


    private void actualizarCampos(Operacion op, OperacionDTO dto) {
        op.setCategoria(dto.getCategoria());
        op.setTipo(dto.getTipo());
        op.setMonto(dto.getMonto());
        op.setDetalle(dto.getDetalle());
        op.setFecha(dto.getFecha());
    }

    private int extraerIdUsuario(Principal principal) {
        String correo = principal.getName();
        return uS.findByCorreo(correo).getIdUsuario();
    }

    private boolean tieneRol(String rol) {
        return org.springframework.security.core.context.SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals(rol));
    }
}
