package dds.poi.action.search;

import java.util.ArrayList;
import java.util.List;

import dds.poi.action.TerminalAction;
import dds.poi.manager.POIManager;
import dds.poi.model.POI;
import dds.poi.observer.Observable;
import dds.poi.observer.Observer;

public class Search implements TerminalAction<List<POI>>, Observable {

	private List<Observer> observersActions = new ArrayList<>();

	private String query;
	private long responseTime;
	private int resultsSize;
	private String date;
	private int idTerminal;
	private Long idUsuario;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public long getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}

	public int getResultsSize() {
		return resultsSize;
	}

	public void setResultsSize(int resultsSize) {
		this.resultsSize = resultsSize;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getIdTerminal() {
		return idTerminal;
	}

	public void setIdTerminal(int idTerminal) {
		this.idTerminal = idTerminal;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public List<POI> execute() {
		long initTime = System.currentTimeMillis();
		List<POI> result = POIManager.getInstance().buscarPOIs(this.query);
		long endTime = System.currentTimeMillis();
		this.setResponseTime(endTime - initTime);
		this.setResultsSize(result.size());

		this.notifyAllObservers();

		return result;
	}

	@Override
	public void addObserver(Observer obs) {
		this.observersActions.add(obs);
	}

	@Override
	public void removeObserver(Observer obs) {
		this.observersActions.remove(obs);
	}

	@Override
	public void notifyAllObservers() {
		this.observersActions.forEach(observer -> observer.update(this));
	}
}
