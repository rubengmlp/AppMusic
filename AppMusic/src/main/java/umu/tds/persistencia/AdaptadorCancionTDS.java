package umu.tds.persistencia;

import java.util.List;

import umu.tds.persistencia.AdaptadorCancionTDS;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.dominio.Cancion;

public class AdaptadorCancionTDS implements IAdaptadorCancionDAO {
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorCancionTDS unicaInstancia = null;

	public static AdaptadorCancionTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorCancionTDS();
		else
			return unicaInstancia;
	}

	private AdaptadorCancionTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	@Override
	public void registrarCancion(Cancion cancion) {
		// TODO Auto-generated method stub

	}

	@Override
	public void borrarCancion(Cancion cancion) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificarCancion(Cancion cancion) {
		// TODO Auto-generated method stub

	}

	@Override
	public Cancion recuperarCancion(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cancion> recuperarTodasCanciones() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void borrarTodasCanciones() {
		// TODO Auto-generated method stub

	}

}
