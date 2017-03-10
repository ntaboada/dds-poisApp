package dds.poi.services;

import dds.poi.dto.model.FiltersDTO;
import dds.poi.dto.model.SearchPOIDTO;
import dds.poi.manager.POIManager;
import dds.poi.model.POI;
import dds.poi.model.search.user.User;
import dds.poi.provider.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nicolas on 25/09/2016.
 */
@Service
public class SearchService {

    @Autowired
    FavoriteService favoriteService;

    public List<SearchPOIDTO> retrievePOIs(FiltersDTO filters, long userId){
        List<POI> resultPOIS = new ArrayList<>();
        User searchedUser = UserRepository.getInstance().searchById(userId);

        filters.getFiltersList().stream()
                            .forEach(filter->resultPOIS.addAll(POIManager.getInstance().buscarPOIs(filter)));    //Busco  todos los que coincidan con los filtros

        List<SearchPOIDTO> searchPOIDTOs  = resultPOIS.stream()
                                                      .map(SearchPOIDTO::new)
                                                      .collect(Collectors.toList());

        searchPOIDTOs.forEach(resultPOI -> {
            boolean isFavoritePOI = favoriteService.isFavoritePOI(searchedUser, resultPOI.getIdentificador());
            resultPOI.setFavoritePOI(isFavoritePOI);
            });

        return searchPOIDTOs;
    }
}
