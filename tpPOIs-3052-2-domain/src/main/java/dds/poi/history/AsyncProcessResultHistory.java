package dds.poi.history;

import java.util.ArrayList;
import java.util.List;

import dds.poi.model.asyncprocess.AsyncProcessResult;

public class AsyncProcessResultHistory {

	private static AsyncProcessResultHistory _selfInstance;
	
	private List<AsyncProcessResult> results = new ArrayList<AsyncProcessResult>();
	
	public static AsyncProcessResultHistory getInstance() {
		if(_selfInstance==null) {
			_selfInstance = new AsyncProcessResultHistory();
		}
		return _selfInstance;
	}

	public void addResult(AsyncProcessResult result) {
		this.results.add(result);
	}
	
	public AsyncProcessResult getLastResult() {
		return this.results.get(this.results.size()-1);
	}
	
	public int getQuantityOfResults() {
		return this.results.size();
	}
	
}
