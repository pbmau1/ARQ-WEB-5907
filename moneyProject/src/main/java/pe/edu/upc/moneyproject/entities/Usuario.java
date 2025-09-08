package pe.edu.upc.moneyproject.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idArea;

    @Column(name = "nombre",nullable = false,length = 30)
    private String nombre;
    @Column(name = "correo",nullable = false,length = 35)
    private String responsibleArea;
    @Column(name = "contrasenia",nullable = false)
    private boolean statusArea;

    public Usuario(int idArea, String nombre, String responsibleArea, boolean statusArea) {
        this.idArea = idArea;
        this.nombre = nombre;
        this.responsibleArea = responsibleArea;
        this.statusArea = statusArea;
    }

    public Usuario() {

    }
}