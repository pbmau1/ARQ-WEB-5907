package pe.edu.upc.moneyproject.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.moneyproject.entities.Balance;
import pe.edu.upc.moneyproject.entities.Operacion;
import pe.edu.upc.moneyproject.repositories.IBalanceRepository;
import pe.edu.upc.moneyproject.repositories.IOperacionRepository;
import pe.edu.upc.moneyproject.servicesinterfaces.IOperacionService;

import java.time.LocalDate;
import java.util.List;

@Service
public class OperacionServiceImplement implements IOperacionService {

    @Autowired
    private IOperacionRepository oR;

    @Autowired
    private IBalanceRepository br;

    // ============================================================
    // INSERTAR (admin + cliente)
    // ============================================================
    @Override
    public void insert(Operacion operacion) {

        oR.save(operacion);

        int mes = operacion.getFecha().getMonthValue();
        int anio = operacion.getFecha().getYear();
        actualizarBalance(operacion.getUsuario().getIdUsuario(), convertirNum(mes), anio);
    }

    // ============================================================
    // SOLO ADMIN
    // ============================================================
    @Override
    public List<Operacion> findAll() {
        return oR.findAll();
    }

    // ============================================================
    // LISTAR POR ID (admin + cliente)
    // ============================================================
    @Override
    public Operacion listId(int id) {
        return oR.findById(id).orElse(null);
    }

    // ============================================================
    // CLIENTE: listar solo sus operaciones
    // ============================================================
    @Override
    public List<Operacion> listarPorUsuario(int idUsuario) {
        return oR.listarPorUsuario(idUsuario);
    }

    // ============================================================
    // CLIENTE: UPDATE seguro
    // ============================================================
    @Override
    public boolean update(Operacion operacion, int idUsuario) {

        Operacion original = oR.findByIdAndUsuario(
                operacion.getIdOperacion(),
                idUsuario
        );

        if (original == null) return false;

        if (operacion.getFecha() == null) {
            operacion.setFecha(original.getFecha());
        }

        oR.save(operacion);
        return true;
    }

    // ============================================================
    // CLIENTE: DELETE seguro
    // ============================================================
    @Override
    public boolean delete(int idOperacion, int idUsuario) {

        Operacion operacion = oR.findByIdAndUsuario(idOperacion, idUsuario);

        if (operacion == null) return false;

        int mes = operacion.getFecha().getMonthValue();
        int anio = operacion.getFecha().getYear();

        oR.deleteById(idOperacion);

        actualizarBalance(idUsuario, convertirNum(mes), anio);

        return true;
    }

    // ============================================================
    // SOLO ADMIN: UPDATE libre
    // ============================================================
    @Override
    public void update(Operacion operacion) {
        oR.save(operacion);
    }

    // ============================================================
    // ✔ SOLO ADMIN: DELETE libre
    // ============================================================
    @Override
    public void delete(int id) {

        Operacion operacion = oR.findById(id).orElse(null);

        if (operacion != null) {

            int mes = operacion.getFecha().getMonthValue();
            int anio = operacion.getFecha().getYear();
            int idUsuario = operacion.getUsuario().getIdUsuario();

            oR.deleteById(id);

            actualizarBalance(idUsuario, convertirNum(mes), anio);
        }
    }

    // ============================================================
    // OTROS MÉTODOS
    // ============================================================
    @Override
    public List<Operacion> findOperacionByCategoria(String Categoria) {
        return oR.findOperacionByCategoria(Categoria);
    }

    @Override
    public List<Operacion> searchOp(LocalDate fecha) {
        return oR.buscar(fecha);
    }

    @Override
    public List<Object[]> sumaOperacionesPorUsuario() {
        return oR.sumaOperacionesPorUsuario();
    }

    // ============================================================
    // BALANCE (SE MANTIENE IGUAL)
    // ============================================================
    public void actualizarBalance(int idUsuario, String mes, int anio) {

        Balance balance = br.findByUsuarioMesAnio(idUsuario, mes, anio);

        double ingresos = br.obtenerTotalIngresos(idUsuario, convertirMes(mes), anio);
        double gastos = br.obtenerTotalGastos(idUsuario, convertirMes(mes), anio);
        double ahorro = br.obtenerTotalAhorro(idUsuario, convertirMes(mes), anio);

        balance.setTotal_ingreso(ingresos);
        balance.setTotal_gasto(gastos);
        balance.setTotal_ahorro(ahorro);
        balance.setBalance(ingresos - gastos);

        br.save(balance);
    }

    public int convertirMes(String mes) {
        switch (mes.toLowerCase()) {
            case "enero": return 1;
            case "febrero": return 2;
            case "marzo": return 3;
            case "abril": return 4;
            case "mayo": return 5;
            case "junio": return 6;
            case "julio": return 7;
            case "agosto": return 8;
            case "septiembre": return 9;
            case "octubre": return 10;
            case "noviembre": return 11;
            case "diciembre": return 12;
            default: return 0;
        }
    }

    public String convertirNum(int numero) {
        switch (numero) {
            case 1: return "Enero";
            case 2: return "Febrero";
            case 3: return "Marzo";
            case 4: return "Abril";
            case 5: return "Mayo";
            case 6: return "Junio";
            case 7: return "Julio";
            case 8: return "Agosto";
            case 9: return "Septiembre";
            case 10: return "Octubre";
            case 11: return "Noviembre";
            case 12: return "Diciembre";
            default: return null;
        }
    }
}
