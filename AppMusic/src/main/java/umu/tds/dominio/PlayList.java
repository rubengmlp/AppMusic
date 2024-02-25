package umu.tds.dominio;

import java.util.List;

public class PlayList {
	private String nombre;
	private List<Cancion> canciones;
	
	//TODO Constructor
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Cancion> getCanciones() {
		return canciones;
	}
	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
	}
	
	
}
