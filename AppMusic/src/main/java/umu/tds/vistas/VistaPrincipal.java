package umu.tds.vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.CardLayout;

public class VistaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
	 */
	public VistaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VistaPrincipal.class.getResource("/umu/tds/imagenes/logo3.png")));
		setTitle("AppMusic");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 669, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Men\u00FA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setToolTipText("");
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		contentPane.add(panel, BorderLayout.WEST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{89, 0};
		gbl_panel.rowHeights = new int[]{20, 23, 0, 20, 0, 0, 20, 0, 0, 20, 0, 20, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
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
		
		JButton btnNewButton = new JButton("Buscar");
		panel_3.add(btnNewButton);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 4;
		panel.add(panel_4, gbc_panel_4);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/umu/tds/imagenes/icons8-plus-24 (1).png")));
		panel_4.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Gestionar playlists");
		panel_4.add(btnNewButton_1);
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 7;
		panel.add(panel_5, gbc_panel_5);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/umu/tds/imagenes/icons8-timer-24.png")));
		panel_5.add(lblNewLabel_3);
		
		JButton btnNewButton_3 = new JButton("Recientes");
		panel_5.add(btnNewButton_3);
		
		JPanel panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 10;
		panel.add(panel_6, gbc_panel_6);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/umu/tds/imagenes/icons8-playlist-24.png")));
		panel_6.add(lblNewLabel_4);
		
		JButton btnNewButton_2 = new JButton("Mis playlists");
		panel_6.add(btnNewButton_2);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Bienvenido, usuario");
		panel_1.add(lblNewLabel);
		
		JButton btnNewButton_4 = new JButton("Premium");
		btnNewButton_4.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/umu/tds/imagenes/icons8-premium-24.png")));
		panel_1.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Logout");
		btnNewButton_5.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/umu/tds/imagenes/icons8-logout-24.png")));
		panel_1.add(btnNewButton_5);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new CardLayout(0, 0));
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setOrientation(SwingConstants.VERTICAL);
		panel_2.add(separator, "name_515236240046300");
	}

}
