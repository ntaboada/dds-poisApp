package dds.poi.test;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import dds.poi.config.TerminalConfig;
import dds.poi.exception.InvalidUserException;
import dds.poi.exception.PermissionException;
import dds.poi.model.POI;
import dds.poi.utils.DateUtils;

public class TestTerminal extends InitTestsEntrega3 {

	@Test(expected=InvalidUserException.class)
	public void testLoginError() {
		this.terminal.loginUser(this.invalidUser.getEmail(), this.invalidUser.getPassword());
		Assert.assertNotNull(this.terminal.getUser());
	}
	
	@Test
	public void testLoginOk() {
		this.terminal.loginUser(this.adminUser.getEmail(), this.adminUser.getPassword());
		Assert.assertNotNull(this.terminal.getUser());
	}
	
	@Test(expected=PermissionException.class)
	public void testModificarResponseTimeConConsultor() {
		this.terminal.loginUser(this.consultUser.getEmail(), this.consultUser.getPassword());
		this.terminal.modifyMaxResponseTime(100);
	}

	@Test(expected=PermissionException.class)
	public void testModificarNotificacionConConsultor() {
		this.terminal.loginUser(this.consultUser.getEmail(), this.consultUser.getPassword());
		this.terminal.enableDisableNotifyOnSearch(false);
	}
	
	@Test
	public void testModificarResponseTimeConAdmin() {
		this.terminal.loginUser(this.adminUser.getEmail(), this.adminUser.getPassword());
		this.terminal.modifyMaxResponseTime(100);
		Assert.assertEquals(TerminalConfig.getInstance().getResponseTimeToNotify(), 100);
	}
	
	@Test
	public void testModificarNotificacionConAdmin() {
		this.terminal.loginUser(this.adminUser.getEmail(), this.adminUser.getPassword());
		this.terminal.enableDisableNotifyOnSearch(false);
		Assert.assertEquals(TerminalConfig.getInstance().isAllowedToSendNotifications(), false);
		this.terminal.enableDisableNotifyOnSearch(true);
		Assert.assertEquals(TerminalConfig.getInstance().isAllowedToSendNotifications(), true);
	}
	
	@Test
	public void testBuscarConResultados() {
		List<POI> pois = this.terminal.search("114");
		Assert.assertNotNull(pois);
	}
	
	@Test
	public void testBuscarSinResultados() {
		List<POI> pois = this.terminal.search("No hay nada por aca");
		Assert.assertEquals(pois.size(), 0);
	}
	
	@Test
	public void testGenerarReportePorFechaConResultados() {
		DateTime dateTime = new DateTime();
		String currentDate = DateUtils.dateTimeToString(new DateTime());
		Map<String, Integer> map = this.terminal.generateReportByDate(dateTime);
		Assert.assertEquals(map.size(), 1);
		
		Assert.assertTrue(map.get(currentDate) > 0);
	}
	
	@Test
	public void testGenerarReportePorFechaSinResultados() {
		DateTime invalidDateTime = new DateTime(2016, 4, 5, 10, 10);
		Map<String, Integer> map = this.terminal.generateReportByDate(invalidDateTime);
		Assert.assertEquals(map.size(), 0);
	}
	
	@Test
	public void testGenerarReporteParcialPorTerminalOk() {
		List<Integer> list = this.terminal.generateReportByTerminal(this.terminal.getIdTerminal());
		Assert.assertTrue(list.size()>0);
	}
	
	@Test
	public void testGenerarReporteParcialPorTerminalError() {
		List<Integer> list = this.terminal.generateReportByTerminal(99012);
		Assert.assertTrue(list.size() == 0);
	}
	
	@Test
	public void testGenerarReporteTotalesTerminales() {
		Map<Integer, Integer> report = this.terminal.generateReportTotalsByTerminals();
		Assert.assertTrue(report.values().size()>0);
	}
}
