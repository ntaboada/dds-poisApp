package dds.poi.builder;

import dds.poi.model.Rango;
import dds.poi.model.ServicioCGP;

public class ServicioCGPBuilder extends Builder<ServicioCGP> {

	private ServicioCGP servicio = new ServicioCGP();
	
	public ServicioCGPBuilder nombreServicio(String nombre) {
		this.servicio.setNombreServicio(nombre);
		return this;
	}
	
	public ServicioCGPBuilder horarioDeAtencion(Rango rango) {
		this.servicio.addHorario(rango);
		return this;
	}
	
	@Override
	public boolean isValidBuild() {
		return this.servicio.getNombreServicio()!=null && this.servicio.getHorarioDeAtencion()!=null
				&& this.servicio.getHorarioDeAtencion().size()>0;
	}

	@Override
	public ServicioCGP returnBuildObject() {
		return this.servicio;
	}

}
