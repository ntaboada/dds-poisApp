package dds.poi.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import dds.poi.model.POI;
import dds.poi.model.POISource;
import dds.poi.provider.repository.POIRepository;

public class POIManager {

	private List<POISource> poiSources = new ArrayList<POISource>();

	private static POIManager _selfInstance;

	public static POIManager getInstance() {
		if (_selfInstance == null) {
			_selfInstance = new POIManager();
		}
		return _selfInstance;
	}
	
	

	public List<POI> buscarPOIs(String valor) {
		List<POI> pois = new ArrayList<POI>();

		Consumer<POISource> consumer = poiSource -> {
			List<POI> poiResults = poiSource.search(valor);
			if (poiResults != null) {
				pois.addAll(poiResults);
			}
		};

		this.poiSources.stream().forEach(consumer);

		return pois;
	}

	public void addPOISource(POISource poiSource) {
		this.poiSources.add(poiSource);
	}

	public void cleanProviders() {
		this.poiSources.clear();
	}



	public POI searchById(int idPOI) {
		return POIRepository.getInstance().searchById(idPOI);
	}

}
