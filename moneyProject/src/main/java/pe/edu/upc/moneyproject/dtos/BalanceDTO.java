package pe.edu.upc.moneyproject.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import pe.edu.upc.moneyproject.entities.Usuario;

public class BalanceDTO {
    private int idBalance;
    private String mes;
    private int anio;
    private double total_gasto;
    private double total_ingreso;
    private double total_ahorro;
    private double balance;
    @JsonIgnoreProperties({"roles"})
    private Usuario usuario;

    public int getIdBalance() {
        return idBalance;
    }

    public void setIdBalance(int idBalance) {
        this.idBalance = idBalance;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getTotal_gasto() {
        return total_gasto;
    }

    public void setTotal_gasto(double total_gasto) {
        this.total_gasto = total_gasto;
    }

    public double getTotal_ingreso() {
        return total_ingreso;
    }

    public void setTotal_ingreso(double total_ingreso) {
        this.total_ingreso = total_ingreso;
    }

    public double getTotal_ahorro() {
        return total_ahorro;
    }

    public void setTotal_ahorro(double total_ahorro) {
        this.total_ahorro = total_ahorro;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
