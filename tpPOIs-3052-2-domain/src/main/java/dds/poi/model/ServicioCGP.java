package dds.poi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.joda.time.DateTime;

@Entity
public class ServicioCGP extends Servicio {
	
	@OneToMany(fetch=FetchType.EAGER)
	private List<Rango> horarioDeAtencion = new ArrayList<Rango>();

	public List<Rango> getHorarioDeAtencion() {
		return horarioDeAtencion;
	}

	public void setHorarioDeAtencion(List<Rango> horarioDeAtencion) {
		this.horarioDeAtencion = horarioDeAtencion;
	}

	@Override
	public boolean estaDisponible(DateTime momento) {
		Predicate<Rango> predicate = rango -> rango.incluyeElMomento(momento);
		return this.horarioDeAtencion.stream().anyMatch(predicate);
	}

	public void addHorario(Rango rango) {
		this.horarioDeAtencion.add(rango);
	}

}
