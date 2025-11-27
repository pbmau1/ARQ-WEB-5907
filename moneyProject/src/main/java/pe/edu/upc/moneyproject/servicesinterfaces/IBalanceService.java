package pe.edu.upc.moneyproject.servicesinterfaces;

import pe.edu.upc.moneyproject.entities.Balance;

import java.util.List;

public interface IBalanceService {
    public void insert(Balance balance);
    public List<Balance> findAll();
    public void delete(int id);
    public Balance listId(int id);
    public void update(Balance balance);
    List<Balance> findBalancesByMes(String mes);
    public List<String[]> sumadetotalingr();
    double obtenerTotalIngresos(int idUsuario, int mes, int anio);
    double obtenerTotalGastos(int idUsuario, int mes, int anio);
    double obtenerTotalAhorro(int idUsuario, int mes, int anio);

}
