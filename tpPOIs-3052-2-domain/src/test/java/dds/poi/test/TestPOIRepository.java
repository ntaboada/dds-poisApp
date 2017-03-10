package dds.poi.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import dds.poi.builder.POIBuilder;
import dds.poi.builder.ParadaColectivoBuilder;
import dds.poi.model.POI;
import dds.poi.model.ParadaColectivo;
import dds.poi.provider.repository.POIRepository;

public class TestPOIRepository extends Inicializar {

	private POI poiConflictivoQueVaAHacerFallarLosTests;
	
	@Before
	public void init() {
		ParadaColectivo parada = new ParadaColectivoBuilder().lineaColectivo("180").build();
		
		this.poiConflictivoQueVaAHacerFallarLosTests = new POIBuilder().coordenadas(new Point(0, 0)).paradaColectivo(parada)
				.direccionPrincipal("Avenida Alberdi 3400").identificador(1092).nombre("Parada del 180").build();
	}
	
	/*Test Metodos de Repository*/
	
	@Test
	public void testAgregarPOI() {

		ParadaColectivo parada = new ParadaColectivoBuilder().lineaColectivo("180").build();
		
		POI newPOI = new POIBuilder().coordenadas(new Point(0, 0)).paradaColectivo(parada)
				.direccionPrincipal("Avenida Alberdi 3400").identificador(10).nombre("Parada del 180").build();
		
		POIRepository.getInstance().create(newPOI);
		
		Assert.assertEquals(3, POIRepository.getInstance().findAll().size());
	}
	
	@Test
	public void testActualizarPOIOk() {
		this.almacenDonManolo.setDireccionPrincipal("Cambio la direccion del Almacen Don Manolo");
		
		boolean poiUpdated = POIRepository.getInstance().update(this.almacenDonManolo);
		
		Assert.assertTrue(poiUpdated);
	}
	
	@Test
	public void testActualizarPOIConError() {
		boolean poiUpdated = POIRepository.getInstance().update(this.poiConflictivoQueVaAHacerFallarLosTests);
		
		Assert.assertFalse(poiUpdated);
	}
	
	@Test
	public void testEliminarPOIOk() {
		boolean poiDeleted = POIRepository.getInstance().delete(this.almacenDonManolo);
		
		Assert.assertTrue(poiDeleted);
		Assert.assertEquals(1, POIRepository.getInstance().findAll().size());
	}
	
	@Test
	public void testEliminarPOIConError() {
		boolean poiDeleted = POIRepository.getInstance().delete(this.poiConflictivoQueVaAHacerFallarLosTests);
		
		Assert.assertFalse(poiDeleted);
		Assert.assertEquals(2, POIRepository.getInstance().findAll().size());
	}
	
	@Test
	public void testBuscarPOIPorIdOk() {
		POI poiResult = POIRepository.getInstance().searchById(this.almacenDonManolo.getIdentificador());
		
		Assert.assertNotNull(poiResult);
	}
	
	@Test
	public void testBuscarPOIPorIdConError() {
		POI poiResult = POIRepository.getInstance().searchById(this.poiConflictivoQueVaAHacerFallarLosTests.getIdentificador());
		
		Assert.assertNull(poiResult);
	}
	
	@Test
	public void testBuscarPOIConTextoConResultados() {
		List<POI> poisForSearch = POIRepository.getInstance().search("114");
		List<POI> poisForSearch2 = POIRepository.getInstance().search("Fiambres");
		
		Assert.assertEquals(1, poisForSearch.size());
		Assert.assertEquals(1, poisForSearch2.size());
	}
	
	@Test
	public void testBuscarPOIConTextoSinResultados() {
		List<POI> poisForSearch = POIRepository.getInstance().search("Banco");

		Assert.assertEquals(0, poisForSearch.size());
	}

	@Test
	public void testBuscaPOIConEtiquetas(){
		List<POI> listPOIResult = POIRepository.getInstance().search("Despensa");
		Assert.assertEquals(1, listPOIResult.size());
		Assert.assertEquals("Almacén Don Manolo", listPOIResult.get(0).getNombrePOI());

	}
	
	/*Fin Tests*/
	
}
