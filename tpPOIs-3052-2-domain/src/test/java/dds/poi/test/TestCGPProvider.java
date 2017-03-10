package dds.poi.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import dds.poi.dto.model.CentroDTO;

public class TestCGPProvider extends Inicializar {

	/* Tests CGPProvider con Stub */

	@Test
	public void testBuscarCGPsConResultadoPorZonaIncluida() {
		List<CentroDTO> centrosDTO = this.cgpProvider.searchPOIs("Zona 1");

		Assert.assertEquals(1, centrosDTO.size());
	}

	@Test
	public void testBuscarCGPsConResultadoPorDomicilio() {
		List<CentroDTO> centrosDTO = this.cgpProvider
				.searchPOIs("Domicilio CentroDTO 2");

		Assert.assertEquals(1, centrosDTO.size());
	}

	@Test
	public void testBuscarCGPsSinResultado() {
		List<CentroDTO> centrosDTO = this.cgpProvider
				.searchPOIs("No me devuelvas ningun resultado");

		Assert.assertEquals(0, centrosDTO.size());
	}

	/* Fin Tests */

}
