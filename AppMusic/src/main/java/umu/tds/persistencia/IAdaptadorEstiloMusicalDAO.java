package umu.tds.persistencia;

import java.util.List;

import umu.tds.dominio.EstiloMusical;

public interface IAdaptadorEstiloMusicalDAO {
	
	public void registrarEstiloMusical(EstiloMusical EstiloMusical);
	public void borrarEstiloMusical(EstiloMusical EstiloMusical);
	public EstiloMusical recuperarEstiloMusical(int codigo);
	public List<EstiloMusical> recuperarTodasEstiloMusicals();
	public void borrarTodasEstiloMusicals();
	
}	