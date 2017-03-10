package dds.poi.builder;

import dds.poi.model.Consulta;

import java.util.Date;
import java.util.List;

/**
 * Created by Nicolas on 06/11/2016.
 */
public class ConsultaBuilder extends Builder<Consulta>{
    private Consulta consulta = new Consulta();

    public ConsultaBuilder username(String username){
        this.consulta.setUsername(username);
        return this;
    }
    public ConsultaBuilder fecha(Date fecha){
        this.consulta.setFecha(fecha);
        return this;
    }
    public ConsultaBuilder filtros(List<String> filtros){
        this.consulta.setFiltros(filtros);
        return this;
    }
    public ConsultaBuilder cantidadResultados(int resultados){
        this.consulta.setCantidadResultados(resultados);
        return this;
    }


    @Override
    public Consulta returnBuildObject() {
        return this.consulta;
    }

    @Override
    public boolean isValidBuild() {
        if(consulta.getUsername()!=null && consulta.getFecha()!=null)
            return true;

        return false;
    }
}
