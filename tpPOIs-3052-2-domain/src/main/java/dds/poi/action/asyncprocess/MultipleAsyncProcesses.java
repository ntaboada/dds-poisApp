package dds.poi.action.asyncprocess;

import java.util.ArrayList;
import java.util.List;

import dds.poi.model.search.user.User;

public class MultipleAsyncProcesses extends AsyncProcess {

	private static final String PROCESS_NAME = "MULTIPLE_ASYNC_PROCESS";
	
	public MultipleAsyncProcesses(User user) {
		super(user);
	}

	private List<AsyncProcess> asyncProcess = new ArrayList<AsyncProcess>();
	
	public List<AsyncProcess> getAsyncProcess() {
		return asyncProcess;
	}

	public void addAsyncProcess(AsyncProcess asyncProcess) {
		this.asyncProcess.add(asyncProcess);
	}
	
	public void setAsyncProcess(List<AsyncProcess> asyncProcess) {
		this.asyncProcess = asyncProcess;
	}

	@Override
	protected void execProcess() throws Exception {
		this.asyncProcess.stream().forEach(process -> process.execute());
	}

	@Override
	protected String getProcessName() {
		return PROCESS_NAME;
	}
	
}
