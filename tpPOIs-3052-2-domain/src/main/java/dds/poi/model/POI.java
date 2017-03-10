package dds.poi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

import com.fasterxml.jackson.annotation.JsonProperty;

import dds.poi.model.converter.PointConverter;

@Entity
public class POI {

	@Id
	@GeneratedValue
	private Long identificador;

	@Column(length = 100)
	private String nombrePOI;

	@Column(length = 50)
	private String image;
	
	@Column(length = 200)
	private String direccionPrincipal;

	@Column(length = 200)
	private String direccionSecundaria;

	@Column
	@Convert(converter = PointConverter.class)
	private Point coordenadas;

	@OneToOne(cascade = CascadeType.ALL)
	private CategoriaPOI categoria;

	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	private List<Etiqueta> etiquetas = new ArrayList<Etiqueta>();

	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	private List<Review> reviewsList = new ArrayList<>();

	public int calificacionGeneralReviews() {
		int calificacionGeneral = 0;

		if (!reviewsList.isEmpty()) {
			int puntajeTotal = reviewsList.stream()
					.mapToInt(review -> review.getPuntaje()).sum();
			calificacionGeneral = puntajeTotal / reviewsList.size();
		}
		return calificacionGeneral;
	}

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
		return this.coincidenEtiquetas(busqueda)
				|| categoria.coincideConBusqueda(busqueda);
	}

	private boolean coincidenEtiquetas(String busqueda) {
		Predicate<Etiqueta> predicate = etiqueta -> etiqueta.getEtiqueta()
				.equalsIgnoreCase(busqueda);
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

	@JsonProperty("coordenadas")
	public String[] getCoordenadasForJson() {
		if (this.coordenadas != null) {
			return new String[] { String.valueOf(this.coordenadas.latitude()),
					String.valueOf(this.coordenadas.longitude()) };
		} else {
			return new String[] { "0", "0" };
		}
	}

	public void setCoordenadas(Point coordenadas) {
		this.coordenadas = coordenadas;
	}

	public Long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Long identificador) {
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

	public List<Etiqueta> getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(List<Etiqueta> etiquetas) {
		this.etiquetas = etiquetas;
	}

	public List<Review> getReviewsList() {
		return reviewsList;
	}

	public void setReviewsList(List<Review> reviewsList) {
		this.reviewsList = reviewsList;
	}

	public void addEtiqueta(String etiquetaText) {
		Etiqueta etiqueta = new Etiqueta();
		etiqueta.setEtiqueta(etiquetaText);
		this.etiquetas.add(etiqueta);
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void addAllEtiquetas(List<String> etiquetasAUpdatear) {
		for(String etiqueta : etiquetasAUpdatear) {
			this.addEtiqueta(etiqueta);
		}
	}

}
