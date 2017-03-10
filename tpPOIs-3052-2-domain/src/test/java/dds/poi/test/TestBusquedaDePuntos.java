package dds.poi.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import dds.poi.manager.POIManager;
import dds.poi.model.POI;


public class TestBusquedaDePuntos extends Inicializar{
	
	@Test
	public void testParadaDeColectivoBuscaYEncuentraPorLineaDeColectivo() {
		boolean coincideConBusqueda = this.parada114.coincideConBusqueda("114");
		Assert.assertTrue(coincideConBusqueda);
	}

	@Test
	public void testParadaDeColectivoBuscaYEncuentraPorEtiquetas(){
		boolean coincideConBusqueda = this.parada114.coincideConBusqueda("Colectivo");
		Assert.assertTrue(coincideConBusqueda);
	}
	

	@Test
	public void testLocalComercialBuscaYEncuentraPorRubro() {
		boolean coincideConBusqueda = this.almacenDonManolo.coincideConBusqueda("Almacen");
		Assert.assertTrue(coincideConBusqueda);
	}

	@Test
	public void testLocalComercialBuscaYEncuentraPorEtiquetas(){
		boolean coincideConBusqueda = this.almacenDonManolo.coincideConBusqueda("Don Manolo");
		Assert.assertTrue(coincideConBusqueda);
	}

	@Test
	public void testLocalComercialBuscaYNoEncuentra(){
		boolean coincideConBusqueda = this.almacenDonManolo.coincideConBusqueda("No hay ni rubro ni etiquetas que coincidan");
		Assert.assertFalse(coincideConBusqueda);
	}
	
	
	/*Tests con STUBS y POIManager*/
	@Test
	public void testBuscaParadaYEncuentraConPOIManager() {
		List<POI> paradas = POIManager.getInstance().buscarPOIs("114");
		
		Assert.assertEquals(1, paradas.size());
	}

	@Test
	public void testBuscaParadaYNoEncuentraConPOIManager() {
		List<POI> paradas = POIManager.getInstance().buscarPOIs("180");
		
		Assert.assertEquals(0, paradas.size());
	}
	
	@Test
	public void testBuscaLocalComercialYEncuentraConPOIManager() {
		List<POI> localesComerciales = POIManager.getInstance().buscarPOIs("Don Manolo");
		
		Assert.assertEquals(1, localesComerciales.size());
	}
	
	@Test
	public void testBuscaLocalComercialYNoEncuentraConPOIManager() {
		List<POI> localesComerciales = POIManager.getInstance().buscarPOIs("Don Pepito");
		
		Assert.assertEquals(0, localesComerciales.size());
	}
	
	@Test
	public void testBuscaBancosYEncuentraConPOIManager() {
		List<POI> bancos = POIManager.getInstance().buscarPOIs("BancoDeLaPlaza");
		
		Assert.assertEquals(2, bancos.size());
	}
	
	@Test
	public void testBuscaBancosYNoEncuentraConPOIManager() {
		List<POI> bancos = POIManager.getInstance().buscarPOIs("Nombre de Banco no registrado en objetos JSON");
		
		Assert.assertEquals(0, bancos.size());
	}
	
	@Test
	public void testBuscasCGPsYEncuentraConPOIManager() {
		List<POI> cgps = POIManager.getInstance().buscarPOIs("Zona 1");
		
		Assert.assertEquals(1, cgps.size());
	}

	@Test
	public void testBuscaCGPsYNoEncuentraConPOIManager() {
		List<POI> cgps = POIManager.getInstance().buscarPOIs("Zona o Calle no registrada por ningun CentroDTO");
		
		Assert.assertEquals(0, cgps.size());
	}
	/*Fin Tests con STUBS y POIManager*/
	
}
