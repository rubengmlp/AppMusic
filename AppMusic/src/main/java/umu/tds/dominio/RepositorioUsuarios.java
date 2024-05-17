package umu.tds.dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import umu.tds.persistencia.*;
import umu.tds.dominio.Usuario;

public class RepositorioUsuarios {
	private Map<String, Usuario> usuarios;
	private static RepositorioUsuarios unicaInstancia = new RepositorioUsuarios();

	private FactoriaDAO dao;
	private IAdaptadorUsuarioDAO adaptadorUsuario;

	private RepositorioUsuarios() {
		try {
			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			adaptadorUsuario = dao.getUsuarioDAO();
			usuarios = new HashMap<String, Usuario>();
			this.cargarRepositorio();
		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}

	public boolean existeUsuario(Usuario u) {
		for (String i : usuarios.keySet()) {
			if (i.equals(u.getNombre()))
				return true;
		}
		return false;
	}

	public boolean existeUsuario(String u) {
		for (String i : usuarios.keySet()) {
			if (i.equals(u))
				return true;
		}
		return false;
	}

	public static RepositorioUsuarios getUnicaInstancia() {
		return unicaInstancia;
	}

	public void addUsuario(Usuario u) {
		this.usuarios.put(u.getNombre(), u);
	}

	public void removeUsuario(Usuario u) {
		this.usuarios.remove(u.getNombre());
	}

	public List<Usuario> getAll() {
		return this.usuarios.values().stream().collect(Collectors.toList());
	}

	public Usuario getUsuario(int codigo) {
		for (Usuario u : this.usuarios.values()) {
			if (u.getCodigo() == codigo)
				return u;
		}
		return null;
	}

	public Usuario getUsuario(String usuario) {
		return this.usuarios.get(usuario);
	}

	public Usuario getUsuario(Usuario u) {
		return this.usuarios.get(u.getNombre());
	}

	private void cargarRepositorio() throws DAOException {
		List<Usuario> usuariosBD = adaptadorUsuario.recuperarTodosUsuarios();
		for (Usuario u : usuariosBD) {
			this.usuarios.put(u.getNombre(), u);
		}
	}
}
