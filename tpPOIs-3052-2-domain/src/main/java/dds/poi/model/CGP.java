package dds.poi.model;

import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP extends CategoriaConServicios {
	
	private Polygon zonaCGP;

	public  CGP(){
		super();
	}

	public CGP(List<Servicio> listServicios) {
		super(listServicios);
	}

	public Polygon getZonaCGP() {
		return zonaCGP;
	}

	public void setZonaCGP(Polygon zonaCGP) {
		this.zonaCGP = zonaCGP;
	}

	@Override
	public boolean estaCerca(Point coordenadasOrigen, Point coordenadasDestino) {
		return zonaCGP.isInside(coordenadasDestino);
	}
	
}
