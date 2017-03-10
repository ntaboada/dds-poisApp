package dds.poi.servicelocator;

import dds.poi.servicelocator.service.FileUpdateService;
import dds.poi.servicelocator.service.InactivePOIsService;
import dds.poi.servicelocator.service.MailSender;

import java.io.IOException;
import java.util.List;

public class ServiceLocator {

	private static ServiceLocator _selfInstance;

	private MailSender mailSender;
	private InactivePOIsService inactivePOIsService;
	private FileUpdateService weekFileUpdateService;

	public static ServiceLocator getInstance() {
		if (_selfInstance == null) {
			_selfInstance = new ServiceLocator();
		}
		return _selfInstance;
	}

	public void sendMailToAdministrators(List<String> mailAddresses) {
		this.mailSender.sendEmail(mailAddresses);
	}

	public void sendMail(String mailAddress) {
		this.mailSender.sendEmail(mailAddress);
	}

	public void sendProcessFailMail(String email, String processName) {
		this.mailSender.sendProcessFailMail(email, processName);
	}
	
	public String getInactivePOIs() {
		return this.inactivePOIsService.obtainInactivePOIs();
	}
	
	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public InactivePOIsService getInactivePOIsService() {
		return inactivePOIsService;
	}

	public void setInactivePOIsService(InactivePOIsService inactivePOIsService) {
		this.inactivePOIsService = inactivePOIsService;
	}

	public void weeklyLocalComercialToUpdate() throws IOException {
		this.weekFileUpdateService.localComercialesToUpdate();
	}
	public FileUpdateService getWeekFileUpdateService() {
		return weekFileUpdateService;
	}

	public void setWeekFileUpdateService(FileUpdateService weekFileUpdateService) {
		this.weekFileUpdateService = weekFileUpdateService;
	}
}
