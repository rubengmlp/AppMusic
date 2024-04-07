package umu.tds.persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.PlayList;

public class AdaptadorPlayListTDS implements IAdaptadorPlayListDAO {

    private static AdaptadorPlayListTDS unicaInstancia;
    private static ServicioPersistencia servPersistencia;

    private AdaptadorPlayListTDS() {
        // Constructor privado para evitar instanciación externa
        servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }

    public static AdaptadorPlayListTDS getUnicaInstancia() {
        if (unicaInstancia == null)
            return new AdaptadorPlayListTDS();
        else
            return unicaInstancia;
    }

    @Override
    public void registrarPlayList(PlayList playList) {
        Entidad entidadPlayList = null;

        // Verificar si la lista de reproducción ya está registrada
        try {
            entidadPlayList = servPersistencia.recuperarEntidad(playList.getCodigo());
        } catch (NullPointerException e) {}

        // Si la lista de reproducción ya está registrada, no la registramos de nuevo
        if (entidadPlayList != null) return;

        // Crear la entidad de lista de reproducción
        entidadPlayList = new Entidad();
        entidadPlayList.setNombre("playlist");
        entidadPlayList.setPropiedades(new ArrayList<Propiedad>(
                Arrays.asList(new Propiedad("nombre", playList.getNombre()),
                              new Propiedad("canciones", obtenerCodigosCanciones(playList.getCanciones())))));

        // Registrar la entidad de lista de reproducción en la base de datos
        entidadPlayList = servPersistencia.registrarEntidad(entidadPlayList);
        // Asignar el identificador único generado por el servicio de persistencia
        playList.setCodigo(entidadPlayList.getId());
    }

	@Override
	public void borrarPlayList(PlayList playList) {
		Entidad ePlayList = servPersistencia.recuperarEntidad(playList.getCodigo());
		
		servPersistencia.borrarEntidad(ePlayList);
		
	}

	@Override
	public void modificarPlayList(PlayList playList) {
	    // Recuperar la entidad de la lista de reproducción
	    Entidad entidadPlayList = servPersistencia.recuperarEntidad(playList.getCodigo());

	    for (Propiedad prop : entidadPlayList.getPropiedades()) {
	        if (prop.getNombre().equals("nombre")) {
	            prop.setValor(playList.getNombre());
	        } else if (prop.getNombre().equals("canciones")) {
	            // Convertir la lista de canciones a una cadena de códigos separada por espacios
	            String codigosCanciones = obtenerCodigosCanciones(playList.getCanciones());
	            prop.setValor(codigosCanciones);
	        }
	        // Actualizar la propiedad en la base de datos
	        servPersistencia.modificarPropiedad(prop);
	    }
	}


	@Override
	public PlayList recuperarPlayList(int codigo) {
	    // Recuperar la entidad de la lista de reproducción
	    Entidad entidadPlayList = servPersistencia.recuperarEntidad(codigo);
	    
	    // Verificar si la entidad existe
	    if (entidadPlayList == null) {
	        return null; // Si no existe, devolvemos null
	    }
	    
	    // Obtener el nombre de la lista de reproducción
	    String nombre = servPersistencia.recuperarPropiedadEntidad(entidadPlayList, "nombre");
	    
	    // Obtener los códigos de las canciones de la lista de reproducción como una cadena
	    String codigosCancionesString = servPersistencia.recuperarPropiedadEntidad(entidadPlayList, "canciones");
	    
	    // Convertir la cadena de códigos de canciones a una lista de códigos
	    List<Integer> codigosCanciones = obtenerCodigosCancionesFromString(codigosCancionesString);
	    
	    // Recuperar las canciones asociadas a los códigos de la lista
	    List<Cancion> canciones = recuperarCancionesFromCodigos(codigosCanciones);
	    
	    // Crear y devolver la lista de reproducción
	    PlayList playList = new PlayList(nombre, canciones);
	    playList.setCodigo(codigo);
	    return playList;
	}


	@Override
	public List<PlayList> recuperarTodasPlayList() {
	    // Recuperar todas las entidades de lista de reproducción de la base de datos
	    List<Entidad> entidadesPlayList = servPersistencia.recuperarEntidades("playlist");
	    
	    // Crear una lista para almacenar las listas de reproducción recuperadas
	    List<PlayList> listasReproduccion = new ArrayList<>();
	    
	    // Iterar sobre todas las entidades recuperadas
	    for (Entidad entidadPlayList : entidadesPlayList) {
	        // Obtener el código de la lista de reproducción
	        int codigo = entidadPlayList.getId();
	        
	        // Recuperar la lista de reproducción utilizando el método recuperarPlayList
	        PlayList playList = recuperarPlayList(codigo);
	        
	        // Si la lista de reproducción se recuperó correctamente, agregarla a la lista
	        if (playList != null) {
	            listasReproduccion.add(playList);
	        }
	    }
	    
	    // Devolver la lista de todas las listas de reproducción recuperadas
	    return listasReproduccion;
	}

	// -------------------Funciones auxiliares-----------------------------
	
	// Función auxiliar para obtener los códigos de las canciones de la lista de reproducción
	private String obtenerCodigosCanciones(LinkedList<Cancion> canciones) {
	    StringBuilder stringBuilder = new StringBuilder();
	    for (Cancion cancion : canciones) {
	        stringBuilder.append(cancion.getCodigo()).append(" ");
	    }
	    return stringBuilder.toString().trim();
	}
	
	// Función auxiliar para convertir una cadena de códigos de canciones a una lista de enteros
	private List<Integer> obtenerCodigosCancionesFromString(String codigosCancionesString) {
	    List<Integer> codigosCanciones = new ArrayList<>();
	    StringTokenizer tokenizer = new StringTokenizer(codigosCancionesString, " ");
	    while (tokenizer.hasMoreTokens()) {
	        codigosCanciones.add(Integer.parseInt(tokenizer.nextToken()));
	    }
	    return codigosCanciones;
	}
	
	// Función auxiliar para recuperar las canciones a partir de los códigos
	private List<Cancion> recuperarCancionesFromCodigos(List<Integer> codigosCanciones) {
	    List<Cancion> canciones = new ArrayList<>();
	    AdaptadorCancionTDS adaptadorCancion = AdaptadorCancionTDS.getUnicaInstancia();
	    for (int codigoCancion : codigosCanciones) {
	        Cancion cancion = adaptadorCancion.recuperarCancion(codigoCancion);
	        if (cancion != null) {
	            canciones.add(cancion);
	        }
	    }
	    return canciones;
	}

}

