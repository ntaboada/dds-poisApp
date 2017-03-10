package dds.poi.test;
import org.junit.Assert;
import org.junit.Test;

public class TestPuntosDeInteres extends Inicializar{

	//Tests ParadaColectivo
	@Test
	public void testParadaDeColectivoCercana() {

		boolean estaCerca = this.parada114.estaCerca(this.coordenadaCercanaColectivo);
		Assert.assertTrue(estaCerca);
	}

	@Test
	public void testParadaDeColectivoNoCercana(){
		boolean estaCerca = this.parada114.estaCerca(this.coordenadaNoCercanaColectivo);
		Assert.assertFalse(estaCerca);
	}

	
	@Test
	public void testParadaDeColectivoBuscaYNoEncuentra(){
		boolean coincideConBusqueda = this.parada114.coincideConBusqueda("No existe esta etiqueta ni es la línea de colectivo");
		Assert.assertFalse(coincideConBusqueda);
	}
	//Fin Tests ParadaColectivo

	//Tests LocalComercial
	@Test
	public void testLocalComercialCercano() {
		boolean estaCerca = this.almacenDonManolo.estaCerca(this.coordenadaCercanaLocalComercial);
		Assert.assertTrue(estaCerca);
	}

	@Test
	public void testLocalComercialNoCercano(){
		boolean estaCerca = this.almacenDonManolo.estaCerca(this.coordenadaNoCercanaLocalComercial);
		Assert.assertFalse(estaCerca);
	}

	//Fin Tests LocalComercial
}