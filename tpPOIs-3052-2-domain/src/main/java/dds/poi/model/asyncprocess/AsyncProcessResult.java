package dds.poi.model.asyncprocess;

import org.joda.time.DateTime;

public class AsyncProcessResult {

	private DateTime initTime;
	private DateTime endTime;
	private String process;
	private Long userId;
	private boolean success;
	private String errorMessage;
	
	public DateTime getInitTime() {
		return initTime;
	}
	public void setInitTime(DateTime initTime) {
		this.initTime = initTime;
	}
	public DateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(DateTime endTime) {
		this.endTime = endTime;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
