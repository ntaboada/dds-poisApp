package dds.poi.dto.model;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

/**
 * Created by nicolas on 01/05/16.
 */
public class CentroDTO {

	private String icono;
	private String nombre;
    private int numeroComuna;
    private String zonasIncluidas;
    private String nombreDirector;
    private String domicilio;
    private String domicilioSecundario;
    private String telefono;
    private List<ServicioDTO> servicios = new ArrayList<ServicioDTO>();
    private Point coordenadas;

    public int getNumeroComuna() {
        return numeroComuna;
    }

    public void setNumeroComuna(int numeroComuna) {
        this.numeroComuna = numeroComuna;
    }

    public String getZonasIncluidas() {
        return zonasIncluidas;
    }

    public void setZonasIncluidas(String zonasIncluidas) {
        this.zonasIncluidas = zonasIncluidas;
    }

    public String getNombreDirector() {
        return nombreDirector;
    }

    public void setNombreDirector(String nombreDirector) {
        this.nombreDirector = nombreDirector;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

	public List<ServicioDTO> getServicios() {
		return servicios;
	}

	public void setServicios(List<ServicioDTO> servicios) {
		this.servicios = servicios;
	}
	
	public void addServicio(ServicioDTO servicio) {
		this.servicios.add(servicio);
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Point getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(Point coordenadas) {
		this.coordenadas = coordenadas;
	}

	public String getDomicilioSecundario() {
		return domicilioSecundario;
	}

	public void setDomicilioSecundario(String domicilioSecundario) {
		this.domicilioSecundario = domicilioSecundario;
	}
}
