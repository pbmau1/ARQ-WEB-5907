package pe.edu.upc.moneyproject.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.moneyproject.entities.Balance;
import pe.edu.upc.moneyproject.repositories.IBalanceRepository;
import pe.edu.upc.moneyproject.servicesinterfaces.IBalanceService;

import java.util.List;

@Service
public class BalanceServiceImplement implements IBalanceService {
    @Autowired
    private IBalanceRepository BR;

    @Override
    public void insert(Balance balance) {
        int idUsuario = balance.getUsuario().getIdUsuario();
        int mes = convertirMes(balance.getMes());
        int anio = balance.getAnio();

        double ingresos = BR.obtenerTotalIngresos(idUsuario, mes, anio);
        double gastos = BR.obtenerTotalGastos(idUsuario, mes, anio);
        double ahorro = BR.obtenerTotalAhorro(idUsuario, mes, anio);

        balance.setTotal_ingreso(0);
        balance.setTotal_ingreso(ingresos);
        balance.setTotal_gasto(gastos);
        balance.setTotal_ahorro(ahorro);

        balance.setBalance(ingresos - gastos);

        BR.save(balance);

        BR.save(balance);
    }

    @Override
    public List<Balance> findAll() {
        return BR.findAll();
    }

    @Override
    public void delete(int id) {
        BR.deleteById(id);
    }

    @Override
    public Balance listId(int id) {
        return BR.findById(id).orElse(null);
    }

    @Override
    public void update(Balance balance) {
        Balance existente = BR.findById(balance.getIdBalance()).orElse(null);

        if (existente != null) {
            existente.setMes(balance.getMes());
            existente.setAnio(balance.getAnio());
            existente.setUsuario(balance.getUsuario());
            existente.setTotal_ahorro(balance.getTotal_ahorro());

            int mesNumero = convertirMes(existente.getMes());
            int idUsuario = existente.getUsuario().getIdUsuario();
            int anio = existente.getAnio();

            double ingresos = BR.obtenerTotalIngresos(idUsuario, mesNumero, anio);
            double gastos = BR.obtenerTotalGastos(idUsuario, mesNumero, anio);
            double ahorro = BR.obtenerTotalAhorro(idUsuario, mesNumero, anio);

            existente.setTotal_ingreso(ingresos);
            existente.setTotal_gasto(gastos);
            existente.setTotal_ahorro(ahorro);
            existente.setBalance(ingresos - gastos);

            BR.save(existente);
        }


        BR.save(balance); }

    @Override
    public List<Balance> findBalancesByMes(String mes){return BR.findBalancesByMes(mes);}

    @Override
    public List<String[]> sumadetotalingr(){return BR.sumadetotalingr();}

    @Override
    public double obtenerTotalIngresos(int idUsuario, int mes, int anio) {
        return BR.obtenerTotalIngresos(idUsuario, mes, anio);
    }

    @Override
    public double obtenerTotalGastos(int idUsuario, int mes, int anio) {
        return BR.obtenerTotalGastos(idUsuario, mes, anio);
    }

    @Override
    public double obtenerTotalAhorro(int idUsuario, int mes, int anio) {
        return BR.obtenerTotalAhorro(idUsuario, mes, anio);
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
}
