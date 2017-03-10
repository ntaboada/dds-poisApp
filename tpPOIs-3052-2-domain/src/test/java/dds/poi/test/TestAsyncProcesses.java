package dds.poi.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dds.poi.action.asyncprocess.AddUserActionsProcess;
import dds.poi.action.asyncprocess.DeletePOIsProcess;
import dds.poi.action.asyncprocess.MultipleAsyncProcesses;
import dds.poi.action.asyncprocess.UpdateLocalesComercialesProcess;
import dds.poi.builder.NewActionsBuilder;
import dds.poi.builder.UserBuilder;
import dds.poi.builder.UserProfileBuilder;
import dds.poi.exception.PermissionException;
import dds.poi.history.AsyncProcessResultHistory;
import dds.poi.manager.UserManager;
import dds.poi.model.asyncprocess.NewActions;
import dds.poi.model.search.user.User;
import dds.poi.model.search.user.UserActions;
import dds.poi.provider.repository.POIRepository;
import dds.poi.servicelocator.ServiceLocator;
import dds.poi.stub.StubInactiveService;

public class TestAsyncProcesses extends InitTestsEntrega4 {

	private AddUserActionsProcess addUserActionsProcess;
	private DeletePOIsProcess deletePOIsProcess;
	private UpdateLocalesComercialesProcess updateLocalesComercialesProcess;
	private MultipleAsyncProcesses multipleAsyncProcesses;
	
	private User consultUser;
	
	@Before
	public void init() {
		super.init();
		this.addUserActionsProcess = new AddUserActionsProcess(this.adminUser);
		this.multipleAsyncProcesses = new MultipleAsyncProcesses(this.adminUser);
		this.deletePOIsProcess = new DeletePOIsProcess(this.adminUser);
		this.updateLocalesComercialesProcess = new UpdateLocalesComercialesProcess(this.adminUser);

		this.multipleAsyncProcesses.addAsyncProcess(this.addUserActionsProcess);
		this.multipleAsyncProcesses.addAsyncProcess(this.updateLocalesComercialesProcess);
		this.multipleAsyncProcesses.addAsyncProcess(this.deletePOIsProcess);

		POIRepository.getInstance().create(this.almacenDonManolo);

		this.consultUser = new UserBuilder().id(2l).mail("mail2@dds.com").password("asd123")
				.profileType(new UserProfileBuilder().action(UserActions.SEARCH).action(UserActions.GENERATE_REPORT_BY_DATE).action(UserActions.GENERATE_REPORT_BY_TERMINAL)
						.action(UserActions.GENERATE_REPORT_TOTALS_TERMINAL) .build()).build();
		
		UserManager.getInstance().addUser(this.consultUser);
	}

	@After
	public void tearDown(){
		POIRepository.getInstance().delete(this.almacenDonManolo);
	}

	/* Test de Terminal y Procesos */
	@Test
	public void testAgregarProcesoOk() {
		this.terminal.loginUser(this.adminUser.getEmail(), this.adminUser.getPassword());
		this.terminal.addAsyncProcess(this.addUserActionsProcess);
	}

	@Test(expected=PermissionException.class)
	public void testAgregarProcesoError() {
		this.terminal.loginUser(this.consultUser.getEmail(), this.consultUser.getPassword());
		this.terminal.addAsyncProcess(this.addUserActionsProcess);
	}

	@Test
	public void testEjecutarProcesosOk() {
		this.terminal.loginUser(this.adminUser.getEmail(), this.adminUser.getPassword());
		this.terminal.executeAsyncProcesses();
	}

	@Test(expected=PermissionException.class)
	public void testEjecutarProcesosError() {
		this.terminal.loginUser(this.consultUser.getEmail(), this.consultUser.getPassword());
		this.terminal.executeAsyncProcesses();
	}

	@Test
	public void testAddActionUsersAsyncProcessOk() {
		int quantityOfActionsBefore = UserManager.getInstance().getAllUserActions().size();

		NewActions actions = new NewActionsBuilder().action("Cualquier accion, solo para test 1").action("Cualquier accion, solo para test 2").build();
		this.addUserActionsProcess.setNewActions(actions);

		this.addUserActionsProcess.execute();

		int quantityOfActionsAfter = UserManager.getInstance().getAllUserActions().size();

		Assert.assertEquals(2, quantityOfActionsAfter - quantityOfActionsBefore);
	}

