package dds.poi.model.search.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserProfile {

	@Id
	@GeneratedValue
	private Long id;

	@JsonIgnore
	@ElementCollection
	@CollectionTable(name="UserActions", joinColumns=@JoinColumn(name="id"))
	private List<String> actions = new ArrayList<String>();

	@JsonIgnore
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
		if (this.actions.stream().anyMatch(
				actionEnabled -> actionEnabled
						.equals(UserActions.ADMIN_ACTIONS))) {
			return true;
		} else {
			return this.actions.stream().anyMatch(
					actionEnabled -> actionEnabled.equals(action));
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
