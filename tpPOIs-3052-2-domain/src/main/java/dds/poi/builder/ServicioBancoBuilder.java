package dds.poi.builder;

import dds.poi.model.ServicioBanco;

public class ServicioBancoBuilder extends Builder<ServicioBanco>{

	private ServicioBanco servicio = new ServicioBanco();
	
	public ServicioBancoBuilder nombreServicio(String nombreServicio) {
		this.servicio.setNombreServicio(nombreServicio);
		return this;
	}
	
	@Override
	public boolean isValidBuild() {
		return this.servicio.getNombreServicio()!=null;
	}

	@Override
	public ServicioBanco returnBuildObject() {
		return this.servicio;
	}

}