	@Test
	public void testAddActionUsersAsyncProcessOkAndUndo() {
		int quantityOfActionsBefore = UserManager.getInstance().getAllUserActions().size();

		NewActions actions = new NewActionsBuilder().action("Cualquier accion, solo para test 1").action("Cualquier accion, solo para test 2").build();
		this.addUserActionsProcess.setNewActions(actions);

		this.addUserActionsProcess.execute();

		int quantityOfActionsAfter = UserManager.getInstance().getAllUserActions().size();

		Assert.assertEquals(2, quantityOfActionsAfter - quantityOfActionsBefore);
		quantityOfActionsBefore = quantityOfActionsAfter;

		this.addUserActionsProcess.undo();

		quantityOfActionsAfter = UserManager.getInstance().getAllUserActions().size();

		Assert.assertEquals(2, quantityOfActionsBefore - quantityOfActionsAfter);
	}

	@Test
	public void testMultipleProcessOk() {
		int quantityOfResultsBefore = AsyncProcessResultHistory.getInstance().getQuantityOfResults();

		this.multipleAsyncProcesses.execute();

		int quantityOfResultsAfter = AsyncProcessResultHistory.getInstance().getQuantityOfResults();

		Assert.assertEquals(4, quantityOfResultsAfter - quantityOfResultsBefore);

	}

	/* Test de Procesos Asincronicos */

	@Test
	public void testCorroboroEstadoDelRepo(){
		Assert.assertEquals(1, POIRepository.getInstance().findAll().size()); //Inicialmente en 1
	}

	@Test
	public void testUpdateProcessUpdateaOK()  {
		//CrearArchivo y especificar ruta. Agregar al archivo: "Fiambres;etiquetaNoExistente1 etiquetaNoExistente2"

		String filePath = "C:\\Users\\Nicolas\\Desktop\\conEtiquetasInexistentesEnElRepo.txt";
		this.fileUpdateService.setFilePath(filePath);
		int sizePreExecution = almacenDonManolo.getEtiquetas().size();
		updateLocalesComercialesProcess.execute();
		int sizePostExecution = almacenDonManolo.getEtiquetas().size();
		Assert.assertNotEquals(sizePreExecution, sizePostExecution);
	}

	@Test
	public void testUpdateRecibeUnPoiQueNoSufreCambiosEnSusEtiquetas() {
		//CrearArchivo y especificar ruta. Agregar al archivo: "Fiambres;Despensa"
		String filePath = "C:\\Users\\Nicolas\\Desktop\\conEtiquetasExistentesEnElRepo.txt";

		this.fileUpdateService.setFilePath(filePath);
		int sizePreExecution = almacenDonManolo.getEtiquetas().size();
		updateLocalesComercialesProcess.execute();
		int sizePostExecution = almacenDonManolo.getEtiquetas().size();
		Assert.assertEquals(sizePreExecution, sizePostExecution);
	}

	@Test
	public void testDeletePOIProcessDeleteaOK(){
		String inactivePois =  "[{\"valorBusqueda\":\"Fiambres\","+ "\"fechaBusqueda\":\"2015-01-01\"" + "}]";
		this.inactiveService = new StubInactiveService(inactivePois);
		ServiceLocator.getInstance().setInactivePOIsService(this.inactiveService);
		this.deletePOIsProcess.execute();
		Assert.assertEquals(0, POIRepository.getInstance().findAll().size());
	}
	@Test
	public void testDeletePOIProcessRecibePoisInactivosNoPertenecientesAlRepo(){
		String inactivePois = "[{\"valorBusqueda\":\"PoiNoPertenecienteAlRepo\","+ "\"fechaBusqueda\":\"2015-06-06\"" + "}]";
		this.inactiveService = new StubInactiveService(inactivePois);
		ServiceLocator.getInstance().setInactivePOIsService(this.inactiveService);
		this.deletePOIsProcess.execute();
		Assert.assertEquals(1, POIRepository.getInstance().findAll().size());
	}








}
