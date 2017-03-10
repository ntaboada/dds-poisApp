package dds.poi.builder;

import dds.poi.model.Rubro;

public class RubroBuilder extends Builder<Rubro> {

	private Rubro rubro = new Rubro();
	
	public RubroBuilder nombreRubro(String rubro) {
		this.rubro.setNombreRubro(rubro);
		return this;
	}
	
	public RubroBuilder radioCercania(double radioCercania) {
		this.rubro.setRadioDeCercania(radioCercania);
		return this;
	}
	
	@Override
	public boolean isValidBuild() {
		return this.rubro.getNombreRubro()!=null && this.rubro.getRadioDeCercania()>0;
	}

	@Override
	public Rubro returnBuildObject() {
		return this.rubro;
	}

	
	
}
