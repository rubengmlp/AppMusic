package umu.tds.persistencia;

import java.util.List;

import umu.tds.dominio.PlayList;

public interface IAdaptadorPlayListDAO {
	
	public void registrarPlayList(PlayList playList);
	public void borrarPlayList(PlayList playList);
	public void modificarPlayList(PlayList playList);
	public PlayList recuperarPlayList(int codigo);
	public List<PlayList> recuperarTodasListas();
	public void borrarTodasPlayList();
	
}

