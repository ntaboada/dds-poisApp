package dds.poi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.uqbar.geodds.Point;

@Entity
public  class Rubro {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
    private double radioDeCercania;
	
	@Column(length = 100)
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