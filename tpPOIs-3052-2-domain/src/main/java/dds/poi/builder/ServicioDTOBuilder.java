package dds.poi.builder;

import dds.poi.dto.model.RangoServicioDTO;
import dds.poi.dto.model.ServicioDTO;

public class ServicioDTOBuilder extends Builder<ServicioDTO>{
	
	private ServicioDTO servicio = new ServicioDTO();
	
	public ServicioDTOBuilder nombre(String servicio) {
		this.servicio.setNombreServicio(servicio);
		return this;
	}
	
	public ServicioDTOBuilder diaServicio(RangoServicioDTO rango) {
		this.servicio.addRangoServicio(rango);
		return this;
	}
	
	@Override
	public boolean isValidBuild() {
		return this.servicio.getDiasDeServicio()!=null && this.servicio.getDiasDeServicio().size()>0 && this.servicio.getNombreServicio()!=null;
	}

	@Override
	public ServicioDTO returnBuildObject() {
		return this.servicio;
	}

}
