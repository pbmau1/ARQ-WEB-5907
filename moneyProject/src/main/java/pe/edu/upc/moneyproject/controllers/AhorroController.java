package pe.edu.upc.moneyproject.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.moneyproject.dtos.AhorroDTO;
import pe.edu.upc.moneyproject.dtos.AhorroTotalDTO;
import pe.edu.upc.moneyproject.entities.Ahorro;
import pe.edu.upc.moneyproject.entities.Usuario;
import pe.edu.upc.moneyproject.servicesinterfaces.IAhorroService;
import pe.edu.upc.moneyproject.servicesinterfaces.IUsuarioService;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ahorro")
public class AhorroController {

    @Autowired
    private IAhorroService aS;

    @Autowired
    private IUsuarioService uS;

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


    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("/me")
    public ResponseEntity<?> listarMisAhorros(Principal principal) {

        int idUsuario = extraerIdUsuario(principal);

        List<Ahorro> lista = aS.listarPorUsuario(idUsuario);

        List<AhorroDTO> dto = lista.stream()
                .map(x -> new ModelMapper().map(x, AhorroDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dto);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/all")
    public ResponseEntity<?> listarTodos() {

        List<Ahorro> lista = aS.findAll();

        List<AhorroDTO> dto = lista.stream()
                .map(x -> new ModelMapper().map(x, AhorroDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id, Principal principal) {

        Ahorro ahorro = aS.listId(id);
        if (ahorro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe ahorro con ID: " + id);
        }

        int idUsuario = extraerIdUsuario(principal);
        boolean isAdmin = tieneRol("ADMIN");

        if (!isAdmin && ahorro.getUsuario().getIdUsuario() != idUsuario) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("No puedes ver un ahorro que no es tuyo");
        }

        AhorroDTO dto = new ModelMapper().map(ahorro, AhorroDTO.class);
        return ResponseEntity.ok(dto);
    }


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @PostMapping("/register")
    public ResponseEntity<?> insert(@RequestBody AhorroDTO dto, Principal principal) {

        ModelMapper m = new ModelMapper();
        Ahorro ahorro = m.map(dto, Ahorro.class);

        int idUsuarioReal = extraerIdUsuario(principal);
        Usuario u = new Usuario();
        u.setIdUsuario(idUsuarioReal);
        ahorro.setUsuario(u);

        aS.insert(ahorro);

        return ResponseEntity.ok("Ahorro registrado correctamente");
    }

    @PreAuthorize("hasAuthority('CLIENT')")
    @PutMapping("/me/{id}")
    public ResponseEntity<?> updateMiAhorro(
            @PathVariable("id") Integer id,
            @RequestBody AhorroDTO dto,
            Principal principal) {

        int idUsuario = extraerIdUsuario(principal);

        Ahorro original = aS.listId(id);

        if (original == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ahorro no encontrado");
        }

        if (original.getUsuario().getIdUsuario() != idUsuario) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("No puedes modificar un ahorro que no es tuyo");
        }

        // SOLO actualizamos los campos, SIN tocar el usuario
        original.setObjetivo(dto.getObjetivo());
        original.setMonto_actual(dto.getMonto_actual());
        original.setFecha_inicio(dto.getFecha_inicio());
        original.setFecha_limite(dto.getFecha_limite());

        aS.update(original);

        return ResponseEntity.ok("Ahorro actualizado correctamente");
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/admin/{id}")
    public ResponseEntity<?> updateAdmin(
            @PathVariable("id") Integer id,
            @RequestBody AhorroDTO dto) {

        Ahorro existe = aS.listId(id);
        if (existe == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ahorro no encontrado");

        ModelMapper m = new ModelMapper();
        Ahorro ahorro = m.map(dto, Ahorro.class);
        ahorro.setIdAhorro(id);

        aS.update(ahorro);
        return ResponseEntity.ok("Ahorro modificado por ADMIN");
    }


    @PreAuthorize("hasAuthority('CLIENT')")
    @DeleteMapping("/me/{id}")
    public ResponseEntity<?> deleteMiAhorro(
            @PathVariable("id") Integer id,
            Principal principal) {

        int idUsuario = extraerIdUsuario(principal);

        boolean eliminado = aS.delete(id, idUsuario);

        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("No puedes eliminar un ahorro que no es tuyo");
        }

        return ResponseEntity.ok("Ahorro eliminado correctamente");
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable("id") Integer id) {

        Ahorro existe = aS.listId(id);

        if (existe == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ahorro no encontrado");

        aS.delete(id);
        return ResponseEntity.ok("Ahorro eliminado por ADMIN");
    }


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @GetMapping("/periodo")
    public ResponseEntity<?> findByPeriodo(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

        List<Ahorro> ahorros = aS.findByPeriodo(start, end);

        if (ahorros.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron ahorros en el rango indicado");
        }

        return ResponseEntity.ok(ahorros);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @GetMapping("/ahorrototal/{id}")
    public ResponseEntity<?> AhorroTotal(@PathVariable("id") Integer id) {

        List<String[]> total = aS.AhorroTotal(id);
        List<AhorroTotalDTO> listDTO = new ArrayList<>();

        for (String[] x : total) {
            AhorroTotalDTO dto = new AhorroTotalDTO();
            dto.setIdUsuario(Integer.parseInt(x[0]));
            dto.setNombre(x[1]);
            dto.setMonto_total(Double.parseDouble(x[2]));
            listDTO.add(dto);
        }

        return ResponseEntity.ok(listDTO);
    }
}
