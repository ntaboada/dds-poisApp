package dds.poi.builder.action;

import dds.poi.action.report.ReportTotalsByTerminals;
import dds.poi.builder.Builder;

public class ReportTotalsByTerminalsBuilder extends Builder<ReportTotalsByTerminals>{

	private ReportTotalsByTerminals reportTotalsByTerminal = new ReportTotalsByTerminals();
	
	@Override
	public ReportTotalsByTerminals returnBuildObject() {
		return this.reportTotalsByTerminal;
	}

	@Override
	public boolean isValidBuild() {
		return true;
	}

}
