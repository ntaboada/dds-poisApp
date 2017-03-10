package dds.poi.model.search.user;

import java.util.ArrayList;
import java.util.List;

public class UserProfile {

	private List<String> actions = new ArrayList<String>();

	public List<String> getActions() {
		return actions;
	}

	public void setActions(List<String> actions) {
		this.actions = actions;
	}

	public void addAction(String action) {
		this.actions.add(action);
	}

	public void removeAction(String action) {
		this.actions.remove(action);
	}

	public boolean canExecute(String action) {
		if (this.actions.stream().anyMatch(actionEnabled -> actionEnabled.equals(UserActions.ADMIN_ACTIONS))) {
			return true;
		} else {
			return this.actions.stream().anyMatch(actionEnabled -> actionEnabled.equals(action));
		}
	}

}
