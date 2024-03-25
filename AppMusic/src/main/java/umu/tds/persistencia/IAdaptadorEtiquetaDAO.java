package umu.tds.persistencia;

import java.util.List;

import umu.tds.dominio.Etiqueta;

public interface IAdaptadorEtiquetaDAO {
	
	public void registrarEtiqueta(Etiqueta etiqueta);
	public void borrarEtiqueta(Etiqueta etiqueta);
	public Etiqueta recuperarEtiqueta(int codigo);
	public List<Etiqueta> recuperarTodasEtiquetas();
	public void borrarTodasEtiquetas();
	
}	