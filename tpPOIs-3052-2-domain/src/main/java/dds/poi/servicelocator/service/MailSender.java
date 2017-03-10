package dds.poi.servicelocator.service;

import java.util.List;

public interface MailSender {

	public boolean sendEmail(List<String> mailAddresses);
	
	public boolean sendEmail(String mailAddresses);

	public boolean sendProcessFailMail(String email, String processName);
	
}
