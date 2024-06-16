package umu.tds.persistencia;

public class TDSFactoriaDAO extends FactoriaDAO {
	public TDSFactoriaDAO () {
	}
	
	@Override
	public IAdaptadorUsuarioDAO getUsuarioDAO() {
		return AdaptadorUsuarioTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorCancionDAO getCancionDAO() {
		return AdaptadorCancionTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorEstiloMusicalDAO getEstiloMusicalDAO() {
		return AdaptadorEstiloMusicalTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorPlayListDAO getPlayListDAO() {
		return AdaptadorPlayListTDS.getUnicaInstancia();
	}



}
