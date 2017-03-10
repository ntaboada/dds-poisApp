package dds.poi.builder;

import org.uqbar.geodds.Point;

import dds.poi.dto.model.CentroDTO;
import dds.poi.dto.model.ServicioDTO;

public class CentroDTOBuilder extends Builder<CentroDTO> {

	private CentroDTO centro = new CentroDTO();
	
	public CentroDTOBuilder domicilio(String domicilio) {
		this.centro.setDomicilio(domicilio);
		return this;
	}
	
	public CentroDTOBuilder domicilioSecundario(String domicilio) {
		this.centro.setDomicilioSecundario(domicilio);
		return this;
	}
	
	public CentroDTOBuilder coordenadas(double latitude, double longitude) {
		this.centro.setCoordenadas(new Point(latitude, longitude));
		return this;
	}
	
	public CentroDTOBuilder director(String nombre) {
		this.centro.setNombreDirector(nombre);
		return this;
	}
	
	public CentroDTOBuilder nombre(String nombre) {
		this.centro.setNombre(nombre);
		return this;
	}
	
	public CentroDTOBuilder icono(String icono) {
		this.centro.setIcono(icono);
		return this;
	}
	
	public CentroDTOBuilder numeroComuna(int comuna) {
		this.centro.setNumeroComuna(comuna);
		return this;
	}
	
	public CentroDTOBuilder telefono(String telefono) {
		this.centro.setTelefono(telefono);
		return this;
	}
	
	public CentroDTOBuilder zonasIncluidas(String zonas) {
		this.centro.setZonasIncluidas(zonas);
		return this;
	}
	
	public CentroDTOBuilder servicio(ServicioDTO servicio) {
		this.centro.addServicio(servicio);
		return this;
	}
	
	@Override
	public boolean isValidBuild() {
		return this.centro.getDomicilio()!=null && this.centro.getNombreDirector()!=null && this.centro.getNumeroComuna()>0
				&& this.centro.getServicios()!=null && this.centro.getServicios().size()>0 && this.centro.getTelefono()!=null
				&& this.centro.getZonasIncluidas()!=null;
	}

	@Override
	public CentroDTO returnBuildObject() {
		return this.centro;
	}

}
