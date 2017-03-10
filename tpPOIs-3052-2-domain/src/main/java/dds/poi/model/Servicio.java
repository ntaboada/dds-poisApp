package dds.poi.model;

import org.joda.time.DateTime;

public abstract class Servicio {

	private String nombreServicio;

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	
	public abstract boolean estaDisponible(DateTime momento);

}
