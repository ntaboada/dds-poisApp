package dds.poi.test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dds.poi.action.asyncprocess.AddUserActionsProcess;
import dds.poi.action.asyncprocess.DeletePOIsProcess;
import dds.poi.action.asyncprocess.MultipleAsyncProcesses;
import dds.poi.action.asyncprocess.UpdateLocalesComercialesProcess;
import dds.poi.history.AsyncProcessResultHistory;
import dds.poi.model.asyncprocess.NewActions;
import dds.poi.model.search.user.UserActions;
import dds.poi.test.builder.NewActionsBuilder;

public class TestManejoDeResultados extends InitTestsEntrega4 {
	private DeletePOIsProcess deletePOIsProcess;
	private UpdateLocalesComercialesProcess updateLocalesComercialesProcess;

	@Before
	public void init() {
		super.init();
		this.deletePOIsProcess = new DeletePOIsProcess(this.adminUser);
		this.updateLocalesComercialesProcess = new UpdateLocalesComercialesProcess(this.adminUser);
	}

	@Test
	public void testResultadoOkAddUserActions() {
		AddUserActionsProcess actionsProcess = new AddUserActionsProcess(this.adminUser);
		NewActions actions = new NewActionsBuilder().action(UserActions.MODIFY_SEND_MAIL_ON_FAILURE).action(UserActions.MODIFY_RETRY_COUNT).build();
		actionsProcess.setNewActions(actions);
		actionsProcess.execute();

		Assert.assertTrue(AsyncProcessResultHistory.getInstance().getLastResult().isSuccess());
	}

	@Test
	public void testResultadoOkMultipleAsyncProcesses() {
		MultipleAsyncProcesses multipleAsyncProcess = new MultipleAsyncProcesses(this.adminUser);
		multipleAsyncProcess.execute();

		Assert.assertTrue(AsyncProcessResultHistory.getInstance().getLastResult().isSuccess());
	}


	@Test
	public void testResultadoErrorAddUserActionsSinEnvioDeMail() {
		this.terminal.modifySendMailOnFailure(false);

		AddUserActionsProcess actionsProcess = new AddUserActionsProcess(this.adminUser);
		actionsProcess.execute();

		Assert.assertFalse(AsyncProcessResultHistory.getInstance().getLastResult().isSuccess());
	}

	@Test
	public void testResultadoErrorAddUserActionsConEnvioDeMail() {
		this.terminal.modifySendMailOnFailure(true);

		AddUserActionsProcess actionsProcess = new AddUserActionsProcess(this.adminUser);
		actionsProcess.execute();

		verify(this.mailSender, times(1)).sendProcessFailMail(any(String.class), any(String.class));
	}

	/*
		Version Vieja del Test NO funcionando
	@Test
	public void testServiceLocatorLllamaAInactivePOIs(){
		DeletePOIsProcess deletePOIsProcess = new DeletePOIsProcess(this.adminUser);
		deletePOIsProcess.execute();
		verify(this.inactiveService, times(1)).obtainInactivePOIs();
	}
	 */

	@Test
	public void testServiceLocatorLllamaAInactivePOIs() {
		DeletePOIsProcess deletePOIsProcess = new DeletePOIsProcess(this.adminUser);
		when(this.inactiveService.obtainInactivePOIs()).thenReturn("[{\"valorBusqueda\":\"NoImportaElValor\"," + "\"fechaBusqueda\":\"NoImportaElValor\"" + "}]");
		deletePOIsProcess.execute();
		verify(this.inactiveService, times(1)).obtainInactivePOIs();


	}
}
