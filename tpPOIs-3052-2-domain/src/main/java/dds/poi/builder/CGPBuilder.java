package dds.poi.builder;

import java.util.List;

import org.uqbar.geodds.Polygon;

import dds.poi.model.CGP;
import dds.poi.model.Servicio;
import dds.poi.model.ServicioCGP;

public class CGPBuilder extends Builder<CGP> {

	private CGP cgp = new CGP();
	
	public CGPBuilder zonaCGP(Polygon zona) {
		this.cgp.setZonaCGP(zona);
		return this;
	}
	
	public CGPBuilder servicio(ServicioCGP servicio) {
		this.cgp.addServicio(servicio);
		return this;
	}
	
	public CGPBuilder servicios(List<Servicio> servicios) {
		this.cgp.setServicios(servicios);
		return this;
	}
	
	@Override
	public boolean isValidBuild() {
		return this.cgp.getServicios()!=null && this.cgp.getServicios().size()>0;
	}

	@Override
	public CGP returnBuildObject() {
		return this.cgp;
	}
	
}
