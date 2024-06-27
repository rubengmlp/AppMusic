package umu.tds.vistas;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import umu.tds.controlador.AppMusic;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.PlayList;
import umu.tds.dominio.repositorios.BDException;
import umu.tds.persistencia.DAOException;
import javax.swing.DefaultComboBoxModel;

public class DialogAnadir extends JDialog {

	private JComboBox<String> comboBoxPlaylists;
	private JTextField textFieldNuevaPlaylist;

	private List<Cancion> cancionesSeleccionadas;

	public DialogAnadir(Frame owner) throws DAOException, BDException {
		super(owner, "Seleccionar Playlist", true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(400, 200);
		setLocationRelativeTo(owner);

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Label y ComboBox para seleccionar playlist existente
		JLabel lblPlaylists = new JLabel("Seleccionar PlayList:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(lblPlaylists, gbc);

		comboBoxPlaylists = new JComboBox<>();
		comboBoxPlaylists.setModel(new DefaultComboBoxModel(new String[] {" "}));
		List<PlayList> playLists = AppMusic.getUnicaInstancia().getPlayListsUsuario();
		for (PlayList playlist : playLists) {
			comboBoxPlaylists.addItem(playlist.getNombre());
		}

		GridBagConstraints gbcComboBox = new GridBagConstraints();
		gbcComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbcComboBox.gridx = 1;
		gbcComboBox.gridy = 0;
		panel.add(comboBoxPlaylists, gbcComboBox);

		JLabel lblNuevaPlaylist = new JLabel("Nueva PlayList:");
		GridBagConstraints gbcLblNuevaPlaylist = new GridBagConstraints();
		gbcLblNuevaPlaylist.gridx = 0;
		gbcLblNuevaPlaylist.gridy = 1;
		panel.add(lblNuevaPlaylist, gbcLblNuevaPlaylist);

		textFieldNuevaPlaylist = new JTextField(20);
		GridBagConstraints gbcTextField = new GridBagConstraints();
		gbcTextField.gridx = 1;
		gbcTextField.gridy = 1;
		panel.add(textFieldNuevaPlaylist, gbcTextField);

		JButton btnAñadir = new JButton("Añadir");
		btnAñadir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String nombrePlaylist = (String) comboBoxPlaylists.getSelectedItem();
		        try {
		            if (nombrePlaylist == null || nombrePlaylist.isEmpty() || nombrePlaylist.equals(" ")) {
		                nombrePlaylist = textFieldNuevaPlaylist.getText();
		                if (nombrePlaylist.isEmpty()) {
		                    JOptionPane.showMessageDialog(DialogAnadir.this, "Ingrese un nombre para la nueva PlayList",
		                            "Error", JOptionPane.ERROR_MESSAGE);
		                    return;
		                }
		                AppMusic.getUnicaInstancia().crearPlayList(nombrePlaylist, cancionesSeleccionadas);
		            } else {
		            	if (!textFieldNuevaPlaylist.getText().isBlank()) {
		            		JOptionPane.showMessageDialog(DialogAnadir.this, "Rellene solo uno de los dos campos",
		                            "Error", JOptionPane.ERROR_MESSAGE);
		                    return;
		            	}
		                PlayList playList = AppMusic.getUnicaInstancia().getPlayList(nombrePlaylist);
		                if (playList != null) {
		                    AppMusic.getUnicaInstancia().addCancionesAPlayList(cancionesSeleccionadas, playList);
		                } else {
		                    JOptionPane.showMessageDialog(DialogAnadir.this, "No se encontró la PlayList seleccionada",
		                            "Error", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		        } catch (DAOException | BDException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(DialogAnadir.this, "Error: " + ex.getMessage(),
		                    "Error", JOptionPane.ERROR_MESSAGE);
		        }
		        dispose();
		    }
		});

		GridBagConstraints gbcBtnAñadir = new GridBagConstraints();
		gbcBtnAñadir.gridx = 0;
		gbcBtnAñadir.gridy = 2;
		gbcBtnAñadir.gridwidth = 2;
		panel.add(btnAñadir, gbcBtnAñadir);

		getContentPane().add(panel);
	}

	public void setCancionesSeleccionadas(List<Cancion> cancionesSeleccionadas) {
		this.cancionesSeleccionadas = cancionesSeleccionadas;
	}

}
