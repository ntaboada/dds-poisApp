package dds.poi.action.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import dds.poi.action.TerminalAction;
import dds.poi.action.search.Search;
import dds.poi.history.SearchsHistory;
import dds.poi.utils.DateUtils;

public class ReportByDate implements TerminalAction<Map<String, Integer>> {

	private DateTime date;

	@Override
	public Map<String, Integer> execute() {
		List<Search> listSearchs = SearchsHistory.getInstance().searchsByDate(this.date);
		Map<String, Integer> report = new HashMap<String, Integer>();
		
		String dateString = DateUtils.dateTimeToString(this.date);
		
		listSearchs.stream()
		.filter(search -> search.getDate().equals(dateString))
		.forEach(search -> {
			
			if(report.containsKey(search.getDate())) {
				Integer currentQuantityOfSearch = report.get(search.getDate());
				currentQuantityOfSearch += search.getResultsSize();
				report.put(search.getDate(), currentQuantityOfSearch);
			}else{
				report.put(search.getDate(), search.getResultsSize());
			}
			
		});
		
		return report;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

}
