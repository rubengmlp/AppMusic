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
		Entidad eEstilo = null;
		
		try {
			eEstilo = servPersistencia.recuperarEntidad(estiloMusical.getCodigo());
		} catch (NullPointerException e) {}
		if (eEstilo != null) return;
		
		AdaptadorCancionTDS adaptadorCancion = AdaptadorCancionTDS.getUnicaInstancia();
		
		for (Cancion c : estiloMusical.getCanciones())
			adaptadorCancion.registrarCancion(c);
		
		eEstilo = new Entidad();
		eEstilo.setNombre("estilo");
		eEstilo.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("nombre", estiloMusical.getNombre()),
		        new Propiedad("canciones", obtenerCodigosCanciones(estiloMusical.getCanciones())))));
		
		eEstilo = servPersistencia.registrarEntidad(eEstilo);
		
		estiloMusical.setCodigo(eEstilo.getId());
	}

	@Override
	public void borrarEstiloMusical(EstiloMusical estiloMusical) {
		Entidad eEstiloMusical = servPersistencia.recuperarEntidad(estiloMusical.getCodigo());
		
		servPersistencia.borrarEntidad(eEstiloMusical);
		
	}
	
	@Override
	public void modificarEstiloMusical(EstiloMusical estiloMusical) {
	    Entidad eEstilo = servPersistencia.recuperarEntidad(estiloMusical.getCodigo());

	    for (Propiedad prop : eEstilo.getPropiedades()) {
	        if (prop.getNombre().equals("nombre")) {
	            prop.setValor(estiloMusical.getNombre());
	        } else if (prop.getNombre().equals("canciones")) {
	            String codigosCanciones = obtenerCodigosCanciones(estiloMusical.getCanciones());
	            prop.setValor(codigosCanciones);
	        }
	        servPersistencia.modificarPropiedad(prop);
	    }
	}


	public EstiloMusical recuperarEstiloMusical(int codigo) {
	    // Si la entidad está en el pool, la devuelve directamente
	    if (PoolDAO.getUnicaInstancia().contiene(codigo))
	        return (EstiloMusical) PoolDAO.getUnicaInstancia().getObjeto(codigo);

	    // Si no, la recupera de la base de datos
	    Entidad eEstilo;
	    String nombre;
	    List<Cancion> canciones = new LinkedList<>();

	    // Recuperar entidad
	    eEstilo = servPersistencia.recuperarEntidad(codigo);

	    // Recuperar propiedades que no son objetos
	    nombre = servPersistencia.recuperarPropiedadEntidad(eEstilo, "nombre");

	    EstiloMusical estiloMusical = new EstiloMusical(nombre);
	    estiloMusical.setCodigo(codigo);

	    // IMPORTANTE: añadir el estiloMusical al pool antes de llamar a otros adaptadores
	    PoolDAO.getUnicaInstancia().addObjeto(codigo, estiloMusical);

	    // Recuperar propiedades que son objetos llamando a adaptadores de canciones
	    canciones = obtenerCancionesDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eEstilo, "canciones"));

	    for (Cancion cancion : canciones)
	        estiloMusical.getCanciones().add(cancion);

	    return estiloMusical;
	}

	@Override
	public List<EstiloMusical> recuperarTodosEstilosMusicales() {
	    List<Entidad> eEstilos = servPersistencia.recuperarEntidades("estilo");
	    List<EstiloMusical> estilosMusicales = new LinkedList<>();

	    for (Entidad eEstilo : eEstilos) {
	        int codigo = eEstilo.getId();
	        // Si la entidad está en el pool, la devuelve directamente
	        if (PoolDAO.getUnicaInstancia().contiene(codigo)) {
	            estilosMusicales.add((EstiloMusical) PoolDAO.getUnicaInstancia().getObjeto(codigo));
	        } else {
	            EstiloMusical estiloMusical = recuperarEstiloMusical(codigo);
	            estilosMusicales.add(estiloMusical);
	        }
	    }
	    return estilosMusicales;
	}

	// -------------------Funciones auxiliares-----------------------------
	
	private String obtenerCodigosCanciones(List<Cancion> canciones) {
	    StringBuilder codigos = new StringBuilder();
	    for (Cancion cancion : canciones) {
	        codigos.append(cancion.getCodigo()).append(" ");
	    }
	    return codigos.toString().trim();
	}
	
	private List<Cancion> obtenerCancionesDesdeCodigos(String codigos) {
	    List<Cancion> canciones = new LinkedList<>();
	    StringTokenizer tokenizer = new StringTokenizer(codigos, " ");
	    AdaptadorCancionTDS adaptadorCancion = AdaptadorCancionTDS.getUnicaInstancia();

	    while (tokenizer.hasMoreTokens()) {
	        int codigoCancion = Integer.parseInt(tokenizer.nextToken());
	        Cancion cancion = adaptadorCancion.recuperarCancion(codigoCancion);
	        canciones.add(cancion);
	    }

	    return canciones;
	}


}
