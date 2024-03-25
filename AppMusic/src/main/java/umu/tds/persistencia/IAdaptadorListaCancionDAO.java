package umu.tds.persistencia;

import java.util.List;

import umu.tds.dominio.ListaCanciones;

public interface IAdaptadorListaCancionDAO {
	
	public void registrarListaCanciones(ListaCanciones listaCancion);
	public void borrarListaCanciones(ListaCanciones listaCancion);
	public void modificarListaCanciones(ListaCanciones listaCancion);
	public ListaCanciones recuperarListaCanciones(int codigo);
	public List<ListaCanciones> recuperarTodasListas();
	public void borrarTodasListaCanciones();
	
}

