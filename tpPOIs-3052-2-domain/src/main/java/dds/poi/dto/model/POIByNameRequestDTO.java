package dds.poi.dto.model;

public class POIByNameRequestDTO {

	private String poiName;
	private int idLoggedUser;
	
	public String getPoiName() {
		return poiName;
	}
	public void setPoiName(String poiName) {
		this.poiName = poiName;
	}
	public int getIdLoggedUser() {
		return idLoggedUser;
	}
	public void setIdLoggedUser(int idLoggedUser) {
		this.idLoggedUser = idLoggedUser;
	}
	
}
