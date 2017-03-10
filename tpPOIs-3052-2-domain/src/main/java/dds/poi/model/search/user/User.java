package dds.poi.model.search.user;

import java.util.List;

import dds.poi.exception.PermissionException;

public class User {

	private int idUsuario;
	private String email;
	private String password;
	private UserProfile profileType;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserProfile getProfileType() {
		return profileType;
	}

	public void setProfileType(UserProfile profileType) {
		this.profileType = profileType;
	}
	
	public boolean canExecute(String action) {
		if(!this.profileType.canExecute(action)) {
			throw new PermissionException();
		}
		return true;
	}

	public boolean isAdministrator() {
		return this.profileType.canExecute(UserActions.ADMIN_ACTIONS);
	}

	public List<String> getUserActions() {
		return this.profileType.getActions();
	}
	
	public void addUserAction(String userAction) {
		this.profileType.addAction(userAction);
	}

	public void removeUserAction(String userAction) {
		this.profileType.removeAction(userAction);
	}
	
}
