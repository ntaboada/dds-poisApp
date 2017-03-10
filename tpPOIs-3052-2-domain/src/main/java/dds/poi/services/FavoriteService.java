package dds.poi.services;

import dds.poi.provider.repository.UserRepository;
import org.springframework.stereotype.Service;

import dds.poi.model.search.user.User;

@Service
public class FavoriteService {

    public void removeFavoritePOI(long idPOI, long idUser){
        User user = UserRepository.getInstance().searchById(idUser);
        user.removeFavoritePOI(idPOI);
        UserRepository.getInstance().update(user);
    }

    public void saveFavoritePOI(long idPOI, long idUser){
        User user = UserRepository.getInstance().searchById(idUser);
        user.addFavoritePOI(idPOI);
        UserRepository.getInstance().update(user);
    }

    public boolean isFavoritePOI(User user, Long id){
        return user.getFavoritesPOIs().contains(id);
    }
}
