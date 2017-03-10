package dds.poi.model.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import dds.poi.action.asyncprocess.AsyncProcess;
import dds.poi.action.report.ReportByDate;
import dds.poi.action.report.ReportByTerminal;
import dds.poi.action.report.ReportTotalsByTerminals;
import dds.poi.action.search.Search;
import dds.poi.action.user.LoginUserAction;
import dds.poi.builder.action.LoginUserActionBuilder;
import dds.poi.builder.action.ReportByDateBuilder;
import dds.poi.builder.action.ReportByTerminalBuilder;
import dds.poi.builder.action.ReportTotalsByTerminalsBuilder;
import dds.poi.builder.action.SearchBuilder;
import dds.poi.config.TerminalConfig;
import dds.poi.exception.PermissionException;
import dds.poi.model.POI;
import dds.poi.model.search.user.User;
import dds.poi.model.search.user.UserActions;

public class Terminal {

	private int idTerminal;
	private User user;

	private List<AsyncProcess> asyncProcesses = new ArrayList<AsyncProcess>();
	
	public int getIdTerminal() {
		return idTerminal;
	}

	public void setIdTerminal(int idTerminal) {
		this.idTerminal = idTerminal;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void addAsyncProcess(AsyncProcess process) {
		if(this.user.isAdministrator()) {
			this.getAsyncProcesses().add(process);			
		} else {
			throw new PermissionException();
		}
	}
	
	public void executeAsyncProcesses() {
		if(this.user.isAdministrator()) {
			this.getAsyncProcesses().stream().forEach(asyncProcess -> asyncProcess.execute());
		}else{
			throw new PermissionException();
		}
	}

	public void loginUser(String email, String password) {
		LoginUserAction loginUserAction = new LoginUserActionBuilder().email(email).password(password).build();
		this.user = loginUserAction.execute();
	}

	public List<POI> search(String valor) {
		if (this.user.canExecute(UserActions.SEARCH)) {
			Search search = new SearchBuilder()
									.query(valor)
									.idTerminal(this.idTerminal)
									.idUsuario(this.user.getIdUsuario())
									.date(new DateTime())
									.notifyAdmins()
									.store()
									.build();

			return search.execute();
		}
		return null;
	}

	public void enableDisableNotifyOnSearch(boolean value) {
		if (this.user.canExecute(UserActions.ENABLE_DISABLE_SEND_NOTIFICATIONS)) {
			TerminalConfig.getInstance().setAllowedToSendNotifications(value);
		}
	}

	public void modifyMaxResponseTime(int time) {
		if (this.user.canExecute(UserActions.MODIFY_MAX_RESPONSE_TIME)) {
			TerminalConfig.getInstance().setDurationTimeToNotify(time);
		}
	}

	public void modifySendMailOnFailure(boolean sendMail) {
		if (this.user.canExecute(UserActions.MODIFY_SEND_MAIL_ON_FAILURE)) {
			TerminalConfig.getInstance().setSendMailOnFailure(sendMail);
		}
	}

	public void modifyRetryCount(int retryCount) {
		if (this.user.canExecute(UserActions.MODIFY_RETRY_COUNT)) {
			TerminalConfig.getInstance().setRetryCount(retryCount);
		}
	}

	public Map<String, Integer> generateReportByDate(DateTime dateTime) {
		if (this.user.canExecute(UserActions.GENERATE_REPORT_BY_DATE)) {
			ReportByDate reportePorFecha = new ReportByDateBuilder().date(dateTime).build();
			return reportePorFecha.execute();
		}
		return null;
	}

	public List<Integer> generateReportByTerminal(int idTerminal) {
		if (this.user.canExecute(UserActions.GENERATE_REPORT_BY_TERMINAL)) {
			ReportByTerminal reporteParcialPorTerminal = new ReportByTerminalBuilder().idTerminal(idTerminal).build();
			return reporteParcialPorTerminal.execute();
		}
		return null;
	}

	public Map<Integer, Integer> generateReportTotalsByTerminals() {
		if (this.user.canExecute(UserActions.GENERATE_REPORT_TOTALS_TERMINAL)) {
			ReportTotalsByTerminals reporteTotalesTerminales = new ReportTotalsByTerminalsBuilder().build();
			return reporteTotalesTerminales.execute();
		}
		return null;
	}

	public List<AsyncProcess> getAsyncProcesses() {
		return asyncProcesses;
	}

	public void setAsyncProcesses(List<AsyncProcess> asyncProcesses) {
		this.asyncProcesses = asyncProcesses;
	}
}
