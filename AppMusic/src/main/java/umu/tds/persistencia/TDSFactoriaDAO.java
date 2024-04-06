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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IAdaptadorEstiloMusicalDAO getEstiloMusicalDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IAdaptadorPlayListDAO getPlayListDAO() {
		// TODO Auto-generated method stub
		return null;
	}



}
