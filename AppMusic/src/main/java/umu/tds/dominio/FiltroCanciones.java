package umu.tds.dominio;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroCanciones {
	
	public List<Cancion> buscarCanciones(List<Cancion> canciones, String titulo, String interprete, String estilo) {
        return canciones.stream()
                .filter(c -> (titulo == null || c.getTitulo().toLowerCase().contains(titulo.toLowerCase())))
                .filter(c -> (interprete == null || c.getInterprete().toLowerCase().contains(interprete.toLowerCase())))
                .filter(c -> (estilo == null || c.getEstilo().contains(estilo.toLowerCase())))
                .collect(Collectors.toList());
    }
}
