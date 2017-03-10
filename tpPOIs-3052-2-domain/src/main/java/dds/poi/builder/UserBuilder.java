package dds.poi.builder;

import dds.poi.exception.BuilderException;
import dds.poi.model.search.user.User;
import dds.poi.model.search.user.UserProfile;

public class UserBuilder implements IBuilder<User> {

	private User user = new User();

	public UserBuilder id(Long userId) {
		this.user.setIdUsuario(userId);
		return this;
	}
	
	public UserBuilder userName(String userName) {
		this.user.setUserName(userName);
		return this;
	}
	
	public UserBuilder mail(String email) {
		this.user.setEmail(email);
		return this;
	}
	
	public UserBuilder password(String password) {
		this.user.setPassword(password);
		return this;
	}

	public UserBuilder profileType(UserProfile type) {
		this.user.setProfileType(type);
		return this;
	}

	@Override
	public User build() {
		if(!this.isValidBuild()) {
			throw new BuilderException("Error al Builder el Usuario");
		}
		return this.user;
	}

	@Override
	public boolean isValidBuild() {
		return this.user.getEmail()!=null && this.user.getPassword()!=null && this.user.getIdUsuario()>0 && this.user.getProfileType()!=null;
	}
	
}
