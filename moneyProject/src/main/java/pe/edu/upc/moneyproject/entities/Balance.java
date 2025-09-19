package pe.edu.upc.moneyproject.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="Balance")
public class Balance {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idBalance;

    @Column(name="mes",length = 100,nullable = false)
    private String mes;

    @Column(name="anio",nullable = false)
    private int anio;

    @Column(name="total_gasto",nullable = false)
    private double total_gasto;

    @Column(name="total_ingreso",nullable = false)
    private double total_ingreso;

    @Column(name="total_ahorro",nullable = false)
    private double total_ahorro;

    @Column(name="balance",nullable = false)
    private double balance;

    @ManyToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuario;

    public Balance() {
    }

    public Balance(int idBalance, String mes, int anio, double total_gasto, double total_ingreso, double total_ahorro, double balance, Usuario usuario) {
        this.idBalance = idBalance;
        this.mes = mes;
        this.anio = anio;
        this.total_gasto = total_gasto;
        this.total_ingreso = total_ingreso;
        this.total_ahorro = total_ahorro;
        this.balance = balance;
        this.usuario = usuario;
    }

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
