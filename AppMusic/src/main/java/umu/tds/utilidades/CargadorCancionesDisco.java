package umu.tds.utilidades;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import umu.tds.dominio.Cancion;
import umu.tds.dominio.repositorios.RepositorioCanciones;

public enum CargadorCancionesDisco {
	INSTANCE;
	
	private String carpetaCanciones = "/canciones"; //carpeta con canciones en resources
	
	public boolean cargarCanciones() throws Exception {
		URL resourceURL = getClass().getResource(carpetaCanciones);
		Path path = Paths.get(resourceURL.toURI());

        if (hayCanciones(path)){ 
			  printCanciones(path);
			  crearObjetosCancion(path);
	          return true;
        }
	    else return false;
	}
	    
	private boolean hayCanciones(Path path) {
	    try {
	          return Files.walk(path)
	                      .anyMatch(f -> !Files.isDirectory(f));
	    } catch (IOException e) {
	            System.err.println("Caught IOException: " + e.getMessage());
	            return false; // Manejo de errores
	    }
	}

	private void printCanciones(Path path){
		try {   
			List<Path> dirs = Files.list(path).collect(toList());
		    for(Path dir:dirs) {
		    	System.out.println("Categoria: "+dir.toFile().getName());
		    	for (Path file:Files.list(dir).collect(toList())){
		    		System.out.println("Disco: "+ file.toFile().getName());
		    	}
		    }
		 } catch(IOException e){System.err.println("Caught IOException: " + e.getMessage());}
	}
	private void crearObjetosCancion(Path path){
		// se crea objeto Categoria, esto no serÃ­a necesario en el caso prÃ¡ctico propuesto.
		try {   
			List<Path> dirs = Files.list(path).collect(toList());
		    for(Path dir:dirs) {
		    	String estilo = dir.toFile().getName();
		    	System.out.println(estilo);
		    	for (Path file:Files.list(dir).collect(toList())){
		    		String nombreFichero = file.toFile().getName();
		    		String rutaFichero = estilo + "/"+ nombreFichero;
		    		String[] partesNombre = nombreFichero.split("-");
		    		String interprete = partesNombre[0];
		    		String titulo = partesNombre[1].replace(".mp3", "");
		    		Cancion cancion = new Cancion(titulo, rutaFichero, interprete, estilo);
		    		RepositorioCanciones.getUnicaInstancia().addCancion(cancion);
		    	}
		    }
		 } catch(IOException e){System.err.println("Caught IOException: " + e.getMessage());}
	}
}
