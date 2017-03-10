package dds.poi.provider.repository;

import dds.poi.model.Consulta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Nicolas on 06/11/2016.
 */
@Repository
public interface ConsultaRepository extends MongoRepository<Consulta, String> {
    List<Consulta> findByUsernameOrderByFechaAsc(String username);
    List<Consulta> findByCantidadResultadosGreaterThanEqual(int cantidadResultados);
}
