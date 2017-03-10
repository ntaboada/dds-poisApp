package dds.poi.test;

import static org.mockito.Mockito.mock;
import dds.poi.builder.LocalComercialBuilder;
import dds.poi.builder.POIBuilder;
import dds.poi.builder.RangoBuilder;
import dds.poi.builder.RubroBuilder;
import dds.poi.builder.TerminalBuilder;
import dds.poi.builder.UserBuilder;
import dds.poi.builder.UserProfileBuilder;
import dds.poi.manager.POIManager;
import dds.poi.model.*;
import dds.poi.provider.repository.POIRepository;
import dds.poi.servicelocator.service.FileUpdateService;
import dds.poi.servicelocator.service.LocalComerciaFile;

import org.junit.Before;

import dds.poi.manager.UserManager;
import dds.poi.model.search.Terminal;
import dds.poi.model.search.user.User;
import dds.poi.model.search.user.UserActions;
import dds.poi.servicelocator.ServiceLocator;
import dds.poi.servicelocator.service.MailSender;
import dds.poi.stub.StubInactiveService;
import dds.poi.stub.StubMailSender;

import org.uqbar.geodds.Point;

public class InitTestsEntrega4 {

	protected Terminal terminal;
	protected MailSender mailSender;
	protected StubInactiveService inactiveService;
	protected User adminUser;
	protected FileUpdateService fileUpdateService;

	protected Point coordenadaCercanaLocalComercial;
	protected Point coordenadaNoCercanaLocalComercial;
	protected Rubro almacen;
	protected Point coordenadaAlmacen;
	protected LocalComercial localComercialAlmacen;
	protected POI almacenDonManolo;


	@Before
	protected void init() {
		this.mailSender = mock(StubMailSender.class);
		ServiceLocator.getInstance().setMailSender(this.mailSender);

		this.inactiveService = mock(StubInactiveService.class);
		ServiceLocator.getInstance().setInactivePOIsService(this.inactiveService);

		fileUpdateService = new LocalComerciaFile();

		ServiceLocator.getInstance().setWeekFileUpdateService(this.fileUpdateService);
		this.terminal = new TerminalBuilder().id(1).build();
		
		this.adminUser = new UserBuilder().id(1l).mail("admin1@dds.com").password("asd123")
				.profileType(new UserProfileBuilder().action(UserActions.ADMIN_ACTIONS).build()).build();
		
		UserManager.getInstance().addUser(this.adminUser);
		
		this.terminal.loginUser(this.adminUser.getEmail(), this.adminUser.getPassword());


		// POI DonManolo
		this.coordenadaCercanaLocalComercial = new Point(-34.643199, -58.505628);
		this.coordenadaNoCercanaLocalComercial = new Point(-34.662913, -58.529880);

		this.almacen = new RubroBuilder().nombreRubro("Almacen").radioCercania(1).build();

		Rango rango1 = new RangoBuilder().dia(1).horaDesde(10).minutosDesde(0).horaHasta(12).minutosHasta(30).build();
		Rango rango2 = new RangoBuilder().dia(2).horaDesde(10).minutosDesde(0).horaHasta(12).minutosHasta(30).build();

		this.localComercialAlmacen = new LocalComercialBuilder().rubro(almacen).rangoDisponible(rango1)
				.rangoDisponible(rango2).build();

		this.coordenadaAlmacen = new Point(-34.642536, -58.504869);

		this.almacenDonManolo = new POIBuilder().identificador(2l).coordenadas(coordenadaAlmacen).nombre("Almacén Don Manolo")
				.direccionPrincipal("Pichincha 2342").direccionSecundaria("").localComercial(localComercialAlmacen)
				.etiqueta("Fiambres").etiqueta("Despensa").etiqueta("Don Manolo").etiqueta("Clásico").build();

		/* POIManager y POIRepository */
		POISource poiSourceRepository = new POISource();
		poiSourceRepository.setDataProvider(POIRepository.getInstance());
		POIManager.getInstance().addPOISource(poiSourceRepository);


	}
	
}
