package dds.poi.builder.action;

import dds.poi.action.user.LoginUserAction;
import dds.poi.builder.Builder;

public class LoginUserActionBuilder extends Builder<LoginUserAction> {

	private LoginUserAction loginUserAction = new LoginUserAction();

	public LoginUserActionBuilder email(String email) {
		this.loginUserAction.setEmail(email);
		return this;
	}

	public LoginUserActionBuilder password(String password) {
		this.loginUserAction.setPassword(password);
		return this;
	}

	@Override
	public boolean isValidBuild() {
		return this.loginUserAction.getEmail() != null
				&& this.loginUserAction.getPassword() != null;
	}

	@Override
	public LoginUserAction returnBuildObject() {
		return this.loginUserAction;
	}

}
