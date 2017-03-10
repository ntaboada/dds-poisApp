package dds.poi.dto.model;

import dds.poi.model.CategoriaPOI;
import dds.poi.model.POI;
import dds.poi.model.Review;

import java.util.List;

/**
 * Created by Nicolas on 25/09/2016.
 */
public class POIDetailsDTO {
    private POI poi;
    private int calificacionGeneral;
    private boolean esFavorito;

    public POIDetailsDTO(POI poi,  boolean esFavorito) {
        this.poi = poi;
        this.calificacionGeneral = poi.calificacionGeneralReviews();
        this.esFavorito = esFavorito;
    }

    public POI getPoi() {
        return poi;
    }

    public void setPoi(POI poi) {
        this.poi = poi;
    }

    public int getCalificacionGeneral() {
        return calificacionGeneral;
    }

    public void setCalificacionGeneral(int calificacionGeneral) {
        this.calificacionGeneral = calificacionGeneral;
    }

    public boolean isEsFavorito() {
        return esFavorito;
    }

    public void setEsFavorito(boolean esFavorito) {
        this.esFavorito = esFavorito;
    }


}
