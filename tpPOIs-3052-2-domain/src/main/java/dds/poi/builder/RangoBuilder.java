package dds.poi.builder;

import dds.poi.model.Rango;

public class RangoBuilder extends Builder<Rango>{

	private Rango rango = new Rango();
	
	public RangoBuilder dia(int dayOfWeek) {
		this.rango.setDayOfWeek(dayOfWeek);
		return this;
	}
	
	public RangoBuilder horaDesde(int horaDesde) {
		this.rango.setOpenHour(horaDesde);
		return this;
	}
	
	public RangoBuilder horaHasta(int horaHasta) {
		this.rango.setCloseHour(horaHasta);
		return this;
	}
	public RangoBuilder minutosDesde(int minutosDesde) {
		this.rango.setOpenHourMinutes(minutosDesde);
		return this;
	}
	public RangoBuilder minutosHasta(int minutosHasta) {
		this.rango.setCloseHourMinutes(minutosHasta);
		return this;
	}
	
	@Override
	public boolean isValidBuild() {
		return this.rango.getDayOfWeek()>0;
	}

	@Override
	public Rango returnBuildObject() {
		return this.rango;
	}

}
