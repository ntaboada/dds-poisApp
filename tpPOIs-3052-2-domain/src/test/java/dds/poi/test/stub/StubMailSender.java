package dds.poi.test.stub;

import java.util.List;

import dds.poi.servicelocator.service.MailSender;

public class StubMailSender implements MailSender {

	@Override
	public boolean sendEmail(List<String> mailAddresses) {
		return true;
	}

	@Override
	public boolean sendEmail(String mailAddresses) {
		return true;
	}

	@Override
	public boolean sendProcessFailMail(String email, String processName) {
		return true;
	}

}
