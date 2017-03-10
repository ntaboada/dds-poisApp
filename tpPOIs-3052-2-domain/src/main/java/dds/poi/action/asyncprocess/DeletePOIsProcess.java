package dds.poi.action.asyncprocess;

import java.util.ArrayList;
import java.util.List;

import dds.poi.adapter.InactivePOIsJSONAdapter;
import dds.poi.dto.model.InactivePOIDTO;
import dds.poi.manager.POIManager;
import dds.poi.model.POI;
import dds.poi.model.search.user.User;
import dds.poi.provider.repository.POIRepository;
import dds.poi.servicelocator.ServiceLocator;

public class DeletePOIsProcess extends AsyncProcess {

	private static final String PROCESS_NAME = "DELETE_POIS_PROCESS";


	public DeletePOIsProcess(User user) {
		super(user);
	}

	@Override
	protected void execProcess() throws Exception{
		String jSonInactivePOIs = ServiceLocator.getInstance().getInactivePOIs();
		List<InactivePOIDTO> inactivePOIDTOList = new ArrayList<>();
		List<POI> poiResults = new ArrayList<>();
		POIManager poiManager = POIManager.getInstance();

		InactivePOIsJSONAdapter adapter = new InactivePOIsJSONAdapter();
		inactivePOIDTOList = (List<InactivePOIDTO>) adapter.adapt(jSonInactivePOIs);

		for (InactivePOIDTO inactivePOI : inactivePOIDTOList   ) {
			String valorBusqueda = inactivePOI .getValorBusqueda();
			poiResults = poiManager.buscarPOIs(valorBusqueda);
			if(poiResults.size()!=0){
				poiResults.forEach(poiResult -> POIRepository.getInstance().delete(poiResult)); //Baja Fisica
			}
		}

	}

	@Override
	protected String getProcessName() {
		return PROCESS_NAME;
	}

	
}
