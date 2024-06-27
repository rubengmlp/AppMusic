package umu.tds.AppMusic;	

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import umu.tds.dominio.Cancion;
import umu.tds.dominio.PlayList;
import umu.tds.dominio.Usuario;
import umu.tds.dominio.descuentos.Descuento65;
import umu.tds.dominio.descuentos.DescuentoJoven;

public class UsuarioTest {

    @Test
    public void testCrearUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1980, 5, 15);
        Usuario usuario = new Usuario("user_fary1", "Juanito Calavera", "juanito@example.com", fechaNacimiento, "password123");

        assertEquals("user_fary1", usuario.getUsername());
        assertEquals("Juanito Calavera", usuario.getNombre());
        assertEquals("juanito@example.com", usuario.getEmail());
        assertEquals(fechaNacimiento, usuario.getFecha());
        assertEquals("password123", usuario.getContrasena());
        assertFalse(usuario.isPremium());
        assertNotNull(usuario.getDescuento());
    }

    @Test
    public void testActualizarPremium() {
        LocalDate fechaNacimiento = LocalDate.of(1975, 3, 10);
        Usuario usuario = new Usuario("user_fary2", "Pepita La Puntual", "pepita@example.com", fechaNacimiento, "securepwd");

        usuario.setPremium(true);

        assertTrue(usuario.isPremium());
        assertNotNull(usuario.getDescuento());
        assertTrue(usuario.getDescuento() instanceof Descuento65); // Usuario debe ser elegible para Descuento65
    }

    @Test
    public void testAgregarPlaylist() {
        Usuario usuario = new Usuario("user_fary3", "Manolito Buenavista", "manolo@example.com", LocalDate.of(1990, 1, 1), "qwerty");

        PlayList playlist = new PlayList("Mis Favoritas del Fary");
        usuario.addPlayList(playlist);

        assertTrue(usuario.getPlayLists().contains(playlist));
    }

    @Test
    public void testAgregarCancionReciente() {
        Usuario usuario = new Usuario("user_fary4", "Rosita La Salerosa", "rosita@example.com", LocalDate.of(1985, 8, 20), "abc123");

        Cancion cancion = new Cancion("La Mandanga", "https://example.com/la-mandanga.mp3", "El Fary", "Rumba");
        usuario.addCancionReciente(cancion);

        assertTrue(usuario.getCancionesRecientes().contains(cancion));
    }

    @Test
    public void testActualizarDescuento() {
        LocalDate fechaNacimiento1 = LocalDate.of(1950, 6, 15);
        Usuario usuario1 = new Usuario("user_fary5", "Antonio El Quemao", "antonio@example.com", fechaNacimiento1, "pass567");

        LocalDate fechaNacimiento2 = LocalDate.of(1990, 2, 10);
        Usuario usuario2 = new Usuario("user_fary6", "Maruja La Machacona", "maruja@example.com", fechaNacimiento2, "654321");

        assertFalse(usuario1.getDescuento() instanceof Descuento65);
        assertTrue(usuario2.getDescuento() instanceof DescuentoJoven);

        usuario1.setPremium(true);
        usuario2.setPremium(true);

        assertTrue(usuario1.getDescuento() instanceof Descuento65);
        assertFalse(usuario2.getDescuento() instanceof DescuentoJoven);
    }
}
