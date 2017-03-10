package dds.poi.test.builder;

import dds.poi.builder.Builder;
import dds.poi.dto.model.RangoServicioDTO;

public class RangoServicioDTOBuilder extends Builder<RangoServicioDTO> {

	private RangoServicioDTO rangoServicio = new RangoServicioDTO(); 

	public RangoServicioDTOBuilder numeroDia(int numeroDia) {
		this.rangoServicio.setNumeroDia(numeroDia);
		return this;
	}
	
	public RangoServicioDTOBuilder horaDesde(int horaDesde) {
		this.rangoServicio.setHorarioDesde(horaDesde);
		return this;
	}
	
	public RangoServicioDTOBuilder horaHasta(int horaHasta) {
		this.rangoServicio.setHorarioHasta(horaHasta);
		return this;
	}
	public RangoServicioDTOBuilder minutosDesde(int minutosDesde) {
		this.rangoServicio.setMinutosDesde(minutosDesde);
		return this;
	}
	public RangoServicioDTOBuilder minutosHasta(int minutosHasta) {
		this.rangoServicio.setMinutosHasta(minutosHasta);
		return this;
	}
	
	@Override
	public boolean isValidBuild() {
		return this.rangoServicio.getNumeroDia()>0;
	}

	@Override
	public RangoServicioDTO returnBuildObject() {
		return this.rangoServicio;
	}

}
