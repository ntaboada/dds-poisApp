package dds.poi.test.stub;

import dds.poi.provider.dataprovider.BancoProvider;

public class StubBancoProvider implements BancoProvider {

	private String jsonBancoUnicaSucursal;
	private String jsonBancoDosSucursales;

	public StubBancoProvider() {
		this.jsonBancoDosSucursales = "[{\"banco\":\"BancoDeLaPlaza\","
				+ "\"x\":-35.9338322,\"y\":72.348353,\"sucursal\":\"Avellaneda\","
				+ "\"gerente\":\"Javier Loeschbor\","
				+ "\"servicios\":[\"cobrocheques\",\"depositos\",\"extracciones\",\"transferencias\",\"creditos\",\"\"]},"
				+ "{\"banco\":\"BancoDeLaPlaza\",\"x\":-35.9338322,\"y\":-58.121212,"
				+ "\"sucursal\":\"Nuniez\",\"gerente\":\"Pablo Mendez\","
				+ "\"servicios\":[\"cajadeahorro\",\"plazofijo\",\"fondodeinversion\"]}]";

		this.jsonBancoUnicaSucursal = "[{\"banco\":\"Banco Santander Rio\","
				+ "\"x\":-35.9338322,\"y\":72.348353,\"sucursal\":\"Avellaneda\","
				+ "\"gerente\":\"Javier Loeschbor\","
				+ "\"servicios\":[\"cobrocheques\",\"depositos\",\"extracciones\",\"transferencias\",\"creditos\",\"\"]}]";
	}

	@Override
	public String searchPOIs(String value) {
		if (value.equals("BancoDeLaPlaza")) {
			return this.jsonBancoDosSucursales;
		} else if (value.equals("Banco Santander Rio")) {
			return this.jsonBancoUnicaSucursal;
		}
		return null;
	}

}
