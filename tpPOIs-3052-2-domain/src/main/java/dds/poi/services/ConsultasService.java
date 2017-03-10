package dds.poi.services;

import dds.poi.dto.model.ConsultaDTO;
import dds.poi.model.Consulta;
import dds.poi.provider.repository.ConsultaRepository;
import dds.poi.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nicolas on 07/11/2016.
 */
@Service
public class ConsultasService {
    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> retrieveConsultas(int cantidadResultados){
        List<Consulta> consultas = consultaRepository.findByCantidadResultadosGreaterThanEqual(cantidadResultados);
        return consultas;
    }

    public ConsultaDTO fechasEnLasQueConsultoElUsuario(String username){
        List<Consulta> consultas = consultaRepository.findByUsernameOrderByFechaAsc(username);
        ConsultaDTO consultaDTO = new ConsultaDTO();

        if(consultas!=null){
            consultaDTO.setUsername(consultas.get(0).getUsername());
            List<String> consultaFechas = consultas.stream()
                                                   .map(consulta -> DateUtils.dateToString(consulta.getFecha()))
                                                   .collect(Collectors.toList());

            consultaDTO.setFechasConsulta(consultaFechas);
        }

        return consultaDTO;
    }

}
