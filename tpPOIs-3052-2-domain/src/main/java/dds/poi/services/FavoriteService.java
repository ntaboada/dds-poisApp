package dds.poi.services;

import org.springframework.stereotype.Service;

import dds.poi.model.search.user.User;
import dds.poi.provider.repository.UserRepository;

@Service
public class FavoriteService {

    public void removeFavoritePOI(int idPOI, int idUser){
        UserRepository.getInstance().searchById(idUser).removeFavoritePOI((long)idPOI);
    }

    public void saveFavoritePOI(int idPOI, int idUser){
    	UserRepository.getInstance().searchById(idUser).addFavoritePOI((long)idPOI);
    }

    public boolean isFavoritePOI(User user, Long id){
        return user.getFavoritesPOIs().contains(id);
    }
}
