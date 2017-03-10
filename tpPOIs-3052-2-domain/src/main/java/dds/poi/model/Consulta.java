package dds.poi.model;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dds.poi.utils.JsonDateSerializer;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Nicolas on 06/11/2016.
 */
@Document(collection = "Consulta")
public class Consulta {
    @Id
    private String idConsulta;
    private String username;
    @JsonSerialize(using = JsonDateSerializer.class) //Serializa respuesta al formato dd/mm/aaaa
    private Date fecha;
    private List<String> filtros;
    private int cantidadResultados;


    public String getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(String idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getFecha() { return fecha; }

    public void setFecha(Date fecha) { this.fecha = fecha; }

    public List<String> getFiltros() {return filtros;}

    public void setFiltros(List<String> filtros) {this.filtros = filtros; }

    public int getCantidadResultados() {return cantidadResultados;}

    public void setCantidadResultados(int cantidadResultados) {this.cantidadResultados = cantidadResultados;}
}
