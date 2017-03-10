package dds.poi.builder;

import dds.poi.adapter.Adapter;
import dds.poi.model.POISource;
import dds.poi.provider.Provider;

@SuppressWarnings("rawtypes")
public class POISourceBuilder extends Builder<POISource>{

	private POISource poiSource = new POISource();
	
	public POISourceBuilder adapter(Adapter adapter) {
		this.poiSource.setAdapter(adapter);
		return this;
	}
	
	public POISourceBuilder provider(Provider provider) {
		this.poiSource.setDataProvider(provider);
		return this;
	}
	
	@Override
	public boolean isValidBuild() {
		return this.poiSource.getAdapter()!=null && this.poiSource.getDataProvider()!=null;
	}

	@Override
	public POISource returnBuildObject() {
		return this.poiSource;
	}

}
