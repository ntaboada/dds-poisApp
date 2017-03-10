package dds.poi.dto.model;

import java.util.ArrayList;
import java.util.List;

public class ServicioDTO {
    private String nombreServicio;
    private List<RangoServicioDTO> diasDeServicio = new ArrayList<RangoServicioDTO>();

	public String getNombreServicio() {

        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public List<RangoServicioDTO> getDiasDeServicio() {
        return diasDeServicio;
    }

    public void setDiasDeServicio(List<RangoServicioDTO> diasDeServicio) {
        this.diasDeServicio = diasDeServicio;
    }

	public void addRangoServicio(RangoServicioDTO rango) {
		this.diasDeServicio.add(rango);
	}
}
