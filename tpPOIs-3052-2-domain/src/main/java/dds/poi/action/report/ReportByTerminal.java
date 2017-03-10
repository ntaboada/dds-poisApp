package dds.poi.action.report;

import java.util.List;
import java.util.stream.Collectors;

import dds.poi.action.TerminalAction;
import dds.poi.action.search.Search;
import dds.poi.history.SearchsHistory;

public class ReportByTerminal implements TerminalAction<List<Integer>> {

	private int idTerminal;
	
	@Override
	public List<Integer> execute() {
		List<Search> listSearchs = SearchsHistory.getInstance().searchsByTerminal(this.idTerminal);
		return listSearchs.stream()
				.filter(search -> search.getIdTerminal() == this.idTerminal)
				.map(search -> search.getResultsSize())
				.collect(Collectors.toList());
	}

	public int getIdTerminal() {
		return idTerminal;
	}

	public void setIdTerminal(int idTerminal) {
		this.idTerminal = idTerminal;
	}

}
