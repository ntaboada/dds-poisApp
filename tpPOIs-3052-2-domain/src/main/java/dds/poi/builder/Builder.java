package dds.poi.builder;

import dds.poi.exception.BuilderException;

public abstract class Builder<ReturnObj> implements IBuilder<ReturnObj> {
	
	@Override
	public ReturnObj build() {
		if(this.isValidBuild()) {
			return this.returnBuildObject();
		}else{
			throw new BuilderException("Build error");
		}
	}
	
	public abstract ReturnObj returnBuildObject();
	
}
