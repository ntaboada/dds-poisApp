package dds.poi.model.search.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import dds.poi.exception.PermissionException;

@Entity
@JsonIgnoreProperties({ "email", "password", "profileType", "favoritesPOIs" })
public class User {

	@Id
	@GeneratedValue
	private Long idUsuario;

	@Column(length = 30)
	private String userName;

	@Column(length = 100)
	private String email;

	@Column(length = 100)
	private String password;

	@ManyToOne(cascade = CascadeType.ALL)
	private UserProfile profileType;

	@ElementCollection
	@CollectionTable(name="UserFavoritePOIs", joinColumns=@JoinColumn(name="idUsuario"))
	private List<Long> favoritesPOIs = new ArrayList<>();

	public List<Long> getFavoritesPOIs() {
		return favoritesPOIs;
	}

	public void setFavoritesPOIs(List<Long> favoritesPOIs) {
		this.favoritesPOIs = favoritesPOIs;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

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

	public UserProfile getProfileType() {
		return profileType;
	}

	public void setProfileType(UserProfile profileType) {
		this.profileType = profileType;
	}

	public boolean canExecute(String action) {
		if (!this.profileType.canExecute(action)) {
			throw new PermissionException();
		}
		return true;
	}

	public boolean isAdministrator() {
		return this.profileType.canExecute(UserActions.ADMIN_ACTIONS);
	}

	public List<String> getUserActions() {
		return this.profileType.getActions();
	}

	public void addUserAction(String userAction) {
		this.profileType.addAction(userAction);
	}

	public void removeUserAction(String userAction) {
		this.profileType.removeAction(userAction);
	}

	public void addFavoritePOI(Long idPOI) {
		this.favoritesPOIs.add(idPOI);
	}

	public void removeFavoritePOI(Long idPOI) {
		this.favoritesPOIs.remove(idPOI);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
