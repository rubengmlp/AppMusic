package umu.tds.utilidades;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import umu.tds.dominio.Cancion;

public class Player {
	// canciones almacenadas en src/main/resources
	private Cancion cancionActual = null;
	private MediaPlayer mediaPlayer;
	private String carpetaCanciones = "/canciones/";

	public Player() {
		// existen otras formas de lanzar JavaFX desde Swing
		try {
			com.sun.javafx.application.PlatformImpl.startup(() -> {
			});
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception: " + ex.getMessage());
		}
	}

	public void play(String boton, Cancion cancion) {
		switch (boton) {
		case "play":
			try {
				setCancionActual(cancion);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			mediaPlayer.play();
			break;
		case "stop":
			mediaPlayer.stop();
			break;
		case "pause":
			mediaPlayer.pause();
			break;
		}
	}

	private void setCancionActual(Cancion cancion) throws FileNotFoundException {
		if (cancionActual != cancion) {
			// Para la cancion que estaba sonando antes
			if (mediaPlayer != null)
				mediaPlayer.stop();

			cancionActual = cancion;
			String rutaCancion = cancion.getUrl();

			URL resourceURL = getClass().getResource(carpetaCanciones + rutaCancion);
			if (resourceURL == null) {
				try {
					String tempPath = System.getProperty("user.dir") + "/temp";
					System.setProperty("java.io.tmpdir", tempPath);
					Path mp3 = Files.createTempFile("now-playing", ".mp3");

					InputStream stream = new URL(rutaCancion).openStream();
					Files.copy(stream, mp3, StandardCopyOption.REPLACE_EXISTING);

					Media media = new Media(mp3.toFile().toURI().toString());
					mediaPlayer = new MediaPlayer(media);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				Media hit = new Media(resourceURL.toExternalForm());
				mediaPlayer = new MediaPlayer(hit);
			}

		}
	}

	public Cancion getCancionActual() {
		return cancionActual;
	}
}