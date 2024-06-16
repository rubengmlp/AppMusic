package umu.tds.dominio.repositorios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import umu.tds.dominio.Cancion;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;
import umu.tds.persistencia.IAdaptadorCancionDAO;

public class RepositorioCanciones {
	private Map<String,Cancion> canciones;
	private static RepositorioCanciones unicaInstancia = new RepositorioCanciones();
	
	private FactoriaDAO dao;
	private IAdaptadorCancionDAO adaptadorCanciones;
	
	private RepositorioCanciones() {
		try {
			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			adaptadorCanciones = dao.getCancionDAO();
			this.canciones = new HashMap<String,Cancion>();
			this.cargarRepositorio();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean existeCancion(Cancion c) {
		return canciones.containsKey(c.getUrl());
	}
	
	public static RepositorioCanciones getUnicaInstancia() {
		return unicaInstancia;
	}
	
	public List<Cancion> getAllCanciones(){
		ArrayList<Cancion> listaCanciones = new ArrayList<Cancion>();
		for(Cancion c: this.canciones.values())
			listaCanciones.add(c);
		return listaCanciones;
	}
	
	public void addCancion(Cancion c) {
		canciones.put(c.getUrl(), c);
	}
	
	public void removeCancion(Cancion c) {
		canciones.remove(c.getUrl());
	}
	
	public Cancion getCancion(int codigo) {
		for(Cancion c: canciones.values()) {
			if(c.getCodigo() == codigo) return c;
		}
		return null;
	}
	
	public Cancion getCancion(String url) {
		return canciones.get(url);
	}
	
	private void cargarRepositorio() throws DAOException {
		List<Cancion> cancionesBD = adaptadorCanciones.recuperarTodasCanciones();
		for(Cancion c: cancionesBD)
			this.canciones.put(c.getUrl(), c);
	}

	public List<Cancion> obtenerTopDiez() {
		LinkedList<Cancion> topDiez = new LinkedList<Cancion>();
		for(Cancion c: this.canciones.values()) {
			topDiez.add(c);
			//Collections.sort(topDiez);
			if(topDiez.size() == 11) {
				topDiez.removeLast();
			}
		}
		
		return topDiez;
	}

	
}












