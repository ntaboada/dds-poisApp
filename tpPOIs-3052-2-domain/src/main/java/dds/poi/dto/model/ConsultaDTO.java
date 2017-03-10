package dds.poi.dto.model;

import java.util.List;

/**
 * Created by Nicolas on 07/11/2016.
 */
public class ConsultaDTO {
    private String username;
    private List<String> fechasConsulta;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getFechasConsulta() {
        return fechasConsulta;
    }

    public void setFechasConsulta(List<String> fechasConsulta) {
        this.fechasConsulta = fechasConsulta;
    }
}
