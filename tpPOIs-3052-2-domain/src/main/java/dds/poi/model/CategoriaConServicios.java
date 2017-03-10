package dds.poi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import org.joda.time.DateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CategoriaConServicios extends CategoriaPOI {

	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	protected List<Servicio> servicios = new ArrayList<Servicio>();

	public CategoriaConServicios() {
		servicios = new ArrayList<>();
	}

	public CategoriaConServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public void addServicio(Servicio servicio) {
		this.servicios.add(servicio);
	}

	@Override
	public boolean coincideConBusqueda(String busqueda) {
		String busquedaUpperCase = busqueda.toUpperCase();
		Predicate<Servicio> predicate = servicio -> servicio
				.getNombreServicio().toUpperCase().contains(busquedaUpperCase);
		return servicios.stream().anyMatch(predicate);
	}

	@Override
	public boolean estaDisponible(DateTime momento, String nombreServicio) {
		return this.estaDisponibleElServicio(momento, nombreServicio);
	}

	@Override
	public boolean estaDisponible(DateTime momento) {
		return this.hayAlgunServicioDisponible(momento);
	}

	public boolean hayAlgunServicioDisponible(DateTime momento) {
		Predicate<Servicio> predicate = servicio -> servicio
				.estaDisponible(momento);
		return this.servicios.stream().anyMatch(predicate);
	}

	public boolean estaDisponibleElServicio(DateTime momento,
			String nombreServicio) {
		Predicate<Servicio> predicate = servicio -> servicio
				.getNombreServicio().equalsIgnoreCase(nombreServicio)
				&& servicio.estaDisponible(momento);
		return this.servicios.stream().anyMatch(predicate);
	}
}
