package umu.tds.controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

public class AppMusic {
	private static AppMusic unicaInstancia;

	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorCancionDAO adaptadorCancion;
	private IAdaptadorEstiloMusicalDAO adaptadorEstiloMusical;
	private IAdaptadorPlayListDAO adaptadorPlayList;

	private RepositorioCanciones repositorioCanciones;
	private RepositorioUsuarios repositorioUsuarios;

	private static Usuario usuarioActual;

	private Map<Cancion, Integer> masReproducidas;

	private AppMusic() throws DAOException, BDException {
		masReproducidas = new TreeMap<>();
		try {
			inicializarAdaptadores();
			inicializarRepositorios();
		} catch (DAOException e) {
			throw e;
		} catch (Exception e) {
			throw new BDException(e.getMessage());
		}
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

	public boolean registro(String username, String nombre, String apellidos, String fechaNacimiento, String email, LocalDate fecha,
			String contrasena) {

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
    	if(!isRegistrado(username))
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
    
    public void setUsuarioPremium() {
    	if (usuarioActual != null)
    		usuarioActual.setPremium(true);
    }
    
    public void setUsuarioNoPremium() {
    	if (usuarioActual != null)
    		usuarioActual.setPremium(false);
    }
    
    public List<Usuario> getTodosUsuarios(){
    	return repositorioUsuarios.getAll();
    }
    
    
}
