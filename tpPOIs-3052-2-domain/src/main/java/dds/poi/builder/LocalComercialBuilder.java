package dds.poi.builder;

import dds.poi.model.LocalComercial;
import dds.poi.model.Rango;
import dds.poi.model.Rubro;

public class LocalComercialBuilder extends Builder<LocalComercial> {

	private LocalComercial localComercial = new LocalComercial();
	
	public LocalComercialBuilder rubro(Rubro rubro) {
		this.localComercial.setRubro(rubro);
		return this;
	}
	
	public LocalComercialBuilder rangoDisponible(Rango rango) {
		this.localComercial.addRango(rango);
		return this;
	}
	
	@Override
	public boolean isValidBuild() {
		return this.localComercial.getRubro()!=null && this.localComercial.getRangosDeDisponibilidad()!=null
				&& this.localComercial.getRangosDeDisponibilidad().size()>0;
	}

	@Override
	public LocalComercial returnBuildObject() {
		return this.localComercial;
	}

}
