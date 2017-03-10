package dds.poi.dto.model;

/**
 * Created by nicolas on 01/05/16.
 */
public class RangoServicioDTO {
    private int numeroDia;
    private int horarioDesde;
    private int horarioHasta;
    private int minutosDesde;
    private int minutosHasta;

    public RangoServicioDTO(int numeroDia, int horarioDesde, int horarioHasta, int minutosDesde, int minutosHasta) {
        this.numeroDia = numeroDia;
        this.horarioDesde = horarioDesde;
        this.horarioHasta = horarioHasta;
        this.minutosDesde = minutosDesde;
        this.minutosHasta = minutosHasta;
    }

    public RangoServicioDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getNumeroDia() {

        return numeroDia;
    }

    public void setNumeroDia(int numeroDia) {
        this.numeroDia = numeroDia;
    }

    public int getHorarioDesde() {
        return horarioDesde;
    }

    public void setHorarioDesde(int horarioDesde) {
        this.horarioDesde = horarioDesde;
    }

    public int getHorarioHasta() {
        return horarioHasta;
    }

    public void setHorarioHasta(int horarioHasta) {
        this.horarioHasta = horarioHasta;
    }

    public int getMinutosDesde() {
        return minutosDesde;
    }

    public void setMinutosDesde(int minutosDesde) {
        this.minutosDesde = minutosDesde;
    }

    public int getMinutosHasta() {
        return minutosHasta;
    }

    public void setMinutosHasta(int minutosHasta) {
        this.minutosHasta = minutosHasta;
    }
}
