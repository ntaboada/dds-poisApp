package dds.poi.test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import dds.poi.adapter.BancoJSONAdapter;
import dds.poi.adapter.CentroDTOAdapter;
import dds.poi.builder.POISourceBuilder;
import dds.poi.manager.POIManager;
import dds.poi.model.POISource;
import dds.poi.test.matchers.ValorQueryMatcher;
import dds.poi.test.stub.StubBancoProvider;
import dds.poi.test.stub.StubCGPProvider;

public class TestMocksPOIManager {

	private static final String VALOR_QUERY_GENERICO = "Mockito esta piola";

	private POIManager poiManagerForMocks;
	
	private StubCGPProvider mockCGPProvider;
	private StubBancoProvider mockBancoProvider;

	@Before
	public void init() {
		this.poiManagerForMocks = new POIManager();
		
		this.mockCGPProvider = mock(StubCGPProvider.class);
		POISource poiSourceCGPProvider = new POISourceBuilder().provider(this.mockCGPProvider).adapter(new CentroDTOAdapter()).build();
		this.poiManagerForMocks.addPOISource(poiSourceCGPProvider);

		this.mockBancoProvider = mock(StubBancoProvider.class);
		POISource poiSourceBancoProvider = new POISourceBuilder().provider(this.mockBancoProvider).adapter(new BancoJSONAdapter()).build();
		this.poiManagerForMocks.addPOISource(poiSourceBancoProvider);
	}

	/* Tests de Comportamiento para POIManager */

	@Test
	public void testServiceLocatorLlamaACGPProvider() {
		this.poiManagerForMocks.buscarPOIs("No importa el valor para este Test");

		verify(this.mockCGPProvider, times(1)).searchPOIs(any(String.class));
	}

	@Test
	public void testServiceLocatorLlamaABancoProvider() {
		this.poiManagerForMocks.buscarPOIs("No importa el valor para este Test");

		verify(this.mockBancoProvider, times(1)).searchPOIs(any(String.class));
	}

	@Test
	public void testPOIManagerLlamaAServiceLocatorYLuegoAProvidersConMismoArgumento() {
		this.poiManagerForMocks.buscarPOIs(VALOR_QUERY_GENERICO);

		verify(this.mockCGPProvider, times(1)).searchPOIs(
				Mockito.argThat(new ValorQueryMatcher(VALOR_QUERY_GENERICO)));

		verify(this.mockBancoProvider, times(1)).searchPOIs(
				Mockito.argThat(new ValorQueryMatcher(VALOR_QUERY_GENERICO)));
	}

	/* Fin Tests de Comportamiento para POIManager */

}
