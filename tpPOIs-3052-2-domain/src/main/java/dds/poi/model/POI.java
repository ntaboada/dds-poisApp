package dds.poi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;


public class POI {

	private CategoriaPOI categoria;
	private Point coordenadas;
	private int identificador;
	private String nombrePOI;
	private String direccionPrincipal;
	private String direccionSecundaria;
	private List<String> etiquetas = new ArrayList<String>();

	public boolean estaCerca(Point coordenadasOrigen) {
		return categoria.estaCerca(coordenadas, coordenadasOrigen);
	}
	
	public boolean estaDisponible(DateTime momento) {
		return categoria.estaDisponible(momento);
	}
	
	public boolean estaDisponible(DateTime momento, String nombreServicio) {
		return categoria.estaDisponible(momento, nombreServicio);
	}
	
	public boolean coincideConBusqueda(String busqueda) {
		return this.coincidenEtiquetas(busqueda) || categoria.coincideConBusqueda(busqueda);
	}
	
	private boolean coincidenEtiquetas(String busqueda) {
		Predicate<String> predicate = etiqueta -> etiqueta.equalsIgnoreCase(busqueda);
		return this.etiquetas.stream().anyMatch(predicate);
	}

	public CategoriaPOI getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaPOI categoria) {
		this.categoria = categoria;
	}
	public Point getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(Point coordenadas) {
		this.coordenadas = coordenadas;
	}
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public String getNombrePOI() {
		return nombrePOI;
	}
	public void setNombrePOI(String nombrePOI) {
		this.nombrePOI = nombrePOI;
	}
	public String getDireccionPrincipal() {
		return direccionPrincipal;
	}
	public void setDireccionPrincipal(String direccionPrincipal) {
		this.direccionPrincipal = direccionPrincipal;
	}
	public String getDireccionSecundaria() {
		return direccionSecundaria;
	}
	public void setDireccionSecundaria(String direccionSecundaria) {
		this.direccionSecundaria = direccionSecundaria;
	}
	public List<String> getEtiquetas() {
		return etiquetas;
	}
	public void setEtiquetas(List<String> etiquetas) {
		this.etiquetas = etiquetas;
	}

	public void addEtiqueta(String etiqueta) {
		this.etiquetas.add(etiqueta);
	}
	
}
