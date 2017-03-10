package dds.poi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dds.poi.dto.model.POIDetailsDTO;
import dds.poi.dto.model.ReviewDTO;
import dds.poi.dto.model.ReviewForPOIByNameRequestDTO;
import dds.poi.manager.POIManager;
import dds.poi.model.POI;
import dds.poi.model.Review;
import dds.poi.model.search.user.User;
import dds.poi.provider.repository.UserRepository;

@Service
public class POIService {

    @Autowired
    private FavoriteService favoriteService;

    public POIDetailsDTO getPOIDetails(int idPOI, int idLoggedUser){
        POI searchedPoi = POIManager.getInstance().searchById(idPOI);
        User searchedUser = UserRepository.getInstance().searchById(idLoggedUser);

        boolean isFavoritePOI = favoriteService.isFavoritePOI(searchedUser, searchedPoi.getIdentificador());
        POIDetailsDTO poiDetailsDTO = new POIDetailsDTO(searchedPoi, isFavoritePOI);

        return poiDetailsDTO ;
    }

    public void saveReview(ReviewDTO reviewDTO, int idPOI){
        POI searchedPoi = POIManager.getInstance().searchById(idPOI);
        User searchedUser = UserRepository.getInstance().searchById(reviewDTO.getIdUser());

        Review reviewPOI = new Review(reviewDTO.getComment(), reviewDTO.getPuntaje(), searchedUser);
        searchedPoi.getReviewsList().add(reviewPOI);
    }

	public Object getPOIDetails(String poiName, int idLoggedUser) {
        POI searchedPoi = POIManager.getInstance().buscarPOIs(poiName).get(0);
        User searchedUser = UserRepository.getInstance().searchById(idLoggedUser);

        boolean isFavoritePOI = searchedPoi.getIdentificador() != 0 ? favoriteService.isFavoritePOI(searchedUser, searchedPoi.getIdentificador()) : false;
        POIDetailsDTO poiDetailsDTO = new POIDetailsDTO(searchedPoi, isFavoritePOI);

        return poiDetailsDTO ;
	}

	public void saveReview(ReviewForPOIByNameRequestDTO request) {
        POI searchedPoi = POIManager.getInstance().buscarPOIs(request.getPoiName()).get(0);
        User searchedUser = UserRepository.getInstance().searchById(request.getIdUser());

        Review reviewPOI = new Review(request.getComment(), request.getPuntaje(), searchedUser);
        searchedPoi.getReviewsList().add(reviewPOI);
	}
}
