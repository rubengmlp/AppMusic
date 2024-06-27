package umu.tds.dominio;

import java.util.LinkedList;
import java.util.List;

public class PlayList {
	private int codigo;
	private final String nombre;
	private LinkedList<Cancion> canciones;

	public PlayList(String nombre) {
		this.nombre = nombre;
		this.canciones = new LinkedList<Cancion>();
	}

	public PlayList(String nombre, List<Cancion> lista) {
		this.nombre = nombre;
		this.canciones = (LinkedList<Cancion>) lista;
	}

	public void addCancion(Cancion c) {
		this.canciones.add(c);
	}
	
	public void addCanciones(List<Cancion> canciones) {
		this.canciones.addAll(canciones);
	}

	public void deleteCancion(Cancion c) {
		this.canciones.remove(c);
	}

	public int getCodigo() {
		return codigo;
	}

	public LinkedList<Cancion> getCanciones() {
		return canciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public boolean contieneCancion(Cancion cancion) {
	    for (Cancion c : canciones) {
	        if (c.getTitulo().equals(cancion.getTitulo())) {
	            return true;
	        }
	    }
	    return false;
	}
}
