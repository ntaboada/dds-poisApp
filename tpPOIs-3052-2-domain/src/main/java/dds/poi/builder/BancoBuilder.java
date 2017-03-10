package dds.poi.builder;

import java.util.List;

import dds.poi.model.Banco;
import dds.poi.model.Servicio;
import dds.poi.model.ServicioBanco;

public class BancoBuilder extends Builder<Banco>{

	private Banco banco = new Banco();
	
	public BancoBuilder gerente(String gerente) {
		this.banco.setGerente(gerente);
		return this;
	}
	
	public BancoBuilder sucursal(String sucursal) {
		this.banco.setSucursal(sucursal);
		return this;
	}
	
	public BancoBuilder servicio(ServicioBanco servicio) {
		this.banco.addServicio(servicio);
		return this;
	}
	
	public BancoBuilder servicios(List<Servicio> servicios) {
		this.banco.setServicios(servicios);
		return this;
	}
	
	@Override
	public boolean isValidBuild() {
		return this.banco.getGerente()!=null && this.banco.getSucursal()!=null
				&& this.banco.getServicios()!=null && this.banco.getServicios().size()>0;
	}

	@Override
	public Banco returnBuildObject() {
		return this.banco;
	}

}
