package dds.poi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CategoriaPOI {
	
	@Id
	@GeneratedValue
	private Long id;
	
	//Kilometros
	public static final double MINIMA_DISTANCIA_PARA_CERCANIA_GENERAL = 0.5;
	
	abstract boolean coincideConBusqueda(String busqueda);

	abstract boolean estaDisponible(DateTime momento);
	
	public boolean estaDisponible(DateTime momento, String nombreServicio) {
		return estaDisponible(momento);
	};
	
	public boolean estaCerca(Point coordenadasOrigen, Point coordenadasDestino) {
		return coordenadasOrigen.distance(coordenadasDestino) <= MINIMA_DISTANCIA_PARA_CERCANIA_GENERAL;
	}
		
}
