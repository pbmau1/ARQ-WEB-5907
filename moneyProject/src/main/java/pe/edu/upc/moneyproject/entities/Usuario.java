package pe.edu.upc.moneyproject.entities;

import jakarta.persistence.*;


    @Entity
    @Table(name = "Usuario")
    public class Usuario
    {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idUsuario;

        @Column(name = "nombre",nullable = false,length = 30)
        private String nombre;

        @Column(name = "correo",nullable = false,length = 35)
        private String correo;

        @Column(name = "contraseña",nullable = false)
        private boolean contrasenia;

        public Usuario() {
        }

        public Usuario(int idUsuario, String nombre, String correo, boolean contrasenia) {
            this.idUsuario = idUsuario;
            this.nombre = nombre;
            this.correo = correo;
            this.contrasenia = contrasenia;
        }
    }
