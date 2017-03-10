package dds.poi.model;

import java.util.ArrayList;

public class Banco extends CategoriaConServicios {
	private String sucursal;
	private String gerente;

	public Banco() {
		
	}
	
	public Banco(String sucursal, String gerente, ArrayList<Servicio> servicios) {
		super(servicios);
		this.sucursal = sucursal;
		this.gerente = gerente;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getGerente() {
		return gerente;
	}
	public void setGerente(String gerente) {
		this.gerente = gerente;
	}
}
