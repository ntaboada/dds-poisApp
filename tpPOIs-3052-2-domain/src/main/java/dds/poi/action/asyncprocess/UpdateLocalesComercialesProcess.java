package dds.poi.action.asyncprocess;

import java.util.List;
import java.util.stream.Collectors;

import dds.poi.manager.POIManager;
import dds.poi.model.Etiqueta;
import dds.poi.model.POI;
import dds.poi.model.UpdateLocalComercial;
import dds.poi.model.search.user.User;
import dds.poi.provider.repository.POIRepository;
import dds.poi.servicelocator.ServiceLocator;

public class UpdateLocalesComercialesProcess extends AsyncProcess {

	private static final String PROCESS_NAME = "UPDATE_LOCALES_COMERCIALES_PROCESS";
	
	public UpdateLocalesComercialesProcess(User user) {
		super(user);
	}

	@Override
	protected void execProcess() throws Exception{
		UpdateLocalComercial localComercialToUpdate = ServiceLocator.getInstance().getWeekFileUpdateService().localComercialesToUpdate();
		POIManager poiManager = POIManager.getInstance();
		POI poiBuscado = poiManager.buscarPOIs(localComercialToUpdate.getNombreFantasia()).get(0);

		if (poiBuscado!=null){
			poiBuscado.addAllEtiquetas(localComercialToUpdate.getEtiquetasAUpdatear());
			List<Etiqueta> etiquetasNoRepetidas = poiBuscado.getEtiquetas().stream().distinct().collect(Collectors.toList());
			poiBuscado.setEtiquetas(etiquetasNoRepetidas);
			POIRepository.getInstance().update(poiBuscado);
		}
	}

	@Override
	protected String getProcessName() {
		return PROCESS_NAME;
	}

}
