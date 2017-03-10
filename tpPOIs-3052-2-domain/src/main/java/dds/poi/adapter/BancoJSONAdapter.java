package dds.poi.adapter;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

import dds.poi.adapter.wrapper.JsonObjectWrapper;
import dds.poi.builder.BancoBuilder;
import dds.poi.builder.POIBuilder;
import dds.poi.model.Banco;
import dds.poi.model.POI;
import dds.poi.model.Servicio;
import dds.poi.model.ServicioBanco;

public class BancoJSONAdapter implements Adapter<String, List<POI>> {

	@Override
	public List<POI> adapt(String jsonBancosArray) {
		if (jsonBancosArray != null) {
			List<POI> bancos = new ArrayList<POI>();
			JsonArray bancoArray = JsonArray.readFrom(jsonBancosArray);

			for (int i = 0; i < bancoArray.size(); i++) {
				JsonObject bancoObject = (JsonObject) bancoArray.get(i);
				if (bancoObject != null) {
					JsonObjectWrapper decorator = new JsonObjectWrapper(
							bancoObject);

					double latitude, longitude;
					latitude = decorator.getDouble("x");
					longitude = decorator.getDouble("y");
					Point coordenadas = new Point(latitude, longitude);
					String nombre = decorator.getString("banco");

					String gerente = decorator.getString("gerente");
					String sucursal = decorator.getString("sucursal");

					List<Servicio> serviciosBanco = new ArrayList<Servicio>();
					JsonArray serviciosArray = decorator.getJsonArray("servicios");
					if (serviciosArray != null && serviciosArray.size() > 0) {
						for (int j = 0; j < serviciosArray.size(); j++) {
							String nombreServicio = serviciosArray.get(j).asString();
							ServicioBanco banco = new ServicioBanco();
							banco.setNombreServicio(nombreServicio);
							serviciosBanco.add(banco);
						}
					}
					
					Banco categoriaBanco = new BancoBuilder().gerente(gerente).sucursal(sucursal).servicios(serviciosBanco).build();
					POI nuevoBanco = new POIBuilder().nombre(nombre).coordenadas(coordenadas).banco(categoriaBanco).build();	
					bancos.add(nuevoBanco);
				}
			}
			return bancos;
		} else {
			return null;
		}
	}

}
