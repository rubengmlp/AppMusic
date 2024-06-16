package umu.tds.dominio;

import java.util.List;


public class Cancion {
	private int codigo;
	private String titulo;
	private int numRep;
	private final String url;
	private String interprete;
	private List<String> estilosMusicales;

	public Cancion(String titulo, int numRepro, String url, String interprete, List<String> estilos) {
		this.titulo = titulo;
		this.numRep =  numRepro;
		this.url = url;
		this.interprete = interprete;
		this.estilosMusicales = estilos;
	}

	public Cancion (String titulo, int numRepro, String url, String interprete) {
		this.titulo = titulo;
		this.numRep =  numRepro;
		this.url = url;
		this.interprete = interprete;
	}

	public void addReproduccion() {
		this.numRep++;
	}


	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getNumRep() {
		return numRep;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getUrl() {
		return url;
	}
	
	public int compareTo(Cancion c) {
		return c.getNumRep() - this.numRep;
	}

	public List<String> getEstilosMusicales() {
		return estilosMusicales;
	}

	public void setEstilosMusicales(List<String> estilosMusicales) {
		this.estilosMusicales = estilosMusicales;
	}

	public void setNumRep(int numRep) {
		this.numRep = numRep;
	}
	
	public String getInterprete() {
		return interprete;
	}


	
}
