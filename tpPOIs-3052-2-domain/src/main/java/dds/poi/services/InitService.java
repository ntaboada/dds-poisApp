package dds.poi.services;

import org.springframework.stereotype.Service;
import org.uqbar.geodds.Point;

import dds.poi.adapter.BancoJSONAdapter;
import dds.poi.adapter.CentroDTOAdapter;
import dds.poi.builder.LocalComercialBuilder;
import dds.poi.builder.POIBuilder;
import dds.poi.builder.ParadaColectivoBuilder;
import dds.poi.builder.RangoBuilder;
import dds.poi.builder.RubroBuilder;
import dds.poi.builder.UserBuilder;
import dds.poi.builder.UserProfileBuilder;
import dds.poi.manager.POIManager;
import dds.poi.model.LocalComercial;
import dds.poi.model.POI;
import dds.poi.model.POISource;
import dds.poi.model.ParadaColectivo;
import dds.poi.model.Rango;
import dds.poi.model.Rubro;
import dds.poi.model.search.Terminal;
import dds.poi.model.search.user.User;
import dds.poi.model.search.user.UserActions;
import dds.poi.provider.repository.POIRepository;
import dds.poi.provider.repository.UserRepository;
import dds.poi.stub.StubBancoProvider;
import dds.poi.stub.StubCGPProvider;
import dds.poi.stub.StubInactiveService;
import dds.poi.stub.StubMailSender;

@Service
public class InitService {

	protected StubCGPProvider cgpProvider;
	protected StubBancoProvider bancoProvider;
	protected StubInactiveService inactiveService;

	// -----------Parada Colectivo-------------//
	protected Point coordenadaCercanaColectivo;
	protected Point coordenadaNoCercanaColectivo;
	protected POI parada114;
	private ParadaColectivo paradaDeColectivo;
	private Point coordenadaParada;

	// -----------Locales Comerciales-------------//
	protected Point coordenadaCercanaLocalComercial;
	protected Point coordenadaNoCercanaLocalComercial;
	private Rubro almacen;
	private Point coordenadaAlmacen;
	private LocalComercial localComercialAlmacen;
	protected POI almacenDonManolo;

	// ---TERMINAL----
	protected Terminal terminal;

	private StubMailSender stubMailSender;

	protected User adminUser;
	protected User consultUser;
	protected User invalidUser;

	public void inicializar() {
		this.initEntrega3();
		this.initEntrega1();
	}

	public void initEntrega1() {
		// Parada 114
		this.paradaDeColectivo = new ParadaColectivoBuilder().lineaColectivo(
				"114").build();

		this.coordenadaCercanaColectivo = new Point(-34.646442, -58.498383);
		this.coordenadaNoCercanaColectivo = new Point(-34.660581, -58.507095);

		this.coordenadaParada = new Point(-34.646442, -58.498383);
		this.parada114 = new POIBuilder().imagen("fa-bus")
				.coordenadas(coordenadaParada)
				.nombre("Parada UTN Campus|Linea 114")
				.direccionPrincipal("Mozart 2300")
				.direccionSecundaria("Saraza 1200").identificador(1l)
				.paradaColectivo(paradaDeColectivo).etiqueta("Campus")
				.etiqueta("Bondi").etiqueta("Colectivo").build();

		// Almacen DonManolo
		this.coordenadaCercanaLocalComercial = new Point(-34.643199, -58.505628);
		this.coordenadaNoCercanaLocalComercial = new Point(-34.662913,
				-58.529880);

		this.almacen = new RubroBuilder().nombreRubro("Almacen")
				.radioCercania(1).build();

		Rango rango1 = new RangoBuilder().dia(1).horaDesde(10).minutosDesde(0)
				.horaHasta(12).minutosHasta(30).build();
		Rango rango2 = new RangoBuilder().dia(2).horaDesde(10).minutosDesde(0)
				.horaHasta(12).minutosHasta(30).build();

		this.localComercialAlmacen = new LocalComercialBuilder().rubro(almacen)
				.rangoDisponible(rango1).rangoDisponible(rango2).build();

		this.coordenadaAlmacen = new Point(-34.642536, -58.504869);

		this.almacenDonManolo = new POIBuilder().imagen("fa-shopping-cart")
				.identificador(2l).coordenadas(coordenadaAlmacen)
				.nombre("Almacén Don Manolo")
				.direccionPrincipal("Pichincha 2342").direccionSecundaria("")
				.localComercial(localComercialAlmacen).etiqueta("Fiambres")
				.etiqueta("Despensa").etiqueta("Don Manolo")
				.etiqueta("Clásico").build();

		/* POIManager y POIRepository */

 		POIRepository.getInstance().create(this.almacenDonManolo);
		POIRepository.getInstance().create(this.parada114);

		POISource poiSourceRepository = new POISource();
		poiSourceRepository.setDataProvider(POIRepository.getInstance());
		POIManager.getInstance().addPOISource(poiSourceRepository);

		/* Stubs */
		this.cgpProvider = new StubCGPProvider();
		POISource poiSourceCGPProvider = new POISource();
		poiSourceCGPProvider.setDataProvider(this.cgpProvider);
		poiSourceCGPProvider.setAdapter(new CentroDTOAdapter());
		POIManager.getInstance().addPOISource(poiSourceCGPProvider);

		this.bancoProvider = new StubBancoProvider();
		POISource poiSourceBancoProvider = new POISource();
		poiSourceBancoProvider.setDataProvider(this.bancoProvider);
		poiSourceBancoProvider.setAdapter(new BancoJSONAdapter());
		POIManager.getInstance().addPOISource(poiSourceBancoProvider);
	}

	public void initEntrega3() {
		this.adminUser = new UserBuilder()
				.id(1l)
				.userName("Admin")
				.mail("admin1@dds.com")
				.password("asd123")
				.profileType(
						new UserProfileBuilder().action(
								UserActions.ADMIN_ACTIONS).build()).build();
		this.consultUser = new UserBuilder()
				.id(2l)
				.userName("Usuario1")
				.mail("mail2@dds.com")
				.password("asd123")
				.profileType(
						new UserProfileBuilder()
								.action(UserActions.SEARCH)
								.action(UserActions.GENERATE_REPORT_BY_DATE)
								.action(UserActions.GENERATE_REPORT_BY_TERMINAL)
								.action(UserActions.GENERATE_REPORT_TOTALS_TERMINAL)
								.build()).build();
		this.invalidUser = new UserBuilder()
				.id(3l)
				.userName("UsuarioCualka")
				.mail("cualka")
				.password("asd123")
				.profileType(
						new UserProfileBuilder().action(
								UserActions.GENERATE_REPORT_BY_DATE).build())
				.build();

		UserRepository.getInstance().create(adminUser);
		UserRepository.getInstance().create(consultUser);
		UserRepository.getInstance().create(invalidUser);
	}
}
