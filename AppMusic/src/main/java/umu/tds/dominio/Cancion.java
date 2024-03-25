package umu.tds.dominio;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cancion {

	private String titulo;
	private int numRep;
	private int codigo;
	private final String url;
	private Set<Etiqueta> etiquetas = new HashSet<Etiqueta>();

	public Cancion (String titulo, int numRepro, String url, Etiqueta... e) {
		this.titulo = titulo;
		this.numRep =  numRepro;
		this.url = url;
		for(Etiqueta i : e) {
			etiquetas.add(i);
		}
	}

	public Cancion(String titulo, int numRepro, String url, List<Etiqueta> e) {
		this.titulo = titulo;
		this.numRep =  numRepro;
		this.url = url;
		for(Etiqueta i : e) {
			etiquetas.add(i);
		}
	}

	public Cancion (String titulo, int numRepro, String url) {
		this.titulo = titulo;
		this.numRep =  numRepro;
		this.url = url;
	}

	public void addReproduccion() {
		this.numRep++;
	}

	public void addEtiqueta(Etiqueta e) {
		this.etiquetas.add(e);
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

	public Set<Etiqueta> getEtiquetas() {
		return etiquetas;
	}

	public String getUrl() {
		return url;
	}

	public boolean contieneEtiquetas(List<String> etiquetasSelecionadas) {
		for(Etiqueta e: this.etiquetas) {
			if(etiquetasSelecionadas.contains(e.getNombre())) return true;
		}
		return false;
	}
	
	public boolean contieneEtiqueta(String s) {
		for(Etiqueta e: this.etiquetas) {
			if(e.getNombre().equals(s)) return true;
		}
		return false;
	}
	
	public int compareTo(Cancion c) {
		return c.getNumRep() - this.numRep;
	}
		
}
