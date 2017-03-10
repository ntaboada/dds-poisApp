package dds.poi.model;

import java.util.List;

import dds.poi.adapter.Adapter;
import dds.poi.provider.Provider;

@SuppressWarnings("rawtypes")
public class POISource {

	private Provider dataProvider;
	private Adapter adapter;

	@SuppressWarnings("unchecked")
	public List<POI> search(String valor) {
		if(this.adapter!=null) {
			return (List<POI>) this.adapter.adapt(this.dataProvider.searchPOIs(valor));
		}else{
			return (List<POI>) this.dataProvider.searchPOIs(valor);
		}
	}
	
	public Adapter getAdapter() {
		return adapter;
	}

	public void setAdapter(Adapter adapter) {
		this.adapter = adapter;
	}

	public Provider getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(Provider dataProvider) {
		this.dataProvider = dataProvider;
	}

}
