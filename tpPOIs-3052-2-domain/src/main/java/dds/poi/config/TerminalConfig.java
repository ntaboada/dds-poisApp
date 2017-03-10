package dds.poi.config;

public class TerminalConfig {

	private static final int DURATION_TIME_TO_NOTIFY_DEFAULT_SECONDS = 10;
	private static final int RETRY_COUNT_DEFAULT = 3;
	private static final boolean ALLOWED_TO_SEND_NOTIFICATION_DEFAULT = true;
	private static final boolean SEND_MAIL_ON_FAILURE_DEFAULT = true;
	
	private static TerminalConfig _selfInstance;
	
	private boolean allowedToSendNotifications;
	private int responseTimeToNotify;
	private boolean sendMailOnFailure;
	private int retryCount;
	
	public static TerminalConfig getInstance() {
		if(_selfInstance == null) {
			_selfInstance = loadConfig();
		}
		return _selfInstance;
	}

	private static TerminalConfig loadConfig() {
		TerminalConfig config = new TerminalConfig();
		
		config.setAllowedToSendNotifications(ALLOWED_TO_SEND_NOTIFICATION_DEFAULT);
		config.setDurationTimeToNotify(DURATION_TIME_TO_NOTIFY_DEFAULT_SECONDS);
		config.setSendMailOnFailure(SEND_MAIL_ON_FAILURE_DEFAULT);
		config.setRetryCount(RETRY_COUNT_DEFAULT);
		
		return config;
	}

	public boolean isAllowedToSendNotifications() {
		return allowedToSendNotifications;
	}

	public void setAllowedToSendNotifications(boolean allowedToSendNotifications) {
		this.allowedToSendNotifications = allowedToSendNotifications;
	}

	public int getResponseTimeToNotify() {
		return responseTimeToNotify;
	}

	public void setDurationTimeToNotify(int durationTimeToNotifiy) {
		this.responseTimeToNotify = durationTimeToNotifiy;
	}

	public void setSendMailOnFailure(boolean sendMail) {
		this.sendMailOnFailure = sendMail;
	}

	public boolean getSendMailOnFailure() {
		return sendMailOnFailure;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public int getRetryCount() {
		return retryCount;
	}
	
	
	
}
