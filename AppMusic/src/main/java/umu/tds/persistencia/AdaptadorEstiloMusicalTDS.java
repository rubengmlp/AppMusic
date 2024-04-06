package umu.tds.persistencia;

import java.util.List;

import beans.Entidad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.dominio.EstiloMusical;

public class AdaptadorEstiloMusicalTDS implements IAdaptadorEstiloMusicalDAO {

    private static AdaptadorEstiloMusicalTDS unicaInstancia;
    private static ServicioPersistencia servPersistencia;

    // Constructor público
    public AdaptadorEstiloMusicalTDS() {
        servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }

    public static AdaptadorEstiloMusicalTDS getUnicaInstancia() {
        if (unicaInstancia == null)
            return new AdaptadorEstiloMusicalTDS();
        else
            return unicaInstancia;
    }

	@Override
	public void registrarEstiloMusical(EstiloMusical estiloMusical) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarEstiloMusical(EstiloMusical estiloMusical) {
		Entidad eEstiloMusical = servPersistencia.recuperarEntidad(estiloMusical.getCodigo());
		
		servPersistencia.borrarEntidad(eEstiloMusical);
		
	}

	@Override
	public EstiloMusical recuperarEstiloMusical(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EstiloMusical> recuperarTodasEstiloMusicals() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void borrarTodasEstiloMusicals() {
		// TODO Auto-generated method stub
		
	}


}
