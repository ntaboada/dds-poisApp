package dds.poi.dto.model;

public class POIByNameRequestDTO {

	private String poiName;
	private Long idLoggedUser;
	
	public String getPoiName() {
		return poiName;
	}
	public void setPoiName(String poiName) {
		this.poiName = poiName;
	}
	public Long getIdLoggedUser() {
		return idLoggedUser;
	}
	public void setIdLoggedUser(Long idLoggedUser) {
		this.idLoggedUser = idLoggedUser;
	}
	
}
