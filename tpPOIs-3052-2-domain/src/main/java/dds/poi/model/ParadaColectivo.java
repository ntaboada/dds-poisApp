package dds.poi.model;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class ParadaColectivo implements CategoriaPOI {

	//Kilometros
	private static final double RADIO_DE_CERCANIA = 0.1;
	
	private String lineaColectivo;

	@Override
	public boolean estaCerca(Point coordenadasOrigen, Point coordenadasDestino) {
		return coordenadasOrigen.distance(coordenadasDestino) <= RADIO_DE_CERCANIA;
	}

	@Override
	public boolean coincideConBusqueda(String busqueda) {
		return lineaColectivo.equalsIgnoreCase(busqueda);
	}

	@Override
	public boolean estaDisponible(DateTime momento) {
		return true;
	}
	
	public String getLineaColectivo() {
		return lineaColectivo;
	}

	public void setLineaColectivo(String lineaColectivo) {
		this.lineaColectivo = lineaColectivo;
	}

}
