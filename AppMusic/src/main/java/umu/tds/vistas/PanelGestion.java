package umu.tds.vistas;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import umu.tds.controlador.AppMusic;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.PlayList;
import umu.tds.dominio.repositorios.BDException;
import umu.tds.persistencia.DAOException;

public class PanelGestion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textField;
	private JTextField txtTitulo;
	private List<Cancion> canciones;

	/**
	 * Create the panel.
	 */
	public PanelGestion() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{40, 240, 25, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{20, 50, 120, 10, 0, 10, 0};
		gbl_panel_1.rowHeights = new int[]{10, 0, 10, 10, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblTitulo = new JLabel("Título");
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.anchor = GridBagConstraints.EAST;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 1;
		panel_1.add(lblTitulo, gbc_lblTitulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(10);
		GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
		gbc_txtTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_txtTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitulo.gridx = 2;
		gbc_txtTitulo.gridy = 1;
		panel_1.add(txtTitulo, gbc_txtTitulo);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_3.gridx = 2;
		gbc_panel_3.gridy = 2;
		panel_1.add(panel_3, gbc_panel_3);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tituloPlaylist = txtTitulo.getText().trim();
				if (tituloPlaylist.isEmpty()) {
					return;
				}
				PlayList playlistActual = null;
				try {
					playlistActual = AppMusic.getUnicaInstancia().getPlayList(tituloPlaylist);
				} catch (DAOException | BDException ex) {
					ex.printStackTrace();
					return;
				}
				if (playlistActual == null) {
					try {
						AppMusic.getUnicaInstancia().crearPlayList(tituloPlaylist, canciones);
						// Mostrar mensaje de éxito o realizar alguna acción adicional si es necesario
					} catch (DAOException | BDException ex) {
						// Manejar la excepción apropiadamente
						ex.printStackTrace();
					}
				} 
			}
		});
		panel_3.add(btnCrear);
		
		JButton btnEliminarLista = new JButton("Eliminar lista");
		panel_3.add(btnEliminarLista);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"T\u00EDtulo", "Int\u00E9rpete", "Estilo", "Seleccionada"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		table = new JTable();
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{20, 89, 89, 89, 89, 89, 0, 20, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnAnterior = new JButton("");
		btnAnterior.setIcon(new ImageIcon(PanelBuscar.class.getResource("/umu/tds/imagenes/atras.png")));
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        try {
		            Cancion c = AppMusic.getUnicaInstancia().getCancionSonando();
		            if (c != null) {
		                int fila = table.getSelectedRow();
		                if (fila == -1) {
		                    return;
		                }
		                fila--;
		                if (fila < 0) {
		                    fila = canciones.size() - 1;
		                }
		                AppMusic.getUnicaInstancia().detenerReproduccion(c);
		                AppMusic.getUnicaInstancia().iniciarReproduccion(canciones.get(fila));
		                table.setRowSelectionInterval(fila, fila);
		            }
		        } catch (DAOException | BDException e1) {
		            e1.printStackTrace();
		        }
		    }
		});
		GridBagConstraints gbc_btnAnterior = new GridBagConstraints();
		gbc_btnAnterior.insets = new Insets(0, 0, 0, 5);
		gbc_btnAnterior.gridx = 1;
		gbc_btnAnterior.gridy = 0;
		panel.add(btnAnterior, gbc_btnAnterior);
		
		JButton btnDetener = new JButton("");
		btnDetener.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            Cancion cancion = AppMusic.getUnicaInstancia().getCancionSonando();
		            if (cancion != null) {
		                AppMusic.getUnicaInstancia().detenerReproduccion(cancion);
		            }
		        } catch (DAOException | BDException e1) {
		            e1.printStackTrace();
		        }
		    }
		});

		btnDetener.setIcon(new ImageIcon(PanelBuscar.class.getResource("/umu/tds/imagenes/detener.png")));
		GridBagConstraints gbc_btnDetener = new GridBagConstraints();
		gbc_btnDetener.insets = new Insets(0, 0, 0, 5);
		gbc_btnDetener.gridx = 2;
		gbc_btnDetener.gridy = 0;
		panel.add(btnDetener, gbc_btnDetener);
		
		JButton btnPlay = new JButton("");
		btnPlay.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            Cancion cancion = canciones.get(selectedRow);
		            if (cancion != null) {
		                try {
		                    AppMusic.getUnicaInstancia().iniciarReproduccion(cancion);
		                } catch (DAOException | BDException e1) {
		                    e1.printStackTrace();
		                }
		            }
		        }
		    }
		});

		btnPlay.setIcon(new ImageIcon(PanelBuscar.class.getResource("/umu/tds/imagenes/play.png")));
		GridBagConstraints gbc_btnPlay = new GridBagConstraints();
		gbc_btnPlay.insets = new Insets(0, 0, 0, 5);
		gbc_btnPlay.gridx = 3;
		gbc_btnPlay.gridy = 0;
		panel.add(btnPlay, gbc_btnPlay);
		
		JButton btnPause = new JButton("");
		btnPause.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            Cancion cancion = AppMusic.getUnicaInstancia().getCancionSonando();
		            if (cancion != null) {
		                AppMusic.getUnicaInstancia().pausarReproduccion(cancion);
		            }
		        } catch (DAOException | BDException e1) {
		            e1.printStackTrace();
		        }
		    }
		});

		btnPause.setIcon(new ImageIcon(PanelBuscar.class.getResource("/umu/tds/imagenes/pausa.png")));
		GridBagConstraints gbc_btnPause = new GridBagConstraints();
		gbc_btnPause.insets = new Insets(0, 0, 0, 5);
		gbc_btnPause.gridx = 4;
		gbc_btnPause.gridy = 0;
		panel.add(btnPause, gbc_btnPause);
		
		JButton btnSiguiente = new JButton("");
		btnSiguiente.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            Cancion cancionSonando = AppMusic.getUnicaInstancia().getCancionSonando();

		            if (cancionSonando != null) {
		                int filaSeleccionada = table.getSelectedRow();

		                if (filaSeleccionada == -1) {
		                    return;
		                }

		                filaSeleccionada++;

		                if (filaSeleccionada >= canciones.size()) {
		                    filaSeleccionada = 0;
		                }

		                AppMusic.getUnicaInstancia().detenerReproduccion(cancionSonando);
		                AppMusic.getUnicaInstancia().iniciarReproduccion(canciones.get(filaSeleccionada));
		                table.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
		            }
		        } catch (DAOException | BDException e1) {
		            e1.printStackTrace();
		        }
		    }
		});

		btnSiguiente.setIcon(new ImageIcon(PanelBuscar.class.getResource("/umu/tds/imagenes/siguiente.png")));
		GridBagConstraints gbc_btnSiguiente = new GridBagConstraints();
		gbc_btnSiguiente.insets = new Insets(0, 0, 0, 5);
		gbc_btnSiguiente.gridx = 5;
		gbc_btnSiguiente.gridy = 0;
		panel.add(btnSiguiente, gbc_btnSiguiente);
		
		JButton btnEliminar = new JButton("Eliminar de la lista");
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.insets = new Insets(0, 0, 0, 5);
		gbc_btnEliminar.gridx = 6;
		gbc_btnEliminar.gridy = 0;
		panel.add(btnEliminar, gbc_btnEliminar);
	} 

}
