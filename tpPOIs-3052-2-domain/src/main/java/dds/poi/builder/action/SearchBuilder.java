package dds.poi.builder.action;

import org.joda.time.DateTime;

import dds.poi.action.search.Search;
import dds.poi.builder.Builder;
import dds.poi.observer.NotifyAdminObserver;
import dds.poi.observer.StoreResultsObserver;
import dds.poi.utils.DateUtils;

public class SearchBuilder extends Builder<Search> {


	private Search simpleSearch = new Search();
	
	public SearchBuilder query(String query) {
		this.simpleSearch.setQuery(query);
		return this;
	}
	
	public SearchBuilder idTerminal(int idTerminal) {
		this.simpleSearch.setIdTerminal(idTerminal);
		return this;
	}
	
	public SearchBuilder idUsuario(int idUsuario) {
		this.simpleSearch.setIdUsuario(idUsuario);
		return this;
	}
	
	public SearchBuilder date(DateTime date) {
		String dateString = DateUtils.dateTimeToString(date);
		this.simpleSearch.setDate(dateString);
		return this;
	}

	public SearchBuilder notifyAdmins() {
		this.simpleSearch.addObserver(new NotifyAdminObserver());
		return this;
	}
	
	public SearchBuilder store() {
		this.simpleSearch.addObserver(new StoreResultsObserver());
		return this;
	}
	

	@Override
	public boolean isValidBuild() {
		boolean validSearchBuilt = true;
		validSearchBuilt &= this.simpleSearch.getQuery()!=null && this.simpleSearch.getQuery().trim().length()>0;
		validSearchBuilt &= this.simpleSearch.getIdTerminal() > 0;
		validSearchBuilt &= this.simpleSearch.getIdUsuario() >0;
		return validSearchBuilt;
	}

	@Override
	public Search returnBuildObject() {
		return this.simpleSearch;
	}
	
}
