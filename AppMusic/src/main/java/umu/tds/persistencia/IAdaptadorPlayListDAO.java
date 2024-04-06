package umu.tds.persistencia;

import java.util.List;

import umu.tds.dominio.PlayList;

public interface IAdaptadorPlayListDAO {
	
	public void registrarPlayList(PlayList listaCancion);
	public void borrarPlayList(PlayList listaCancion);
	public void modificarPlayList(PlayList listaCancion);
	public PlayList recuperarPlayList(int codigo);
	public List<PlayList> recuperarTodasListas();
	public void borrarTodasPlayList();
	
}

