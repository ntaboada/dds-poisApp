package dds.poi.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import dds.poi.model.asyncprocess.NewActions;
import dds.poi.model.search.user.User;

public class UserManager {

	private List<User> users = new ArrayList<User>();

	private static UserManager _selfInstance;

	public static UserManager getInstance() {
		if (_selfInstance == null) {
			_selfInstance = new UserManager();
		}
		return _selfInstance;
	}
	
	public void setUsersNull() {
		this.users = null;
	}
	
	public void addUser(User user) {
		this.users.add(user);
	}

	public void changeUserActions(NewActions actions) throws RuntimeException {
		if (actions.isActivate()) {
			this.addActionsToUsers(actions.getUserActions());
		} else {
			this.removeActionsToUsers(actions.getUserActions());
		}
	}

	public List<String> getAllUserActions() throws RuntimeException {
		List<String> userActions = new ArrayList<String>();
		this.users.stream().forEach(
				user -> {
					user.getUserActions().stream().forEach(
						userAction -> {
							if(!userActions.contains(userAction)) {
								userActions.add(userAction);
							}
						}
					);
				});
		return userActions;
	}
	
	private void removeActionsToUsers(List<String> userActions) throws RuntimeException {
		this.users.stream().forEach(
				user -> {
					userActions.stream().forEach(
							userAction -> user.addUserAction(userAction));
				});
	}

	private void addActionsToUsers(List<String> userActions) {
		this.users.stream().forEach(
				user -> {
					userActions.stream().forEach(
							userAction -> user.removeUserAction(userAction));
				});
	}

	public User loginUser(String email, String password) {
		Predicate<User> predicate = user -> user.getEmail().equalsIgnoreCase(
				email)
				&& user.getPassword().equals(password);
		if (this.users.stream().anyMatch(predicate)) {
			return this.users.stream().filter(predicate)
					.collect(Collectors.toList()).get(0);
		} else {
			return null;
		}
	}

	public List<String> getAdminProfilesEmails() {
		Predicate<User> filterPredicate = user -> user.isAdministrator();

		return this.users.stream().filter(filterPredicate)
				.map(user -> user.getEmail()).collect(Collectors.toList());
	}

}
