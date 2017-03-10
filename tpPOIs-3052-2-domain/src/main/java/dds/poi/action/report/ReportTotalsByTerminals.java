package dds.poi.action.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dds.poi.action.TerminalAction;
import dds.poi.action.search.Search;
import dds.poi.history.SearchsHistory;

public class ReportTotalsByTerminals implements TerminalAction<Map<Integer, Integer>> {

	@Override
	public Map<Integer, Integer> execute() {
		List<Search> listSearchs = SearchsHistory.getInstance().searchsTotalByTerminals();
		Map<Integer, Integer> report = new HashMap<Integer, Integer>();
		
		listSearchs.stream().forEach(search -> {
			
			if(report.containsKey(search.getIdTerminal())) {
				Integer currentQuantityOfSearch = report.get(search.getIdTerminal());
				currentQuantityOfSearch += search.getResultsSize();
				report.put(search.getIdTerminal(), currentQuantityOfSearch);
			}else{
				report.put(search.getIdTerminal(), search.getResultsSize());
			}
			
		});
		
		return report;
	}

}
