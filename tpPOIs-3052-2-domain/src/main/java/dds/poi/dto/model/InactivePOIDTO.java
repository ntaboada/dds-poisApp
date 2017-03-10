package dds.poi.dto.model;

/**
 * Created by Nicolas on 13/06/2016.
 */
public class InactivePOIDTO {
    private String valorBusqueda;
    private String fechaBusqueda;

    public InactivePOIDTO(String valorBusqueda, String fechaBusqueda) {
        this.valorBusqueda = valorBusqueda;
        this.fechaBusqueda = fechaBusqueda;
    }

    public String getValorBusqueda() {
        return valorBusqueda;
    }

    public void setValorBusqueda(String valorBusqueda) {
        this.valorBusqueda = valorBusqueda;
    }

    public String getFechaBusqueda() {
        return fechaBusqueda;
    }

    public void setFechaBusqueda(String fechaBusqueda) {
        this.fechaBusqueda = fechaBusqueda;
    }
}
