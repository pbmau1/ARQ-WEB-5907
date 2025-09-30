package pe.edu.upc.moneyproject.dtos;

import java.io.Serializable;

public class JwtRequestDTO implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;
    private String nombre;
    private String contrasenia;
    public JwtRequestDTO() {
        super();
    }


    public JwtRequestDTO(String nombre, String contrasenia) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
