package dds.poi.builder;

import dds.poi.model.asyncprocess.NewActions;

public class NewActionsBuilder extends Builder<NewActions> {

	private NewActions newActions = new NewActions();
	
	public NewActionsBuilder action(String action) {
		this.newActions.addUserAction(action);
		return this;
	}
	
	@Override
	public boolean isValidBuild() {
		return this.newActions.getUserActions() != null;
	}

	@Override
	public NewActions returnBuildObject() {
		return this.newActions;
	}

}
