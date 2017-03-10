package dds.poi.dto.model;

public class ReviewForPOIByNameRequestDTO {
	
	private Long idUser;
	private String poiName;
	private String comment;
	private int puntaje;
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long userId) {
		this.idUser = userId;
	}
	public String getPoiName() {
		return poiName;
	}
	public void setPoiName(String poiName) {
		this.poiName = poiName;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
