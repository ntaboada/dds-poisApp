package dds.poi.builder.action;

import dds.poi.action.report.ReportByTerminal;
import dds.poi.builder.Builder;

public class ReportByTerminalBuilder extends Builder<ReportByTerminal> {

	private ReportByTerminal reportByTerminal = new ReportByTerminal();

	public ReportByTerminalBuilder idTerminal(int idTerminal) {
		this.reportByTerminal.setIdTerminal(idTerminal);
		return this;
	}

	@Override
	public boolean isValidBuild() {
		return this.reportByTerminal.getIdTerminal() > 0;
	}

	@Override
	public ReportByTerminal returnBuildObject() {
		return this.reportByTerminal;
	}

}
