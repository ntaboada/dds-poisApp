package dds.poi.adapter;
import dds.poi.builder.CGPBuilder;
import dds.poi.builder.POIBuilder;
import dds.poi.dto.model.CentroDTO;
import dds.poi.dto.model.RangoServicioDTO;
import dds.poi.dto.model.ServicioDTO;
import dds.poi.model.*;

import java.util.ArrayList;
import java.util.List;

public class CentroDTOAdapter implements Adapter<List<CentroDTO>, List<POI>> {
	@Override
	public List<POI> adapt(List<CentroDTO> centrosDTO) {
		List<POI> poiCGPList = new ArrayList<POI>();
		centrosDTO.forEach(centroDTO -> poiCGPList.add(adaptCentroDTO(centroDTO)));
		return poiCGPList;
	}

	private POI adaptCentroDTO(CentroDTO centroDto) {
		String direccionCGP = centroDto.getDomicilio();
		List<ServicioDTO> listServiciosDTO = centroDto.getServicios();
		List<Rango> rangosDisponibilidadServicio = new ArrayList<>();
		List<Servicio> listServicios = new ArrayList<Servicio>();

		for (ServicioDTO servicioDTO:listServiciosDTO) {
			servicioDTO.getDiasDeServicio().forEach(diaServicio -> rangosDisponibilidadServicio.add(this.rangoDTOtoRango(diaServicio)));
			ServicioCGP servicioCGP = new ServicioCGP();
			servicioCGP.setNombreServicio(servicioDTO.getNombreServicio());
			servicioCGP.setHorarioDeAtencion(rangosDisponibilidadServicio);
			listServicios.add(servicioCGP);
		}
		
		CGP cgp = new CGPBuilder().servicios(listServicios).build();
		
		POI cgpPoi = new POIBuilder().imagen(centroDto.getIcono()).
				direccionPrincipal(direccionCGP).cgp(cgp).
				direccionSecundaria(centroDto.getDomicilioSecundario()).
				nombre(centroDto.getNombre())
				.coordenadas(centroDto.getCoordenadas()).build();
		
		return cgpPoi;
	}

	private Rango rangoDTOtoRango(RangoServicioDTO rangoDTO){
		Rango parsedRango = new Rango();
		parsedRango.setDayOfWeek(rangoDTO.getNumeroDia());
		parsedRango.setOpenHour(rangoDTO.getHorarioDesde());
		parsedRango.setOpenHourMinutes(rangoDTO.getMinutosDesde());
		parsedRango.setCloseHour(rangoDTO.getHorarioHasta());
		parsedRango.setCloseHourMinutes(rangoDTO.getMinutosHasta());
		return parsedRango;
	}

}
