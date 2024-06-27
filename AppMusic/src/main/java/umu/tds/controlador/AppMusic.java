package umu.tds.controlador;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tds.CargadorCanciones.CancionesEvent;
import tds.CargadorCanciones.CargadorCanciones;
import tds.CargadorCanciones.ICargadoListener;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.PlayList;
import umu.tds.dominio.Usuario;
import umu.tds.dominio.repositorios.BDException;
import umu.tds.dominio.repositorios.RepositorioCanciones;
import umu.tds.dominio.repositorios.RepositorioUsuarios;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;
import umu.tds.persistencia.IAdaptadorCancionDAO;
import umu.tds.persistencia.IAdaptadorEstiloMusicalDAO;
import umu.tds.persistencia.IAdaptadorPlayListDAO;
import umu.tds.persistencia.IAdaptadorUsuarioDAO;
import umu.tds.utilidades.CargadorCancionesDisco;
import umu.tds.utilidades.Player;

public class AppMusic implements ICargadoListener {
	private static AppMusic unicaInstancia;

	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorCancionDAO adaptadorCancion;
	private IAdaptadorEstiloMusicalDAO adaptadorEstiloMusical;
	private IAdaptadorPlayListDAO adaptadorPlayList;

	private RepositorioCanciones repositorioCanciones;
	private RepositorioUsuarios repositorioUsuarios;

	private static Usuario usuarioActual;
	private Player reproductor;

	private AppMusic() throws DAOException, BDException {
		try {
			inicializarAdaptadores();
			inicializarRepositorios();
			reproductor = new Player();
		} catch (DAOException e) {
			throw e;
		} catch (Exception e) {
			throw new BDException(e.getMessage());
		}
	}
	
	public static AppMusic getUnicaInstancia() throws DAOException, BDException {
		if (unicaInstancia == null)
			unicaInstancia = new AppMusic();
		return unicaInstancia;
	}

	public static Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public static void setUsuarioActual(Usuario usuarioActual) {
		AppMusic.usuarioActual = usuarioActual;
	}

	private void inicializarAdaptadores() throws DAOException {
		FactoriaDAO factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		adaptadorUsuario = factoria.getUsuarioDAO();
		adaptadorCancion = factoria.getCancionDAO();
		adaptadorPlayList = factoria.getPlayListDAO();
	}

	private void inicializarRepositorios() throws BDException {
		repositorioUsuarios = RepositorioUsuarios.getUnicaInstancia();
		repositorioCanciones = RepositorioCanciones.getUnicaInstancia();
	}

	// USUARIOS

	public boolean isRegistrado(String username) {
		return repositorioUsuarios.getUsuario(username) != null;
	}

	public boolean registro(String username, String nombre, String apellidos, String fechaNacimiento, String email,
			LocalDate fecha, String contrasena) {

		Usuario usuario = new Usuario(username, nombre, apellidos, email, fecha, contrasena);
		adaptadorUsuario.registrarUsuario(usuario);
		repositorioUsuarios.addUsuario(usuario);
		return true;
	}

	public void eliminarUsuario(Usuario usuario) {
		adaptadorUsuario.borrarUsuario(usuario);
		repositorioUsuarios.removeUsuario(usuario);
	}

	public boolean login(String username, String contrasena) {
		if (!isRegistrado(username))
			return false;
		// En caso contrario, se recupera el usuario y se comprueba la contraseña
		Usuario usuario = repositorioUsuarios.getUsuario(username);
		if (usuario.getContrasena().equals(contrasena)) {
			setUsuarioActual(usuario);
			return true;
		}
		return false;
	}

	public List<PlayList> getPlayListsUsuario() {
		List<PlayList> playLists = new ArrayList<PlayList>();
		if (usuarioActual != null)
			playLists = usuarioActual.getPlayLists();
		return playLists;
	}
	
	public boolean isUsuarioPremium() {
		return usuarioActual.isPremium();
	}

	public void setUsuarioPremium() {
		if (usuarioActual != null)
			usuarioActual.setPremium(true);
	}

	public void setUsuarioNoPremium() {
		if (usuarioActual != null)
			usuarioActual.setPremium(false);
	}

	public List<Usuario> getTodosUsuarios() {
		return repositorioUsuarios.getAll();
	}

	// CANCIONES

