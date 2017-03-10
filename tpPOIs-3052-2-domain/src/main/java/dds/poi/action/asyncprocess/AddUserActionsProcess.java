package dds.poi.action.asyncprocess;

import dds.poi.manager.UserManager;
import dds.poi.model.asyncprocess.NewActions;
import dds.poi.model.search.user.User;

public class AddUserActionsProcess extends AsyncProcess {

	private static final String PROCESS_NAME = "ADD_USER_ACTIONS_PROCESS";
	
	private NewActions actionsBefore;
	private NewActions newActions;

	public AddUserActionsProcess(User user) {
		super(user);
	}
	
	@Override
	protected void execProcess() throws Exception {
		UserManager.getInstance().changeUserActions(newActions);
	}
	
	public void undo() {
		UserManager.getInstance().changeUserActions(actionsBefore);
	}

	public NewActions getNewActions() {
		return newActions;
	}

	public void setNewActions(NewActions newActions) {
		this.newActions = newActions;
		
		try {
			this.actionsBefore = (NewActions) this.newActions.clone();
			this.actionsBefore.setActivate(!this.newActions.isActivate());
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected String getProcessName() {
		return PROCESS_NAME;
	}
	
}
