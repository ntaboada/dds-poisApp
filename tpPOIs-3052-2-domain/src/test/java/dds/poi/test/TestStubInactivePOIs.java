package dds.poi.test;

import dds.poi.stub.StubInactiveService;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by nicolas.taboada on 14/06/2016.
 */
public class TestStubInactivePOIs extends Inicializar {

    @Test
    public void testGETDePOIsInactivosTraeResultados(){
        this.inactiveService = new StubInactiveService("NoImportaElValor");
        String inactiveJsonArrayPois = this.inactiveService.obtainInactivePOIs();
        Assert.assertNotNull(inactiveJsonArrayPois);
    }

}
