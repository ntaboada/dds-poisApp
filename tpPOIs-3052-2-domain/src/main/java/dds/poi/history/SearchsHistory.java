package dds.poi.history;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import dds.poi.action.search.Search;
import dds.poi.utils.DateUtils;

public class SearchsHistory {

	private static SearchsHistory _selfInstance;
	
	private List<Search> searchs = new ArrayList<>();
	
	public static SearchsHistory getInstance() {
		if(_selfInstance==null) {
			_selfInstance = new SearchsHistory();
		}
		return _selfInstance;
	}
	
	public void addSearch(Search simpleSearch) {
		this.searchs.add(simpleSearch);
	}
	
	public List<Search> searchsByDate(DateTime date) {
		String dateString = DateUtils.dateTimeToString(date);
		Predicate<Search> predicate = search -> search.getDate().equals(dateString);
		return this.searchs.stream().filter(predicate).collect(Collectors.toList());
	}
	
	public List<Search> searchsByTerminal(int idTerminal) {
		Predicate<Search> predicate = search -> search.getIdTerminal() == idTerminal;
		return this.searchs.stream().filter(predicate).collect(Collectors.toList());
	}
	
	public List<Search> searchsTotalByTerminals() {
		return this.searchs;
	}
	
}
