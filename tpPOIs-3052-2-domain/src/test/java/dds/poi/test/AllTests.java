package dds.poi.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestBusquedaDePuntos.class, TestPuntosDeInteres.class,
		TestServicioDisponibilidad.class, TestRangoDateUtils.class,
		TestBancoProvider.class, TestCGPProvider.class,
		TestPOIRepository.class, TestMocksPOIManager.class, 
		TestTerminal.class, TestBusquedasObserver.class,
		TestManejoDeResultados.class, TestAsyncProcesses.class})
public class AllTests {

}