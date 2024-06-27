package umu.tds.vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;

public class VistaLogin {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaLogin window = new VistaLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VistaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VistaLogin.class.getResource("/umu/tds/imagenes/logo3.png")));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelLogin = new JPanel();
		frame.getContentPane().add(panelLogin, BorderLayout.CENTER);
		GridBagLayout gbl_panelLogin = new GridBagLayout();
		gbl_panelLogin.columnWidths = new int[]{30, 30, 167, 30, 0};
		gbl_panelLogin.rowHeights = new int[]{30, 20, 20, 43, 43, 60, 43, 43, 30, 0};
		gbl_panelLogin.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelLogin.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelLogin.setLayout(gbl_panelLogin);
		
		JLabel lblTitulo = new JLabel("AppMusic ");
		lblTitulo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblTitulo.setIcon(new ImageIcon(VistaLogin.class.getResource("/umu/tds/imagenes/logo3.png")));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 26));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.gridwidth = 2;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 1;
		panelLogin.add(lblTitulo, gbc_lblTitulo);
		
		JLabel lblUsuario = new JLabel("Usuario");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.gridx = 1;
		gbc_lblUsuario.gridy = 3;
		panelLogin.add(lblUsuario, gbc_lblUsuario);
		
		textField = new JTextField();
		textField.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 2, true));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 3;
		panelLogin.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblContraseña = new JLabel("Contraseña");
		GridBagConstraints gbc_lblContraseña = new GridBagConstraints();
		gbc_lblContraseña.insets = new Insets(0, 0, 5, 5);
		gbc_lblContraseña.anchor = GridBagConstraints.EAST;
		gbc_lblContraseña.gridx = 1;
		gbc_lblContraseña.gridy = 4;
		panelLogin.add(lblContraseña, gbc_lblContraseña);
		
		textField_1 = new JTextField();
		textField_1.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 2, true));
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 4;
		panelLogin.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 6;
		panelLogin.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{154, 154, 0};
		gbl_panel.rowHeights = new int[]{38, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton botonLoginGitHub = new JButton("Login con GitHub");
		botonLoginGitHub.setIcon(new ImageIcon(VistaLogin.class.getResource("/umu/tds/imagenes/icons8-github-24.png")));
		botonLoginGitHub.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 2, true));
		botonLoginGitHub.setAlignmentX(Component.CENTER_ALIGNMENT);
		botonLoginGitHub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton botonLogin = new JButton("Login");
		botonLogin.setIcon(new ImageIcon(VistaLogin.class.getResource("/umu/tds/imagenes/icons8-login-24.png")));
		botonLogin.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 2, true));
		botonLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_botonLogin = new GridBagConstraints();
		gbc_botonLogin.fill = GridBagConstraints.BOTH;
		gbc_botonLogin.insets = new Insets(0, 0, 0, 5);
		gbc_botonLogin.gridx = 0;
		gbc_botonLogin.gridy = 0;
		panel.add(botonLogin, gbc_botonLogin);
		GridBagConstraints gbc_botonLoginGitHub = new GridBagConstraints();
		gbc_botonLoginGitHub.fill = GridBagConstraints.BOTH;
		gbc_botonLoginGitHub.gridx = 1;
		gbc_botonLoginGitHub.gridy = 0;
		panel.add(botonLoginGitHub, gbc_botonLoginGitHub);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.VERTICAL;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 7;
		panelLogin.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{308, 0};
		gbl_panel_1.rowHeights = new int[]{38, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JButton btnRegistro = new JButton("Registro");
		btnRegistro.setIcon(new ImageIcon(VistaLogin.class.getResource("/umu/tds/imagenes/icons8-register-24.png")));
		btnRegistro.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 2, true));
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnRegistro = new GridBagConstraints();
		gbc_btnRegistro.fill = GridBagConstraints.VERTICAL;
		gbc_btnRegistro.gridx = 0;
		gbc_btnRegistro.gridy = 0;
		panel_1.add(btnRegistro, gbc_btnRegistro);
	}

	public void mostrar() {
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
