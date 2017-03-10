package dds.poi.provider;

import java.util.List;

import dds.poi.model.search.user.User;

public interface UserService {

	public User loginUser(String email, String password);

	public List<String> getAdminProfilesEmails();

}
