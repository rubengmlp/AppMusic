package umu.tds.dominio;

import java.util.List;

public class Cancion {
	private String titulo;
	private String rutaFichero;
	private int numReproducciones;
	private List<Interprete> interpretes;
	
	//TODO Constructor
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getRutaFichero() {
		return rutaFichero;
	}
	public void setRutaFichero(String rutaFichero) {
		this.rutaFichero = rutaFichero;
	}
	public int getNumReproducciones() {
		return numReproducciones;
	}
	public void setNumReproducciones(int numReproducciones) {
		this.numReproducciones = numReproducciones;
	}
	
	
}
