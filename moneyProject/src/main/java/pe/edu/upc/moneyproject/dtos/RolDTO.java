package pe.edu.upc.moneyproject.dtos;

import pe.edu.upc.moneyproject.entities.Usuario;

public class RolDTO {
    private Long id;
    private String rol;
    private int idUsuario;;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
