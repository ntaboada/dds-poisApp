package dds.poi.provider.repository;

import dds.poi.model.POI;
import dds.poi.provider.Provider;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class POIRepository implements Repository<POI>, Provider<List<POI>>{

	private static POIRepository _selfInstance;
	
	private List<POI> poisList = new ArrayList<POI>();

	public void setPoisList(List<POI> poisList) {
		this.poisList = poisList;
	}

	public static POIRepository getInstance() {
		if(_selfInstance == null) {
			_selfInstance = new POIRepository();
		}
		return _selfInstance;
	}
	
	@Override
	public boolean create(POI obj) {
		this.poisList.add(obj);
		return true;
	}

	@Override
	public boolean delete(POI obj) {
		for(POI poi : poisList) {
			if(poi.getIdentificador() == obj.getIdentificador()) {
				int indexOfPOI = this.poisList.indexOf(poi);
				this.poisList.remove(indexOfPOI);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean update(POI obj) {
		for(POI poi : poisList) {
			if(poi.getIdentificador() == obj.getIdentificador()) {
				int indexOfPOI = this.poisList.indexOf(poi);
				this.poisList.remove(indexOfPOI);
				this.poisList.add(indexOfPOI, obj);
				return true;
			}
		}
		return false;
		
	}

	@Override
	public POI searchById(int id) {
		for(POI poi : poisList) {
			if(poi.getIdentificador() == id) {
				return poi;
			}
		}
		return null;
	}

	@Override
	public List<POI> search(String valor) {
		Predicate<POI> predicate = poi -> poi.coincideConBusqueda(valor);
		return poisList.stream().filter(predicate).collect(Collectors.toList());
	}

	@Override
	public List<POI> findAll() {
		return this.poisList;
	}

	@Override
	public List<POI> searchPOIs(String valor) {
		return this.search(valor);
	}

	@Override
	public boolean deleteAll() {
		this.poisList.clear();
		return true;
	}

}