	public void altaCancion(String titulo, String interprete, String url, String estilo) {
		Cancion cancion = new Cancion(titulo, 0, url, interprete, estilo);

		if (repositorioCanciones.existeCancion(cancion))
			return;

		repositorioCanciones.addCancion(cancion);
		adaptadorCancion.registrarCancion(cancion);
	}

	public void eliminarCancion(Cancion cancion) {
		adaptadorCancion.borrarCancion(cancion);
		repositorioCanciones.removeCancion(cancion);
	}

	public void addCancionRecientes(Cancion cancion) {
		if (usuarioActual != null)
			usuarioActual.addCancionReciente(cancion);

		adaptadorUsuario.modificarUsuario(usuarioActual);
	}

	public void actualizarNumRep(Cancion cancion) {
		cancion.setNumRep(cancion.getNumRep() + 1);
		adaptadorCancion.modificarCancion(cancion);
		repositorioCanciones.addCancion(cancion);
	}

	public List<Cancion> getCancionesPlayList() {
		return usuarioActual.getPlayLists().stream() 
				.flatMap(playlist -> playlist.getCanciones().stream()) 					
				.collect(Collectors.toList());
	}

	// Usamos CargadorCanciones para poder cargarlas desde un fichero XML
	public void cargarCanciones(String fichero) throws URISyntaxException {
		CargadorCanciones c = new CargadorCanciones();
		c.addOyente(this);
		c.setArchivoCanciones(fichero);
	}
	
	public void cargarCancionesEnDisco() throws Exception {
		CargadorCancionesDisco.INSTANCE.cargarCanciones();
	}

	@Override
	public void enteradoCarga(CancionesEvent evento) {
		evento.getCanciones().getCancion().stream().forEach(cancion -> {
			this.altaCancion(cancion.getTitulo(), cancion.getInterprete(), cancion.getURL(), cancion.getEstilo());
		});
	}

	public List<Cancion> getTodasCanciones() {
		return repositorioCanciones.getAllCanciones();
	}

	public List<Cancion> filtroInterprete(String interprete, List<Cancion> canciones) {
		return canciones.stream().filter(c -> c.getInterprete().toLowerCase().contains(interprete.toLowerCase()))
				.collect(Collectors.toList());
	}

	public List<Cancion> filtroEstilo(String estilo, List<Cancion> canciones) {
		return canciones.stream().filter(e -> e.getEstilo().toLowerCase().contains(estilo.toLowerCase()))
				.collect(Collectors.toList());
	}

	public List<Cancion> filtroTitulo(String titulo, List<Cancion> canciones) {
		return canciones.stream().filter(t -> t.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
				.collect(Collectors.toList());
	}


	// PLAYLISTS

	public void crearPlayList(String nombre) {
		if (usuarioActual != null) {
			PlayList playList = new PlayList(nombre);
			usuarioActual.addPlayList(playList);
			// Se persiste la playList en la bd
			adaptadorPlayList.registrarPlayList(playList);
			// Se actualiza el usuario en la bd
			adaptadorUsuario.modificarUsuario(usuarioActual);
		}
	}

	public void eliminarPlayList(PlayList playList) {
		// Se elimina de las playList del usuario
		usuarioActual.removePlayList(playList);
		// Se actualiza el usuario en la bd
		adaptadorUsuario.modificarUsuario(usuarioActual);
		// Eliminamos la lista de la bd
		adaptadorPlayList.borrarPlayList(playList);
	}

	// Devuelve una playList de un usuario dado el nombre de esta
	public PlayList getPlayList(String nombre) {
		if (usuarioActual != null) {
			PlayList playList = usuarioActual.getPlayLists().stream().filter(pl -> pl.getNombre().equals(nombre))
					.findAny().orElse(null);
			return playList;
		}
		return null;
	}

	public void addCancionAPlayList(Cancion cancion, PlayList playList) {
		if (usuarioActual != null) {
			playList.addCancion(cancion);
			adaptadorPlayList.modificarPlayList(playList);
		}
	}
	
	//Player
	public void iniciarReproduccion(Cancion cancion) {
		reproductor.play("play", cancion);
		cancion.setNumRep(cancion.getNumRep() + 1);
		adaptadorCancion.modificarCancion(cancion);
	}
	
	public void pausarReproduccion(Cancion cancion) {
		reproductor.play("pause", cancion);
	}

	public void detenerReproduccion(Cancion cancion) {
		reproductor.play("stop", cancion);
	}

	public Cancion getCancionSonando() {
		return reproductor.getCancionActual();
	}
}
