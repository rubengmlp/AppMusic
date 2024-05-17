package tds.CargadorCanciones;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.Vector;


public class CargadorCanciones implements Serializable {

	private static final long serialVersionUID = 1L;
	private Canciones archivoCanciones; // propiedad ligada
	private String nombre; // identificador del cargador

	private Vector<ICargadoListener> oyentes; 
	
	/**
	 * Constructor
	 */
	public CargadorCanciones() {
		archivoCanciones = new Canciones();
		oyentes = new Vector<>();
	}
	
	
	// Getters y setters
	

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void addOyente(ICargadoListener oyente) {
		this.oyentes.add(oyente);
	}
	
	public void removeOyente(ICargadoListener oyente) {
		this.oyentes.removeElement(oyente);
	}


	public Canciones getArchivoCanciones() {
		return archivoCanciones;
	}
	
	public void setArchivoCanciones (String fichero) throws URISyntaxException {
		Canciones canciones = MapperCancionesXMLtoJava.cargarCanciones(fichero);
		this.archivoCanciones = canciones;
		
		// Notificar oyentes de que hay nueva carga
		CancionesEvent cEvent = new CancionesEvent(this, canciones);
		notificarOyentes(cEvent);
	}
	
	
	/**
 	 * Notificar evento en el cargador a los oyentes
 	 * @param e Evento a notificar
 	 */
 	@SuppressWarnings("unchecked")
 	public void notificarOyentes(CancionesEvent cEvent) {
 		Vector<ICargadoListener> copia;
 		synchronized (this) {
 			copia = (Vector<ICargadoListener>) oyentes.clone();
 		}
 		copia.forEach(o -> o.enteradoCarga(cEvent)); 
 	}

}
