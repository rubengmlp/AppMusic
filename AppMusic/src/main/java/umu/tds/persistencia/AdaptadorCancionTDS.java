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

public class AdaptadorCancionTDS implements IAdaptadorCancionDAO {
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorCancionTDS unicaInstancia = null;

	public static AdaptadorCancionTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorCancionTDS();
		else
			return unicaInstancia;
	}

	private AdaptadorCancionTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	@Override
	public void registrarCancion(Cancion cancion) {
	    Entidad eCancion = null;
	    
	    // Si la entidad está registrada, no la registra de nuevo
	    try {
	        eCancion = servPersistencia.recuperarEntidad(cancion.getCodigo());
	    } catch (NullPointerException e) {}
	    if (eCancion != null) return;

	    // Registrar primero los atributos que son objetos (si los hay)
	    // En este caso, no hay atributos que sean objetos, por lo que no es necesario hacer nada aquí

	    // Crear entidad Cancion
	    eCancion = new Entidad();
	    eCancion.setNombre("cancion");
	    eCancion.setPropiedades(new ArrayList<Propiedad>(
	            Arrays.asList(new Propiedad("titulo", cancion.getTitulo()),
	                          new Propiedad("numRep", String.valueOf(cancion.getNumRep())),
	                          new Propiedad("url", cancion.getUrl()),
	                          new Propiedad("interprete", cancion.getInterprete()),
	                          new Propiedad("estilo", cancion.getEstilo()))));

	    // Registrar entidad canción
	    eCancion = servPersistencia.registrarEntidad(eCancion);
	    // Asignar identificador único
	    // Se aprovecha el que genera el servicio de persistencia
	    cancion.setCodigo(eCancion.getId());
	}

	@Override
	public void borrarCancion(Cancion cancion) {
		Entidad eCancion = servPersistencia.recuperarEntidad(cancion.getCodigo());
		
		servPersistencia.borrarEntidad(eCancion);

	}

	@Override
	public void modificarCancion(Cancion cancion) {
	    // Recuperar la entidad de la canción
	    Entidad entidadCancion = servPersistencia.recuperarEntidad(cancion.getCodigo());

	    for (Propiedad prop : entidadCancion.getPropiedades()) {
	        if (prop.getNombre().equals("titulo")) {
	            prop.setValor(cancion.getTitulo());
	        } else if (prop.getNombre().equals("numRep")) {
	            prop.setValor(Integer.toString(cancion.getNumRep()));
	        } else if (prop.getNombre().equals("url")) {
	            prop.setValor(cancion.getUrl());
	        } else if (prop.getNombre().equals("interprete")) {
	        	prop.setValor(cancion.getInterprete());
	        } else if (prop.getNombre().equals("estilo")) {
	            prop.setValor(cancion.getEstilo());
	        }
	        // Actualizar la propiedad en la base de datos
	        servPersistencia.modificarPropiedad(prop);
	    }
	}

	@Override
	public Cancion recuperarCancion(int codigo) {
	    // Si la entidad está en el pool, la devuelve directamente
	    if (PoolDAO.getUnicaInstancia().contiene(codigo))
	        return (Cancion) PoolDAO.getUnicaInstancia().getObjeto(codigo);

	    // Si no, la recupera de la base de datos
	    Entidad eCancion;
	    String titulo;
	    int numRep;
	    String url;
	    String interprete;
	    String estilo;

	    // Recuperar entidad
	    eCancion = servPersistencia.recuperarEntidad(codigo);

	    // Recuperar propiedades que no son objetos
	    titulo = servPersistencia.recuperarPropiedadEntidad(eCancion, "titulo");
	    numRep = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eCancion, "numRep"));
	    url = servPersistencia.recuperarPropiedadEntidad(eCancion, "url");
	    interprete = servPersistencia.recuperarPropiedadEntidad(eCancion, "interprete");
	    estilo = servPersistencia.recuperarPropiedadEntidad(eCancion, "estilo");

	    Cancion cancion = new Cancion(titulo, numRep, url, interprete, estilo);
	    cancion.setCodigo(codigo);

	    // IMPORTANTE: añadir la canción al pool antes de devolverla
	    PoolDAO.getUnicaInstancia().addObjeto(codigo, cancion);

	    return cancion;
	}

	@Override
	public List<Cancion> recuperarTodasCanciones() {
	    List<Entidad> entidadesCanciones = servPersistencia.recuperarEntidades("cancion");
	    List<Cancion> canciones = new ArrayList<>();

	    for (Entidad entidadCancion : entidadesCanciones) {
	        int codigoCancion = entidadCancion.getId();
	        // Si la canción ya está en el pool, la devuelve directamente
	        if (PoolDAO.getUnicaInstancia().contiene(codigoCancion)) {
	            canciones.add((Cancion) PoolDAO.getUnicaInstancia().getObjeto(codigoCancion));
	        } else {
	            Cancion cancion = recuperarCancion(codigoCancion);
	            canciones.add(cancion);
	        }
	    }

	    return canciones;
	}


}
