package dds.poi.test;

import org.junit.Assert;
import org.junit.Test;

public class TestBancoProvider extends Inicializar {

	/* Tests BancoProvider con Stub */

	@Test
	public void testBuscarBancosSinCoincidencias() {
		String bancoJson = this.bancoProvider
				.searchPOIs("No voy a tener bancos");

		Assert.assertNull(bancoJson);
	}

	@Test
	public void testBuscarBancosCon1Coincidencia() {
		String bancoJson = this.bancoProvider.searchPOIs("Banco Santander Rio");

		Assert.assertNotNull(bancoJson);
	}

	@Test
	public void testBuscarBancosCon2Coincidencias() {
		String bancoJson = this.bancoProvider.searchPOIs("BancoDeLaPlaza");

		Assert.assertNotNull(bancoJson);
	}


	/* Fin Tests */

}
