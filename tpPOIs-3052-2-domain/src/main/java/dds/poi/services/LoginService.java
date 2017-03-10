package dds.poi.services;

import org.springframework.stereotype.Service;

import dds.poi.provider.repository.UserRepository;

@Service
public class LoginService {

    public boolean checkCredentials(String username, String password){
    	return UserRepository.getInstance().correctCredentials(username, password);
    }

	public Object getUserId(String username, String password) {
		Long userId = UserRepository.getInstance().getUserIdByUserNameAndPassword(username, password);
		return userId;
	}
}
