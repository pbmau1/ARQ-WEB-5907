package pe.edu.upc.moneyproject.dtos;

import java.io.Serializable;

public class JwtResponseDTO implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private final String token;
    private final int idUsuario;

    public JwtResponseDTO(String token, int idUsuario) {
        this.token = token;
        this.idUsuario = idUsuario;
    }

    public String getToken() {
        return token;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
}
