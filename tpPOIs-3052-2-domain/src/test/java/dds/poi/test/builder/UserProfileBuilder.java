package dds.poi.test.builder;

import dds.poi.builder.Builder;
import dds.poi.model.search.user.UserProfile;

public class UserProfileBuilder extends Builder<UserProfile>{

	private UserProfile userProfile = new UserProfile();
	
	public UserProfileBuilder action(String action) {
		this.userProfile.addAction(action);
		return this;
	}

	@Override
	public boolean isValidBuild() {
		return true;
	}

	@Override
	public UserProfile returnBuildObject() {
		return this.userProfile;
	}
	
}
