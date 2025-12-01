package pe.edu.upc.moneyproject.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.moneyproject.entities.Ahorro;
import pe.edu.upc.moneyproject.repositories.IAhorroRepository;
import pe.edu.upc.moneyproject.servicesinterfaces.IAhorroService;

import java.time.LocalDate;
import java.util.List;

@Service
public class AhorroServiceImplement implements IAhorroService {

    @Autowired
    private IAhorroRepository aR;

    // ============================================================
    // INSERTAR (admin + cliente)
    // ============================================================
    @Override
    public void insert(Ahorro ahorro) {
        aR.save(ahorro);
    }

    // ============================================================
    // LISTAR PARA ADMIN
    // ============================================================
    @Override
    public List<Ahorro> findAll() {
        return aR.findAll();
    }

    // ============================================================
    // LISTAR PARA CLIENTE
    // ============================================================
    @Override
    public List<Ahorro> listarPorUsuario(int idUsuario) {
        return aR.listarPorUsuario(idUsuario);
    }

    // ============================================================
    // BUSCAR POR ID (ambos roles)
    // ============================================================
    @Override
    public Ahorro listId(int id) {
        return aR.findById(id).orElse(null);
    }

    // ============================================================
    // UPDATE ADMIN (libre)
    // ============================================================
    @Override
    public void update(Ahorro ahorro) {
        aR.save(ahorro);
    }

    // ============================================================
    // UPDATE CLIENTE (validar dueño)
    // ============================================================
    @Override
    public boolean update(Ahorro ahorro, int idUsuario) {

        Ahorro original = aR.findByIdAndUsuario(
                ahorro.getIdAhorro(),
                idUsuario
        );

        if (original == null) return false;

        aR.save(ahorro);
        return true;
    }

    // ============================================================
    // DELETE ADMIN (libre)
    // ============================================================
    @Override
    public void delete(int id) {
        aR.deleteById(id);
    }

    // ============================================================
    // DELETE CLIENTE (validar dueño)
    // ============================================================
    @Override
    public boolean delete(int idAhorro, int idUsuario) {

        Ahorro original = aR.findByIdAndUsuario(idAhorro, idUsuario);

        if (original == null) return false;

        aR.deleteById(idAhorro);
        return true;
    }

    // ============================================================
    // REPORTES (no se tocan)
    // ============================================================
    @Override
    public List<Ahorro> findByPeriodo(LocalDate start, LocalDate end) {
        return aR.findByPeriodo(start, end);
    }

    @Override
    public List<String[]> AhorroTotal(Integer idUsuario) {
        return aR.AhorroTotal(idUsuario);
    }
}