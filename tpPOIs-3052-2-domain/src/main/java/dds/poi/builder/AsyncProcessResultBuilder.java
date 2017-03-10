package dds.poi.builder;

import org.joda.time.DateTime;

import dds.poi.model.asyncprocess.AsyncProcessResult;

public class AsyncProcessResultBuilder extends Builder<AsyncProcessResult>{

	private AsyncProcessResult asyncProcessResult = new AsyncProcessResult();
	
	public AsyncProcessResultBuilder success(boolean success) {
		this.asyncProcessResult.setSuccess(success);
		return this;
	}
	
	public AsyncProcessResultBuilder errorMessage(String errorMessage) {
		this.asyncProcessResult.setErrorMessage(errorMessage);
		return this;
	}
	
	public AsyncProcessResultBuilder userId(Long userId) {
		this.asyncProcessResult.setUserId(userId);
		return this;
	}
	
	public AsyncProcessResultBuilder process(String process) {
		this.asyncProcessResult.setProcess(process);
		return this;
	}
	
	public AsyncProcessResultBuilder endTime(long endTime) {
		DateTime dateTime = new DateTime(endTime);
		this.asyncProcessResult.setEndTime(dateTime);
		return this;
	}
	
	public AsyncProcessResultBuilder initTime(long initTime) {
		DateTime dateTime = new DateTime(initTime);
		this.asyncProcessResult.setInitTime(dateTime);
		return this;
	}
	
	@Override
	public boolean isValidBuild() {
		return this.asyncProcessResult.getEndTime() != null && this.asyncProcessResult.getInitTime() != null
				&& this.asyncProcessResult.getProcess() != null && this.asyncProcessResult.getUserId() > 0;
	}

	@Override
	public AsyncProcessResult returnBuildObject() {
		return this.asyncProcessResult;
	}

}
