package dds.poi.test;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;


public class TestServicioDisponibilidad extends Inicializar{

   
    //Tests ParadaColectivo
    @Test
    public void testDisponibilidadPOIParadaColectivo(){
        boolean estaDisponible = parada114.estaDisponible(new DateTime());
        Assert.assertTrue(estaDisponible);
    }
    //Fin Tests ParadaColectivo
    
    //Tests Locales Comerciales
    @Test
    public void testLocalComercialDisponible() {
    	DateTime dateTime = new DateTime(2016, 5, 9, 11, 30);
    	boolean estaDisponible = almacenDonManolo.estaDisponible(dateTime);
    	Assert.assertTrue(estaDisponible);
    }
    
    @Test
    public void testLocalComercialNoDisponible() {
    	DateTime dateTime = new DateTime(2016, 5, 13, 15, 0);
    	boolean estaDisponible = almacenDonManolo.estaDisponible(dateTime);
    	Assert.assertFalse(estaDisponible);
    	
    }
    //Fin Tests Locales Comerciales

}
