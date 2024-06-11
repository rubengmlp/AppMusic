package umu.tds.dominio;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroCanciones {
	
	public List<Cancion> buscarCanciones(List<Cancion> canciones, String titulo, String interprete, String estiloMusical, Boolean esFavorita) {
        return canciones.stream()
                .filter(c -> (titulo == null || c.getTitulo().toLowerCase().contains(titulo.toLowerCase())))
                .filter(c -> (interprete == null || c.getInterprete().toLowerCase().contains(interprete.toLowerCase())))
                .filter(c -> (estiloMusical == null || c.getEstilosMusicales().contains(estiloMusical.toLowerCase())))
                .filter(c -> (esFavorita == null || c.isEsFavorita() == esFavorita))
                .collect(Collectors.toList());
    }

}
