package dds.poi.builder.action;

import org.joda.time.DateTime;

import dds.poi.action.report.ReportByDate;
import dds.poi.builder.Builder;

public class ReportByDateBuilder extends Builder<ReportByDate> {

	private ReportByDate reportByDate = new ReportByDate();

	public ReportByDateBuilder date(DateTime date) {
		this.reportByDate.setDate(date);
		return this;
	}

	public ReportByDate returnBuildObject() {
		return this.reportByDate;
	};

	@Override
	public boolean isValidBuild() {
		return this.reportByDate.getDate() != null;
	}

}
