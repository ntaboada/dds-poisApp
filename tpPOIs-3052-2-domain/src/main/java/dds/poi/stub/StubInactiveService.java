package dds.poi.stub;

import dds.poi.servicelocator.service.InactivePOIsService;

/**
 * Created by Nicolas on 13/06/2016.
 */
public class StubInactiveService implements InactivePOIsService{

    private String jsonInactivePOIs;

    public StubInactiveService(String inactivePois) {
        this.jsonInactivePOIs = inactivePois;
    }

    @Override
    public String obtainInactivePOIs() {
        return jsonInactivePOIs ;
    }
}
