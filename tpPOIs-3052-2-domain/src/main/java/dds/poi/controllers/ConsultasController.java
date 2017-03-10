package dds.poi.controllers;

import dds.poi.dto.model.FiltersDTO;
import dds.poi.services.ConsultasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Nicolas on 07/11/2016.
 */
@RestController
@RequestMapping(value = "/api")
public class ConsultasController {
    @Autowired
    private ConsultasService consultasService;

    @RequestMapping(value = "/consultas", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> searchConsultasXCantidadDeResultados(@RequestParam int cantidadResultados){
        return new ResponseEntity<>(consultasService.retrieveConsultas(cantidadResultados), HttpStatus.OK);
    }

    @RequestMapping(value = "/consultasUsuario", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> searchConsultasXUsuario(@RequestParam String username) {
        return new ResponseEntity<>(consultasService.fechasEnLasQueConsultoElUsuario(username), HttpStatus.OK);
    }

}
