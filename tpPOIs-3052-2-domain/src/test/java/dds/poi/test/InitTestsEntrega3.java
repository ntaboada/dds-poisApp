package dds.poi.test;

import org.junit.Before;

import dds.poi.builder.TerminalBuilder;
import dds.poi.builder.UserBuilder;
import dds.poi.builder.UserProfileBuilder;
import dds.poi.manager.UserManager;
import dds.poi.model.search.Terminal;
import dds.poi.model.search.user.User;
import dds.poi.model.search.user.UserActions;
import dds.poi.servicelocator.ServiceLocator;
import dds.poi.stub.StubMailSender;

public class InitTestsEntrega3 extends Inicializar {

	protected Terminal terminal;

	private StubMailSender stubMailSender;

	protected User adminUser;
	protected User consultUser;
	protected User invalidUser;

	@Before
	public void init() {
		this.stubMailSender = new StubMailSender();
		ServiceLocator.getInstance().setMailSender(this.stubMailSender);

		this.terminal = new TerminalBuilder().id(1).build();

		this.adminUser = new UserBuilder().id(1l).mail("admin1@dds.com").password("asd123")
				.profileType(new UserProfileBuilder().action(UserActions.ADMIN_ACTIONS).build()).build();
		this.consultUser = new UserBuilder().id(2l).mail("mail2@dds.com").password("asd123")
				.profileType(new UserProfileBuilder().action(UserActions.SEARCH).action(UserActions.GENERATE_REPORT_BY_DATE).action(UserActions.GENERATE_REPORT_BY_TERMINAL)
						.action(UserActions.GENERATE_REPORT_TOTALS_TERMINAL) .build()).build();
		this.invalidUser = new UserBuilder().id(3l).mail("cualka").password("asd123")
				.profileType(new UserProfileBuilder().action(UserActions.GENERATE_REPORT_BY_DATE).build()).build();

		UserManager.getInstance().addUser(this.adminUser);
		UserManager.getInstance().addUser(this.consultUser);
		
		this.terminal.loginUser(this.adminUser.getEmail(), this.adminUser.getPassword());

		this.terminal.search("114");
		this.terminal.search("almacen");
		this.terminal.search("91j9˜1");
	}

}
