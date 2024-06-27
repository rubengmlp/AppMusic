package umu.tds.vistas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.itextpdf.text.DocumentException;

import umu.tds.controlador.AppMusic;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.repositorios.BDException;
import umu.tds.persistencia.DAOException;

public class VistaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PanelBuscar panelBuscar;
	private PanelGestion panelGestion;
	private PanelMasReproducidas panelMasReproducidas;
	private PanelRecientes panelRecientes;
	private PanelMisPlayLists panelMisPlayLists;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPrincipal frame = new VistaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws BDException
	 * @throws DAOException
	 */
	public VistaPrincipal() throws DAOException, BDException {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(VistaPrincipal.class.getResource("/umu/tds/imagenes/logo3.png")));
		setTitle("AppMusic");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 669, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new CardLayout(0, 0));

		panelBuscar = new PanelBuscar();
		panelGestion = new PanelGestion();
		panelMasReproducidas = new PanelMasReproducidas();
		panelRecientes = new PanelRecientes();
		panelMisPlayLists = new PanelMisPlayLists();

		panel_2.add(panelBuscar, "Buscar");
		panel_2.add(panelGestion, "Gestionar");
		panel_2.add(panelMasReproducidas, "MasReproducidas");
		panel_2.add(panelRecientes, "Recientes");
		panel_2.add(panelMisPlayLists, "MisPlayLists");

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Men\u00FA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setToolTipText("");
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		contentPane.add(panel, BorderLayout.WEST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 89, 0 };
		gbl_panel.rowHeights = new int[] { 20, 23, 20, 0, 20, 0, 20, 0, 20, 0, 20, 0, 20, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0,
				Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		panel.add(panel_3, gbc_panel_3);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/umu/tds/imagenes/buscar.png")));
		panel_3.add(lblNewLabel_1);

		JButton botonBuscar = new JButton("Buscar");
		botonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (panel_2.getLayout());
				cl.show(panel_2, "Buscar");
			}
		});
		panel_3.add(botonBuscar);

		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 3;
		panel.add(panel_4, gbc_panel_4);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2
				.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/umu/tds/imagenes/icons8-plus-24 (1).png")));
		panel_4.add(lblNewLabel_2);

		JButton botonGestionar = new JButton("Gestionar playlists");
		botonGestionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (panel_2.getLayout());
				cl.show(panel_2, "Gestionar");
			}
		});
		panel_4.add(botonGestionar);

		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 5;
		panel.add(panel_5, gbc_panel_5);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/umu/tds/imagenes/icons8-timer-24.png")));
		panel_5.add(lblNewLabel_3);

		JButton botonRecientes = new JButton("Recientes");
		botonRecientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					panelRecientes.actualizar();
				} catch (DAOException | BDException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				CardLayout cl = (CardLayout) (panel_2.getLayout());
				cl.show(panel_2, "Recientes");
			}
		});
		panel_5.add(botonRecientes);
		
		JPanel panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 7;
		panel.add(panel_6, gbc_panel_6);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4
				.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/umu/tds/imagenes/icons8-playlist-24.png")));
		panel_6.add(lblNewLabel_4);

		JButton botonPlayLists = new JButton("Mis playlists");
		botonPlayLists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					panelMisPlayLists.actualizar();
				} catch (DAOException | BDException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				CardLayout cl = (CardLayout) (panel_2.getLayout());
				cl.show(panel_2, "MisPlayLists");
			}
		});
		panel_6.add(botonPlayLists);

		JPanel panel_masRepro = new JPanel();
		GridBagConstraints gbc_panel_masRepro = new GridBagConstraints();
		gbc_panel_masRepro.insets = new Insets(0, 0, 5, 0);
		gbc_panel_masRepro.fill = GridBagConstraints.BOTH;
		gbc_panel_masRepro.gridx = 0;
		gbc_panel_masRepro.gridy = 9;
		panel.add(panel_masRepro, gbc_panel_masRepro);

		JLabel lblNewLabel_3_2 = new JLabel("");
		lblNewLabel_3_2.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/umu/tds/imagenes/fuego.png")));
		panel_masRepro.add(lblNewLabel_3_2);

		JButton btnMasReproducidas = new JButton("Más reproducidas");
		btnMasReproducidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					panelMasReproducidas.actualizar();
				} catch (DAOException | BDException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				CardLayout cl = (CardLayout) (panel_2.getLayout());
				cl.show(panel_2, "MasReproducidas");
			}
		});
		panel_masRepro.add(btnMasReproducidas);
		
		JPanel panel_pdf = new JPanel();
		GridBagConstraints gbc_panel_pdf = new GridBagConstraints();
		gbc_panel_pdf.insets = new Insets(0, 0, 5, 0);
		gbc_panel_pdf.fill = GridBagConstraints.BOTH;
		gbc_panel_pdf.gridx = 0;
		gbc_panel_pdf.gridy = 11;
		panel.add(panel_pdf, gbc_panel_pdf);

		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/umu/tds/imagenes/pdf.png")));
		panel_pdf.add(lblNewLabel_3_1);

		JButton btnGenerarPdf = new JButton("Generar PDF");
		btnGenerarPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean generarPDF = false;
				try {
					generarPDF = AppMusic.getUnicaInstancia().generarPDF();
				} catch (FileNotFoundException | DocumentException | DAOException | BDException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (generarPDF) {
					JOptionPane.showMessageDialog(VistaPrincipal.this, "PDF generado con éxito en ./pdf", "Generar PDF",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(VistaPrincipal.this, "Error al generar el PDF", "Generar PDF",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel_pdf.add(btnGenerarPdf);

		// mostrar solo si es premium
		if (AppMusic.getUnicaInstancia().isUsuarioPremium()) {
			panel_masRepro.setVisible(true);
			panel_pdf.setVisible(true);
		} else {
			panel_masRepro.setVisible(false);
			panel_pdf.setVisible(false);
		}


		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel_1, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Bienvenido, " + AppMusic.getUnicaInstancia().getUsername());
		panel_1.add(lblNewLabel);

		JButton btnPremium = new JButton("Premium");
		btnPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double precio = 10;
				try {
					precio = AppMusic.getUnicaInstancia().getPrecioPremium();
				} catch (DAOException | BDException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int opcion = JOptionPane.showConfirmDialog(null,
						"¿Seguro que quiere hacerse usuario premium por " + precio + "€?", "Hacerse premium",
						JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.OK_OPTION) {
					try {
						AppMusic.getUnicaInstancia().setUsuarioPremium();

					} catch (DAOException | BDException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					btnPremium.setVisible(false);
					panel_masRepro.setVisible(true);
					panel_pdf.setVisible(true);
				}
			}
		});

		btnPremium.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/umu/tds/imagenes/icons8-premium-24.png")));
		panel_1.add(btnPremium);
		if (AppMusic.getUnicaInstancia().isUsuarioPremium()) {
			btnPremium.setVisible(false);
		} else {
			btnPremium.setVisible(true);
		}

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VistaLogin login = new VistaLogin();
				login.mostrar();
				Cancion sonando = null;
				try {
					sonando = AppMusic.getUnicaInstancia().getCancionSonando();
				} catch (DAOException | BDException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (sonando == null) {
					dispose();
				} else {
					try {
						AppMusic.getUnicaInstancia().detenerReproduccion(sonando);
					} catch (DAOException | BDException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
				}
			}
		});
		btnLogout.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/umu/tds/imagenes/icons8-logout-24.png")));
		panel_1.add(btnLogout);

	}

}
