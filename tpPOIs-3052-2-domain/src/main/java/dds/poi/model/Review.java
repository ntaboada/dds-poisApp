package dds.poi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import dds.poi.model.search.user.User;

@Entity
public class Review {
    
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length=400)
	private String comment;
	
	@Column
    private int puntaje;
	
	@ManyToOne
    private User user;
	
	public Review(){}

    public Review(String comment, int puntaje, User user) {
        this.comment = comment;
        this.puntaje = puntaje;
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
