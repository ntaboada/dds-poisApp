package dds.poi.observer;

import dds.poi.action.search.Search;
import dds.poi.config.TerminalConfig;
import dds.poi.manager.UserManager;
import dds.poi.servicelocator.ServiceLocator;

import java.util.List;

public class NotifyAdminObserver implements Observer {

    public NotifyAdminObserver() {
    }

    @Override
    public void update(Search search) {
        if(TerminalConfig.getInstance().isAllowedToSendNotifications()) {
            this.sendEmailToAdministrators(search);
        }

    }
    private void sendEmailToAdministrators(Search search) {
        if(search.getResponseTime() > TerminalConfig.getInstance().getResponseTimeToNotify()) {
            List<String> adminProfiles = UserManager.getInstance().getAdminProfilesEmails();

            ServiceLocator.getInstance().sendMailToAdministrators(adminProfiles);
        }
    }


}
