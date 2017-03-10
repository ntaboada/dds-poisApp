package dds.poi.builder;

import org.uqbar.geodds.Point;

import dds.poi.model.Banco;
import dds.poi.model.CGP;
import dds.poi.model.LocalComercial;
import dds.poi.model.POI;
import dds.poi.model.ParadaColectivo;

public class POIBuilder extends Builder<POI>{

	private POI poi = new POI();
	
	public POIBuilder coordenadas(Point point) {
		this.poi.setCoordenadas(point);
		return this;
	}
	
	public POIBuilder imagen(String icono) {
		this.poi.setImage(icono);
		return this;
	}
	
	public POIBuilder direccionPrincipal(String direccion) {
		this.poi.setDireccionPrincipal(direccion);
		return this;
	}
	
	public POIBuilder direccionSecundaria(String direccion) {
		this.poi.setDireccionSecundaria(direccion);
		return this;
	}
	
	public POIBuilder identificador(Long id) {
		this.poi.setIdentificador(id);
		return this;
	}
	
	public POIBuilder nombre(String nombre) {
		this.poi.setNombrePOI(nombre);
		return this;
	}
	
	public POIBuilder etiqueta(String etiqueta) {
		this.poi.addEtiqueta(etiqueta);
		return this;
	}
	
	public POIBuilder paradaColectivo(ParadaColectivo paradaColectivo) {
		this.poi.setCategoria(paradaColectivo);
		return this;
	}
	
	public POIBuilder localComercial(LocalComercial local) {
		this.poi.setCategoria(local);
		return this;
	}
	
	public POIBuilder banco(Banco banco) {
		this.poi.setCategoria(banco);
		return this;
	}
	
	public POIBuilder cgp(CGP cgp) {
		this.poi.setCategoria(cgp);
		return this;
	}
	
	@Override
	public boolean isValidBuild() {
		return this.poi.getCategoria()!=null;
	}

	@Override
	public POI returnBuildObject() {
		return this.poi;
	}

}
