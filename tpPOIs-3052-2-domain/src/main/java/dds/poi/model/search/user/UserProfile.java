package dds.poi.model.search.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class UserProfile {

	@Id
	@GeneratedValue
	private Long id;

	@ElementCollection(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@CollectionTable(name="UserActions", joinColumns=@JoinColumn(name="id"))
	@JoinTable(name="UserActions", joinColumns=@JoinColumn(name="id"))
    @Cascade(value=org.hibernate.annotations.CascadeType.ALL)
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
