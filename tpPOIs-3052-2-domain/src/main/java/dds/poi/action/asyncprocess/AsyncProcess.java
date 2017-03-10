package dds.poi.action.asyncprocess;

import dds.poi.action.TerminalAction;
import dds.poi.builder.AsyncProcessResultBuilder;
import dds.poi.config.TerminalConfig;
import dds.poi.history.AsyncProcessResultHistory;
import dds.poi.model.search.user.User;
import dds.poi.servicelocator.ServiceLocator;

public abstract class AsyncProcess implements TerminalAction<Void> {

	private User user;

	public AsyncProcess(User user) {
		this.setUser(user);
	}

	@Override
	public Void execute() {
		try {
			this.normalExecute();
		} catch (Exception e) {
			this.handleErrorResult(e.getMessage());
		}
		return null;
	}

	private void normalExecute() throws Exception {
		long initTime = System.currentTimeMillis();
		this.execProcess();
		long endTime = System.currentTimeMillis();
		this.handleFinishedExecution(initTime, endTime, true, null);
	}

	private void handleErrorResult(String message) {
		boolean successExec = false;
		long initTime = 0, endTime = 0;
		for (int i = 0; i < TerminalConfig.getInstance().getRetryCount(); i++) {
			try {
				initTime = System.currentTimeMillis();
				this.normalExecute();
				return;
			} catch (Exception e) {
				message = e.getMessage();
				endTime = System.currentTimeMillis();
			}
		}

		if (!successExec) {
			this.handleFinishedExecution(initTime, endTime, false, message);
			this.handleSendMailOnFailure();
		}
	}

	private void handleFinishedExecution(long initTime, long endTime, boolean successExecution, String message) {
		AsyncProcessResultBuilder builder = new AsyncProcessResultBuilder()
			.initTime(initTime).endTime(endTime).success(successExecution).userId(this.getUser().getIdUsuario())
			.process(this.getProcessName());
		
		if(message != null) {
			builder.errorMessage(message);
		}
		
		AsyncProcessResultHistory.getInstance().addResult(builder.build());
	}
	
	private void handleSendMailOnFailure() {
		if(TerminalConfig.getInstance().getSendMailOnFailure()) {
			ServiceLocator.getInstance().sendProcessFailMail(this.getUser().getEmail(), this.getProcessName());
		}
	}

	protected abstract void execProcess() throws Exception;

	protected abstract String getProcessName();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
