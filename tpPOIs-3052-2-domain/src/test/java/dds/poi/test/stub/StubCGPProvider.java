package dds.poi.test.stub;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import dds.poi.dto.model.CentroDTO;
import dds.poi.dto.model.RangoServicioDTO;
import dds.poi.dto.model.ServicioDTO;
import dds.poi.provider.dataprovider.CGPProvider;
import dds.poi.test.builder.CentroDTOBuilder;
import dds.poi.test.builder.RangoServicioDTOBuilder;
import dds.poi.test.builder.ServicioDTOBuilder;

public class StubCGPProvider implements CGPProvider {

	private List<CentroDTO> centrosDTO;

	public StubCGPProvider() {
		this.centrosDTO = new ArrayList<CentroDTO>();

		CentroDTO centroDTO1 = this.crearCentroDTO(1, "Zona 1, Zona 2", "Domicilio CentroDTO 1");
		CentroDTO centroDTO2 = this.crearCentroDTO(2, "Zona 3, Zona 4", "Domicilio CentroDTO 2");
		CentroDTO centroDTO3 = this.crearCentroDTO(2, "Zona 5, Zona 6", "Domicilio CentroDTO 3");
		
		this.centrosDTO.add(centroDTO1);
		this.centrosDTO.add(centroDTO2);
		this.centrosDTO.add(centroDTO3);
	}

	private CentroDTO crearCentroDTO(int numeroCentro, String zonasCentroDTO, String domicilioCentroDTO) {
		RangoServicioDTO rangoDTO1ServicioDTO1 = new RangoServicioDTOBuilder().numeroDia(1).horaDesde(10).minutosDesde(30)
				.horaHasta(14).minutosHasta(30).build();

		RangoServicioDTO rangoDTO2ServicioDTO1 = new RangoServicioDTOBuilder().numeroDia(2).horaDesde(10).minutosDesde(30)
				.horaHasta(14).minutosHasta(30).build();

		RangoServicioDTO rangoDTO3ServicioDTO1 = new RangoServicioDTOBuilder().numeroDia(3).horaDesde(10).minutosDesde(30)
				.horaHasta(14).minutosHasta(30).build();

		ServicioDTO servicioDTO1CentroDTO = new ServicioDTOBuilder().nombre("Dummy 1").diaServicio(rangoDTO1ServicioDTO1)
				.diaServicio(rangoDTO2ServicioDTO1).diaServicio(rangoDTO3ServicioDTO1).build();

		RangoServicioDTO rangoDTO1ServicioDTO2 = new RangoServicioDTOBuilder().numeroDia(4).horaDesde(11).minutosDesde(30)
				.horaHasta(15).minutosHasta(30).build();
		
		RangoServicioDTO rangoDTO2ServicioDTO2 = new RangoServicioDTOBuilder().numeroDia(5).horaDesde(11).minutosDesde(30)
				.horaHasta(15).minutosHasta(30).build();

		RangoServicioDTO rangoDTO3ServicioDTO2 = new RangoServicioDTOBuilder().numeroDia(6).horaDesde(11).minutosDesde(30)
				.horaHasta(15).minutosHasta(30).build();

		ServicioDTO servicioDTO2CentroDTO = new ServicioDTOBuilder().nombre("Dummy 2").diaServicio(rangoDTO1ServicioDTO2)
				.diaServicio(rangoDTO2ServicioDTO2).diaServicio(rangoDTO3ServicioDTO2).build();

		CentroDTO centroDTO = new CentroDTOBuilder().domicilio(domicilioCentroDTO).director("DirDummy").numeroComuna(9)
				.telefono("TelDummy").zonasIncluidas(zonasCentroDTO).servicio(servicioDTO1CentroDTO)
				.servicio(servicioDTO2CentroDTO).build();
		return centroDTO;
	}

	@Override
	public List<CentroDTO> searchPOIs(String value) {
		Predicate<CentroDTO> predicate = centroDTO -> centroDTO
				.getZonasIncluidas().contains(value)
				|| centroDTO.getDomicilio().contains(value);
		return this.centrosDTO.stream().filter(predicate)
				.collect(Collectors.toList());
	}

}
