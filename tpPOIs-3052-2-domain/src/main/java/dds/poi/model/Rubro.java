package dds.poi.model;

import org.uqbar.geodds.Point;

/**
 * Created by nicolas.taboada on 13/04/2016.
 */
public  class Rubro {

    private double radioDeCercania;
    private String nombreRubro;

    public boolean estaCerca(Point coordenadasOrigen, Point coordenadasDestino) {
        return coordenadasOrigen.distance(coordenadasDestino) <= this.radioDeCercania;
    }

    public boolean coincideConBusqueda(String busqueda) {
        return nombreRubro.equalsIgnoreCase(busqueda);
    }

    public double getRadioDeCercania() {
        return radioDeCercania;
    }

    public void setRadioDeCercania(double radioDeCercania) {
        this.radioDeCercania = radioDeCercania;
    }

    public String getNombreRubro() {
        return nombreRubro;
    }

    public void setNombreRubro(String nombreRubro) {
        this.nombreRubro = nombreRubro;
    }

}