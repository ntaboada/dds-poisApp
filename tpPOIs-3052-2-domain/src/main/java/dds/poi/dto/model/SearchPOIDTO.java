package dds.poi.dto.model;

import dds.poi.model.POI;

/**
 * Created by Nicolas on 25/09/2016.
 */
public class SearchPOIDTO {
    private Long identificador;
    private String nombre;
    private String direccion;
    private boolean isFavoritePOI;

    public SearchPOIDTO(POI poi){
        this.identificador = poi.getIdentificador();
        this.nombre = poi.getNombrePOI();
        this.direccion = poi.getDireccionPrincipal();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isFavoritePOI() {
        return isFavoritePOI;
    }

    public void setFavoritePOI(boolean favoritePOI) {
        isFavoritePOI = favoritePOI;
    }

    public Long getIdentificador() {return identificador; }

    public void setIdentificador(Long identificador) {this.identificador = identificador; }
}
