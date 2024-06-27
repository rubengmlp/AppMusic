package umu.tds.controlador;

import java.awt.EventQueue;

import javax.swing.UIManager;

import umu.tds.vistas.VistaLogin;

public class Lanzador {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					VistaLogin ventana = new VistaLogin();
					ventana.mostrar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
