package umu.tds.persistencia;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.PlayList;
import umu.tds.dominio.Usuario;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO {
	private static ServicioPersistencia servPersistencia;
	private SimpleDateFormat dateFormat;
	private static AdaptadorUsuarioTDS unicaInstancia;
	
	public static AdaptadorUsuarioTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			return new AdaptadorUsuarioTDS();
		else
			return unicaInstancia;
	}
	
	public AdaptadorUsuarioTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy")
	}

	@Override
	public void registrarUsuario(Usuario usuario) {
	    // Crear una nueva entidad para el usuario
		Entidad entidadUsuario = null;
		//Si la entidad esta registrada no la registra de nuevo
		try {
			entidadUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		} catch (NullPointerException e) {}
		if (entidadUsuario != null) return;
		
		AdaptadorPlayListTDS adaptadorLista = AdaptadorPlayListTDS.getUnicaInstancia();
		for(PlayList p: usuario.getPlayLists()) {
			adaptadorLista.registrarPlayList(p);
		}
		//adaptadorLista.registrarPlayList(usuario.getCancionesRecientes());
		
		entidadUsuario = new Entidad();
	    entidadUsuario.setNombre("usuario");
	    
	    // Crear propiedades para el usuario y asignarles valores
	    entidadUsuario.setPropiedades(Arrays.asList(
	        new Propiedad("nombre", usuario.getNombre()),
	        new Propiedad("apellidos", usuario.getApellido()),
	        new Propiedad("fechaNacimiento",dateFormat.format(usuario.getFecha())),
			new Propiedad("email", usuario.getEmail()),
			new Propiedad("contraseña", usuario.getContrasena()),
	        new Propiedad("premium", String.valueOf(usuario.isPremium())),
	        new Propiedad("playlists", obtenerCodigosPlayLists(usuario.getPlayLists())),
	        new Propiedad("cancionesRecientes", obtenerCodigosCanciones(usuario.getCancionesRecientes()))
	    ));
	    
	    // Registrar la entidad en el servicio de persistencia
	    entidadUsuario = servPersistencia.registrarEntidad(entidadUsuario);
	    
	    // Asignar el código generado por el servicio de persistencia al usuario
	    usuario.setCodigo(entidadUsuario.getId());
	}


	@Override
	public void borrarUsuario(Usuario usuario) {
		AdaptadorPlayListTDS adaptadorP = AdaptadorPlayListTDS.getUnicaInstancia();
		for(PlayList p: usuario.getPlayLists()) {
			adaptadorP.borrarPlayList(p);
		}
		//adaptadorP.borrarPlayList(usuario.getCancionesRecientes());
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		
		servPersistencia.borrarEntidad(eUsuario);
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
	    // Obtener la entidad del usuario desde la base de datos
	    Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());

	    // Actualizar las propiedades de la entidad con la información del usuario
	    for (Propiedad prop : eUsuario.getPropiedades()) {
	        if (prop.getNombre().equals("nombre")) {
	            prop.setValor(usuario.getNombre());
	        } else if (prop.getNombre().equals("premium")) {
	            prop.setValor(String.valueOf(usuario.isPremium()));
	        } else if (prop.getNombre().equals("playLists")) {
	            String codigosPlayLists = obtenerCodigosPlayLists(usuario.getPlayLists());
	            prop.setValor(codigosPlayLists);
	        } else if (prop.getNombre().equals("cancionesRecientes")) {
	            String codigosCancionesRecientes = obtenerCodigosCanciones(usuario.getCancionesRecientes());
	            prop.setValor(codigosCancionesRecientes);
	        }
	        // Modificar la propiedad en la base de datos
	        servPersistencia.modificarPropiedad(prop);
	    }
	}

	@Override
	public Usuario recuperarUsuario(int codigo) {
	    // Si la entidad está en el pool, la devuelve directamente
	    if (PoolDAO.getUnicaInstancia().contiene(codigo))
	        return (Usuario) PoolDAO.getUnicaInstancia().getObjeto(codigo);

	    // Si no, la recupera de la base de datos
	    Entidad eUsuario;
	    List<PlayList> playLists;
	    List<Cancion> cancionesRecientes;

	    // Recuperar entidad
	    eUsuario = servPersistencia.recuperarEntidad(codigo);

	    // Recuperar propiedades que no son objetos
	    String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
	    String apellido = servPersistencia.recuperarPropiedadEntidad(eUsuario, "apellidos");
		Date fecha = null;
		try {
			fecha = dateFormat.parse(servPersistencia.recuperarPropiedadEntidad(eUsuario, "fechaNacimiento"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		//String id = servPersistencia.recuperarPropiedadEntidad(eUsuario, "usuID");
		String contrasena = servPersistencia.recuperarPropiedadEntidad(eUsuario, "password");
		//String filtro = servPersistencia.recuperarPropiedadEntidad(eUsuario, "filtro");
		boolean premium = Boolean.valueOf(servPersistencia.recuperarPropiedadEntidad(eUsuario, "premium"));
		AdaptadorPlayListTDS adaptadorP = AdaptadorPlayListTDS.getUnicaInstancia();
		//PlayList recientes = adaptadorP.recuperarPlayList(Integer.valueOf(servPersistencia.recuperarPropiedadEntidad(eUsuario, "recientes")));

	    Usuario usuario = new Usuario(nombre, apellido, email, fecha, contrasena);
	    usuario.setCodigo(codigo);
	    usuario.setPremium(premium);

	    // IMPORTANTE: añadir el usuario al pool antes de llamar a otros adaptadores
	    PoolDAO.getUnicaInstancia().addObjeto(codigo, usuario);

	    // Recuperar listas de reproducción (playlists)
	    String codigosPlaylists = servPersistencia.recuperarPropiedadEntidad(eUsuario, "UsuarioPlaylist");
	    playLists = recuperarPlaylistsDesdeCodigos(codigosPlaylists);
	    usuario.setPlayLists(playLists);

	    // Recuperar canciones recientes
	    String codigosCancionesRecientes = servPersistencia.recuperarPropiedadEntidad(eUsuario, "UsuarioCancion");
	    cancionesRecientes = recuperarCancionesDesdeCodigos(codigosCancionesRecientes);
	    usuario.setCancionesRecientes(cancionesRecientes);

	    return usuario;
	}


	@Override
	public List<Usuario> recuperarTodosUsuarios() {
	    List<Usuario> usuarios = new ArrayList<>();

	    // Recuperar todas las entidades de usuario de la base de datos
	    List<Entidad> entidadesUsuarios = servPersistencia.recuperarEntidades("Usuario");

	    // Recorrer cada entidad de usuario para convertirla en un objeto Usuario
	    for (Entidad entidadUsuario : entidadesUsuarios) {
	        int codigoUsuario = entidadUsuario.getId();

	        // Verificar si el usuario ya ha sido recuperado previamente (evitar duplicados)
	        if (!PoolDAO.getUnicaInstancia().contiene(codigoUsuario)) {
	            Usuario usuario = recuperarUsuario(codigoUsuario);
	            usuarios.add(usuario);
	        }
	    }

	    return usuarios;
	}

	
	// -------------------Funciones auxiliares-----------------------------
	
	// Función auxiliar para obtener una cadena de códigos de listas de reproducción
	private String obtenerCodigosPlayLists(List<PlayList> playLists) {
	    StringBuilder codigos = new StringBuilder();
	    for (PlayList playList : playLists) {
	        codigos.append(playList.getCodigo()).append(" ");
	    }
	    return codigos.toString().trim();
	}

	// Función auxiliar para obtener una cadena de códigos de canciones
	private String obtenerCodigosCanciones(List<Cancion> canciones) {
	    StringBuilder codigos = new StringBuilder();
	    for (Cancion cancion : canciones) {
	        codigos.append(cancion.getCodigo()).append(" ");
	    }
	    return codigos.toString().trim();
	}
	
	private List<PlayList> recuperarPlaylistsDesdeCodigos(String codigos) {
	    List<PlayList> playlists = new ArrayList<>();
	    StringTokenizer tokenizer = new StringTokenizer(codigos, " ");
	    AdaptadorPlayListTDS adaptadorPlaylist = AdaptadorPlayListTDS.getUnicaInstancia();
	    while (tokenizer.hasMoreTokens()) {
	        int codigoPlaylist = Integer.parseInt(tokenizer.nextToken());
	        playlists.add(adaptadorPlaylist.recuperarPlayList(codigoPlaylist));
	    }
	    return playlists;
	}

	private List<Cancion> recuperarCancionesDesdeCodigos(String codigos) {
	    List<Cancion> canciones = new ArrayList<>();
	    StringTokenizer tokenizer = new StringTokenizer(codigos, " ");
	    AdaptadorCancionTDS adaptadorCancion = AdaptadorCancionTDS.getUnicaInstancia();
	    while (tokenizer.hasMoreTokens()) {
	        int codigoCancion = Integer.parseInt(tokenizer.nextToken());
	        canciones.add(adaptadorCancion.recuperarCancion(codigoCancion));
	    }
	    return canciones;
	}



}
