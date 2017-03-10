package dds.poi.model;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public interface CategoriaPOI {

	//Kilometros
	public static final double MINIMA_DISTANCIA_PARA_CERCANIA_GENERAL = 0.5;
	
	boolean coincideConBusqueda(String busqueda);

	boolean estaDisponible(DateTime momento);
	
	default boolean estaDisponible(DateTime momento, String nombreServicio) {
		return estaDisponible(momento);
	};
	
	default boolean estaCerca(Point coordenadasOrigen, Point coordenadasDestino) {
		return coordenadasOrigen.distance(coordenadasDestino) <= MINIMA_DISTANCIA_PARA_CERCANIA_GENERAL;
	}
	
}
