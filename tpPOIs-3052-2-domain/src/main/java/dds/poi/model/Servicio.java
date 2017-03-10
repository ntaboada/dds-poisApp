package dds.poi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.joda.time.DateTime;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Servicio {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length = 200)
	private String nombreServicio;

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	
	public abstract boolean estaDisponible(DateTime momento);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
