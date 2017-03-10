package dds.poi.model.asyncprocess;

import java.util.ArrayList;
import java.util.List;

public class NewActions implements Cloneable {

	private List<String> userActions = new ArrayList<String>();
	private boolean activate;
	
	public List<String> getUserActions() {
		return userActions;
	}
	public void setUserAction(List<String> userActions) {
		this.userActions = userActions;
	}
	public boolean isActivate() {
		return activate;
	}
	public void setActivate(boolean activate) {
		this.activate = activate;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public void addUserAction(String action) {
		this.userActions.add(action);
	}
	
}
