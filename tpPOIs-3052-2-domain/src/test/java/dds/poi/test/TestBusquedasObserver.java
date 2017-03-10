package dds.poi.test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dds.poi.history.SearchsHistory;
import dds.poi.servicelocator.ServiceLocator;
import dds.poi.servicelocator.service.MailSender;
import dds.poi.stub.StubMailSender;

@SuppressWarnings("unchecked")
public class TestBusquedasObserver extends InitTestsEntrega3 {

	private MailSender mockedMailSender;
	
	@Before
	public void initTestBusquedaDecorator() {
		this.mockedMailSender = mock(StubMailSender.class);

		ServiceLocator.getInstance().setMailSender(this.mockedMailSender);
	}
	
	@Test
	public void testNoMandaMailAAdministradores() {
		this.terminal.modifyMaxResponseTime(10000);
		this.terminal.search("114");
		verify(this.mockedMailSender, times(0)).sendEmail(any(List.class));
	}
	
	@Test
	public void testMandaMailAAdministradores() {
		this.terminal.modifyMaxResponseTime(-1);
		this.terminal.search("114");
		verify(this.mockedMailSender, times(1)).sendEmail(any(List.class));
	}
	
	@Test
	public void testNoNotificarAAdministradoresOk() {
		this.terminal.enableDisableNotifyOnSearch(false);
		this.terminal.modifyMaxResponseTime(-1);
		this.terminal.search("114");
		verify(this.mockedMailSender, times(0)).sendEmail(any(List.class));
	}
	
	@Test
	public void testNoSolicitaMailsAUserService() {
		this.terminal.modifyMaxResponseTime(10000);
		this.terminal.search("114");
		verify(this.mockedMailSender, times(0)).sendEmail(any(List.class));
	}

	@Test
	public void testSolicitaMailsAUserService() {
		this.terminal.modifyMaxResponseTime(-1);
		this.terminal.search("114");
		verify(this.mockedMailSender, times(1)).sendEmail(any(List.class));
	}
	
	@Test
	public void testGuardaBusquedaEnHistorial() {
		int searchsBeforeSearch = SearchsHistory.getInstance().searchsTotalByTerminals().size();
		this.terminal.search("114");
		int searchsAfterSearch = SearchsHistory.getInstance().searchsTotalByTerminals().size();
		Assert.assertEquals(1, searchsAfterSearch-searchsBeforeSearch);
	}
	
	
}
