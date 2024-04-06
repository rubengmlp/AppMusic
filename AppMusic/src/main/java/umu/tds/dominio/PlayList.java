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

	public void agregarReciente(Cancion c) {
		if(canciones.contains(c)) {
			canciones.remove(c);
			canciones.addFirst(c);
		}else {
			if(canciones.size() == 5) {
				canciones.removeLast();
			}
			canciones.addFirst(c);
		}
	}

}
