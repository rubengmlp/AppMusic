package umu.tds.servicio;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import umu.tds.dominio.Cancion;
import umu.tds.dominio.RepositorioCanciones;

public enum CargadorCanciones {
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
			List<Path> dirs = Files.list(path).collect(Collectors.toList());
		    for(Path dir:dirs) {
		    	System.out.println("Categoria: "+dir.toFile().getName());
		    	for (Path file:Files.list(dir).collect(Collectors.toList())){
		    		System.out.println("Disco: "+ file.toFile().getName());
		    	}
		    }
		 } catch(IOException e){System.err.println("Caught IOException: " + e.getMessage());}
	}
	private void crearObjetosCancion(Path path){
		// se crea objeto Categoria, esto no serÃ­a necesario en el caso prÃ¡ctico propuesto.
		try {   
			List<Path> dirs = Files.list(path).collect(Collectors.toList());
		    for(Path dir:dirs) {
		    	String nombreCategoria = dir.toFile().getName();
		    	for (Path file:Files.list(dir).collect(Collectors.toList())){
		    		String nombreFichero = file.toFile().getName();
		    		String rutaFichero = nombreCategoria + "/"+ nombreFichero;
		    		String[] partesNombre = nombreFichero.split("-");
		    		String interprete = partesNombre[0];
		    		String titulo = partesNombre[1];
		    		//CatalogoInterpretes.getUnicaInstancia().addAutor(interprete);
		    		Cancion cancion = new Cancion(titulo, interprete, rutaFichero, categoria);
		    		RepositorioCanciones.getUnicaInstancia().addCancion(cancion);
		    		categoria.addCancion(cancion);
		    	}
		    }
		 } catch(IOException e){System.err.println("Caught IOException: " + e.getMessage());}
	}
}