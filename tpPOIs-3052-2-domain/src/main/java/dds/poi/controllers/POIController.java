package dds.poi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dds.poi.dto.model.FiltersDTO;
import dds.poi.dto.model.POIByNameRequestDTO;
import dds.poi.dto.model.ReviewDTO;
import dds.poi.dto.model.ReviewForPOIByNameRequestDTO;
import dds.poi.services.POIService;
import dds.poi.services.SearchService;

/**
 * Created by Nicolas on 25/09/2016.
 */
@RestController
@RequestMapping(value = "/api")
public class POIController {

    @Autowired
    SearchService searchService;
    @Autowired
    POIService poiService;

    @RequestMapping(value = "/searchPois", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> searchPois(@RequestBody FiltersDTO filters, @RequestParam int idLoggedUser){
        return new ResponseEntity<>(searchService.retrievePOIs(filters, idLoggedUser), HttpStatus.OK);
    }

    @RequestMapping(value = "/poiDetails/{idPoi}", method = RequestMethod.GET)
    public ResponseEntity<?> getPOIDetails(@PathVariable int idPoi, @RequestParam int idLoggedUser){
        return new ResponseEntity<>(poiService.getPOIDetails(idPoi, idLoggedUser), HttpStatus.OK);
    }

    @RequestMapping(value = "/poiDetails/", method = RequestMethod.POST)
    public ResponseEntity<?> getPOIDetailsFromPOIName(@RequestBody POIByNameRequestDTO request){
        return new ResponseEntity<>(poiService.getPOIDetails(request.getPoiName(), request.getIdLoggedUser()), HttpStatus.OK);
    }

    @RequestMapping(value = "/poiDetails/review", method = RequestMethod.POST)
    public ResponseEntity<?> saveReview(@RequestBody ReviewDTO reviewDTO, @RequestParam int idPoi){
        poiService.saveReview(reviewDTO, idPoi);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/poiDetails/reviewByName/", method = RequestMethod.POST)
    public ResponseEntity<?> saveReviewForPOIByName(@RequestBody ReviewForPOIByNameRequestDTO request){
        poiService.saveReview(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
