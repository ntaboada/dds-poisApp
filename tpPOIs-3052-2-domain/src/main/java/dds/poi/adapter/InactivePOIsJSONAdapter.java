package dds.poi.adapter;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import dds.poi.adapter.wrapper.JsonObjectWrapper;
import dds.poi.dto.model.InactivePOIDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicolas on 13/06/2016.
 */
public class InactivePOIsJSONAdapter implements Adapter<String,List<InactivePOIDTO>> {


    @Override
    public List<InactivePOIDTO> adapt(String arrayInactivePOIs) {
        List<InactivePOIDTO> inactivePOIDTOs = new ArrayList<>();

        if (arrayInactivePOIs != null) {
            JsonArray inactivePoiArray = JsonArray.readFrom(arrayInactivePOIs);

            for (int i = 0; i < inactivePoiArray.size(); i++) {
                JsonObject inactivePoi = (JsonObject) inactivePoiArray.get(i);
                if (inactivePoi != null) {
                    JsonObjectWrapper wrapper = new JsonObjectWrapper(inactivePoi);
                    String valorBusqueda = wrapper.getString("valorBusqueda");
                    String fechaBusqueda = wrapper.getString("fechaBusqueda");
                    InactivePOIDTO inactivePOIDTO = new InactivePOIDTO(valorBusqueda, fechaBusqueda);
                    inactivePOIDTOs.add(inactivePOIDTO);
                }
            }
            return inactivePOIDTOs;
        }
        return null;
    }
}