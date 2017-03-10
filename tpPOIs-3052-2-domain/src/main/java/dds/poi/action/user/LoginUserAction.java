package dds.poi.action.user;

import dds.poi.action.TerminalAction;
import dds.poi.exception.InvalidUserException;
import dds.poi.manager.UserManager;
import dds.poi.model.search.user.User;

public class LoginUserAction implements TerminalAction<User> {

	private String email;
	private String password;

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

	@Override
	public User execute() {
		User user = UserManager.getInstance().loginUser(this.email, this.password);
		if (user == null) {
			throw new InvalidUserException();
		} else {
			return user;
		}
	}

}
