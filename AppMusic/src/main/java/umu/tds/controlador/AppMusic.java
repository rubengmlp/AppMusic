package umu.tds.controlador;

import java.util.Map;
import java.util.TreeMap;

import umu.tds.dominio.Cancion;
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

	public boolean isRegistrado(String usuario) {
		return repositorioUsuarios.getUsuario(usuario) != null;
	}

	public boolean registroUsuario(String nombre, String apellidos, String fechaNacimiento, String email, String login,
			String password) {

		Usuario usuario = new Usuario(nombre, apellidos, login, null, password);
		adaptadorUsuario.registrarUsuario(usuario);
		repositorioUsuarios.addUsuario(usuario);
		return true;
	}
}
