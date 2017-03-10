package dds.poi.test;

import dds.poi.test.stub.StubInactiveService;
import org.junit.After;
import org.junit.Before;
import org.uqbar.geodds.Point;

import dds.poi.adapter.BancoJSONAdapter;
import dds.poi.adapter.CentroDTOAdapter;
import dds.poi.builder.LocalComercialBuilder;
import dds.poi.builder.POIBuilder;
import dds.poi.builder.ParadaColectivoBuilder;
import dds.poi.builder.RangoBuilder;
import dds.poi.builder.RubroBuilder;
import dds.poi.manager.POIManager;
import dds.poi.model.LocalComercial;
import dds.poi.model.POI;
import dds.poi.model.POISource;
import dds.poi.model.ParadaColectivo;
import dds.poi.model.Rango;
import dds.poi.model.Rubro;
import dds.poi.provider.repository.POIRepository;
import dds.poi.test.stub.StubBancoProvider;
import dds.poi.test.stub.StubCGPProvider;

public class Inicializar {

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

	@Before
	public void inicializar() {
		// Parada 114
		this.paradaDeColectivo = new ParadaColectivoBuilder().lineaColectivo("114").build();
		
		this.coordenadaCercanaColectivo = new Point(-34.646442, -58.498383);
		this.coordenadaNoCercanaColectivo = new Point(-34.660581, -58.507095);
		
		this.coordenadaParada = new Point(-34.646442, -58.498383);
		this.parada114 = new POIBuilder().coordenadas(coordenadaParada).nombre("Parada UTN Campus|Linea 114").direccionPrincipal("Mozart 2300")
				.direccionSecundaria("Saraza 1200").identificador(1).paradaColectivo(paradaDeColectivo)
				.etiqueta("Campus").etiqueta("Bondi").etiqueta("Colectivo").build();
		
		// Almacen DonManolo
		this.coordenadaCercanaLocalComercial = new Point(-34.643199, -58.505628);
		this.coordenadaNoCercanaLocalComercial = new Point(-34.662913, -58.529880);
		
		this.almacen = new RubroBuilder().nombreRubro("Almacen").radioCercania(1).build();

		Rango rango1 = new RangoBuilder().dia(1).horaDesde(10).minutosDesde(0).horaHasta(12).minutosHasta(30).build();
		Rango rango2 = new RangoBuilder().dia(2).horaDesde(10).minutosDesde(0).horaHasta(12).minutosHasta(30).build();

		this.localComercialAlmacen = new LocalComercialBuilder().rubro(almacen).rangoDisponible(rango1)
				.rangoDisponible(rango2).build();
		
		this.coordenadaAlmacen = new Point(-34.642536, -58.504869);

		this.almacenDonManolo = new POIBuilder().identificador(2).coordenadas(coordenadaAlmacen).nombre("Almacén Don Manolo")
				.direccionPrincipal("Pichincha 2342").direccionSecundaria("").localComercial(localComercialAlmacen)
				.etiqueta("Fiambres").etiqueta("Despensa").etiqueta("Don Manolo").etiqueta("Clásico").build();

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
	
	@After
	public void cleanPOIRepositoryAndPOIManager() {
		POIRepository.getInstance().deleteAll();
		POIManager.getInstance().cleanProviders();
	}

}
