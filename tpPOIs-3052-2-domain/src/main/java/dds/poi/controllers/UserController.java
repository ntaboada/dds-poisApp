package dds.poi.controllers;

import dds.poi.model.Consulta;
import dds.poi.services.FavoriteService;
import dds.poi.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api")
public class UserController {
    @Autowired
    LoginService loginService;
    @Autowired
    FavoriteService favoriteService;

    @RequestMapping(value="/favoritePOI", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeFavoritePOI(@RequestParam long idPoi, @RequestParam long idLoggedUser){
        favoriteService.removeFavoritePOI(idPoi, idLoggedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/favoritePOI", method = RequestMethod.POST)
    public ResponseEntity<?> saveFavoritePOI(@RequestParam long idPoi, @RequestParam long idLoggedUser){
        favoriteService.saveFavoritePOI(idPoi, idLoggedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ResponseEntity<?> checkCredentials(@RequestParam String username, @RequestParam String password){
        try {
            if(loginService.checkCredentials(username, password))
            	return new ResponseEntity<>(loginService.getUserId(username, password), HttpStatus.OK);
            else
            	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
        	return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }


}
