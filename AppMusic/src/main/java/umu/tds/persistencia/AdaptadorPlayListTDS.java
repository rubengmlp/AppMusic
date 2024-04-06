package umu.tds.persistencia;

import java.util.List;

import beans.Entidad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.dominio.PlayList;

public class AdaptadorPlayListTDS implements IAdaptadorPlayListDAO {

    private static AdaptadorPlayListTDS unicaInstancia;
    private static ServicioPersistencia servPersistencia;

    private AdaptadorPlayListTDS() {
        // Constructor privado para evitar instanciación externa
        servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }

    public static AdaptadorPlayListTDS getUnicaInstancia() {
        if (unicaInstancia == null)
            return new AdaptadorPlayListTDS();
        else
            return unicaInstancia;
    }

	@Override
	public void registrarPlayList(PlayList playList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarPlayList(PlayList playList) {
		Entidad ePlayList = servPersistencia.recuperarEntidad(playList.getCodigo());
		
		servPersistencia.borrarEntidad(ePlayList);
		
	}

	@Override
	public void modificarPlayList(PlayList playList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PlayList recuperarPlayList(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlayList> recuperarTodasListas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void borrarTodasPlayList() {
		// TODO Auto-generated method stub
		
	}


}

