package dds.poi.builder;

import dds.poi.model.ParadaColectivo;

public class ParadaColectivoBuilder extends Builder<ParadaColectivo> {

	private ParadaColectivo paradaColectivo = new ParadaColectivo();
	
	public ParadaColectivoBuilder lineaColectivo(String lineaColectivo) {
		this.paradaColectivo.setLineaColectivo(lineaColectivo);
		return this;
	}
	
	@Override
	public boolean isValidBuild() {
		return this.paradaColectivo.getLineaColectivo()!=null;
	}

	@Override
	public ParadaColectivo returnBuildObject() {
		return this.paradaColectivo;
	}

}
