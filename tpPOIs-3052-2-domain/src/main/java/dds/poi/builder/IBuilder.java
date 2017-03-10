package dds.poi.builder;

public interface IBuilder<BuildObj> {

	public BuildObj build();
	
	public boolean isValidBuild();
}
