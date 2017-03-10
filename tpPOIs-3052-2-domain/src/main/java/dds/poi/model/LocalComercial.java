package dds.poi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

@Entity
public class LocalComercial extends CategoriaPOI {
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Rubro rubro;

	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	private List<Rango> rangosDeDisponibilidad;

	public LocalComercial() {
		rangosDeDisponibilidad = new ArrayList<Rango>();
	}

	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}

	public List<Rango> getRangosDeDisponibilidad() {
		return rangosDeDisponibilidad;
	}

	public void setRangosDeDisponibilidad(List<Rango> rangosDeDisponibilidad) {
		this.rangosDeDisponibilidad = rangosDeDisponibilidad;
	}

	@Override
	public boolean estaCerca(Point coordenadasOrigen, Point coordenadasDestino) {
		return this.rubro.estaCerca(coordenadasOrigen, coordenadasDestino);
	}

	@Override
	public boolean coincideConBusqueda(String busqueda) {
		return rubro.coincideConBusqueda(busqueda);
	}

	@Override
	public boolean estaDisponible(DateTime momento) {
		Predicate<Rango> predicate = rango -> rango.incluyeElMomento(momento);
		return this.rangosDeDisponibilidad.stream().anyMatch(predicate);
	}

	public void addRango(Rango rango) {
		this.rangosDeDisponibilidad.add(rango);
	}
	
}