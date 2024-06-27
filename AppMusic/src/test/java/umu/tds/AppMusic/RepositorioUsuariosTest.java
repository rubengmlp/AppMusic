package umu.tds.AppMusic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import umu.tds.dominio.Usuario;
import umu.tds.dominio.repositorios.RepositorioUsuarios;

public class RepositorioUsuariosTest {

    @Test
    public void testAgregarYEliminarUsuario() {
        RepositorioUsuarios repositorio = RepositorioUsuarios.getUnicaInstancia();

        LocalDate fechaNacimiento1 = LocalDate.of(1970, 5, 20);
        Usuario usuario1 = new Usuario("user_fary7", "Paco Porras", "paco@example.com", fechaNacimiento1, "p4c0p455w0rd");
        
        LocalDate fechaNacimiento2 = LocalDate.of(1988, 12, 10);
        Usuario usuario2 = new Usuario("user_fary8", "Ana la de Carretera", "ana@example.com", fechaNacimiento2, "carretera123");

        repositorio.addUsuario(usuario1);
        assertTrue(repositorio.existeUsuario(usuario1));

        repositorio.addUsuario(usuario2);
        assertTrue(repositorio.existeUsuario(usuario2));

        repositorio.removeUsuario(usuario1);
        assertFalse(repositorio.existeUsuario(usuario1));
    }

    @Test
    public void testObtenerUsuarioPorNombre() {
        RepositorioUsuarios repositorio = RepositorioUsuarios.getUnicaInstancia();

        LocalDate fechaNacimiento = LocalDate.of(1995, 7, 12);
        Usuario usuario = new Usuario("user_fary9", "Manuel El Correcaminos", "manuel@example.com", fechaNacimiento, "m4nU3l!2#");

        repositorio.addUsuario(usuario);

        Usuario usuarioRecuperado = repositorio.getUsuario("user_fary9");
        assertNotNull(usuarioRecuperado);
        assertEquals("Manuel El Correcaminos", usuarioRecuperado.getNombre());
    }

    @Test
    public void testObtenerTodosLosUsuarios() {
        RepositorioUsuarios repositorio = RepositorioUsuarios.getUnicaInstancia();

        List<Usuario> usuarios = repositorio.getAll();
        assertNotNull(usuarios);
        assertFalse(usuarios.isEmpty());
    }
}
