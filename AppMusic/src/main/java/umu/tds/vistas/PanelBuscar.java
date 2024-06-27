package umu.tds.vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URISyntaxException;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import pulsador.IEncendidoListener;
import pulsador.Luz;
import umu.tds.controlador.AppMusic;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.repositorios.BDException;
import umu.tds.persistencia.DAOException;

public class PanelBuscar extends JPanel {

	private static final long serialVersionUID = 1L;
	private List<Cancion> canciones;
	private JTextField textFieldTitulo;
	private JTextField textFieldInterprete;
	private JTable table;
	private List<Cancion> seleccionadas = new LinkedList<Cancion>();
	private List<Cancion> currentlySelected = new LinkedList<Cancion>();

	/**
	 * Create the panel.
	 * @throws BDException 
	 * @throws DAOException 
	 */
	public PanelBuscar() throws DAOException, BDException {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 150, 150, 25, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 10, 50, 120, 10, 0, 10, 0 };
		gbl_panel_1.rowHeights = new int[] { 10, 0, 10, 0, 10, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblTitulo = new JLabel("Título");
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.anchor = GridBagConstraints.EAST;
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 1;
		panel_1.add(lblTitulo, gbc_lblTitulo);

		textFieldTitulo = new JTextField();
		GridBagConstraints gbc_textFieldTitulo = new GridBagConstraints();
		gbc_textFieldTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTitulo.gridx = 2;
		gbc_textFieldTitulo.gridy = 1;
		panel_1.add(textFieldTitulo, gbc_textFieldTitulo);
		textFieldTitulo.setColumns(10);

		JCheckBox chckbxFav = new JCheckBox("Favoritas");
		GridBagConstraints gbc_chckbxFav = new GridBagConstraints();
		gbc_chckbxFav.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxFav.gridx = 4;
		gbc_chckbxFav.gridy = 2;
		panel_1.add(chckbxFav, gbc_chckbxFav);

		JLabel lblInterpete = new JLabel("Intérpete");
		GridBagConstraints gbc_lblInterpete = new GridBagConstraints();
		gbc_lblInterpete.anchor = GridBagConstraints.EAST;
		gbc_lblInterpete.insets = new Insets(0, 0, 5, 5);
		gbc_lblInterpete.gridx = 1;
		gbc_lblInterpete.gridy = 3;
		panel_1.add(lblInterpete, gbc_lblInterpete);

		textFieldInterprete = new JTextField();
		textFieldInterprete.setColumns(10);
		GridBagConstraints gbc_textFieldInterprete = new GridBagConstraints();
		gbc_textFieldInterprete.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldInterprete.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldInterprete.gridx = 2;
		gbc_textFieldInterprete.gridy = 3;
		panel_1.add(textFieldInterprete, gbc_textFieldInterprete);

		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscar.gridx = 4;
		gbc_btnBuscar.gridy = 3;
		JButton btnBuscar = new JButton("Buscar");
		panel_1.add(btnBuscar, gbc_btnBuscar);

		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 4;
		gbc_panel_3.gridy = 4;
		panel_1.add(panel_3, gbc_panel_3);

		JLabel lblNewLabel = new JLabel("Cargar de XML");
		panel_3.add(lblNewLabel);

		Luz luz = new Luz();
		panel_3.add(luz);
		luz.addEncendidoListener(new IEncendidoListener() {
			public void enteradoCambioEncendido(EventObject e) {
				if (luz.isEncendido()) {
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filtro = new FileNameExtensionFilter("\"*.xml\"", "xml");
					chooser.setFileFilter(filtro);
					chooser.setAcceptAllFileFilterUsed(false);
					chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					File currentDirectory = new File("xml");
					chooser.setCurrentDirectory(currentDirectory);
					int resultado = chooser.showOpenDialog(chooser);
					if (resultado == JFileChooser.APPROVE_OPTION) {
						File file = chooser.getSelectedFile();
						String archivo = file.getAbsolutePath();
						try {
							AppMusic.getUnicaInstancia().cargarCanciones(archivo);
						} catch (URISyntaxException | DAOException | BDException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});

		JLabel lblEstilo = new JLabel("Estilo");
		GridBagConstraints gbc_lblEstilo = new GridBagConstraints();
		gbc_lblEstilo.anchor = GridBagConstraints.EAST;
		gbc_lblEstilo.insets = new Insets(0, 0, 0, 5);
		gbc_lblEstilo.gridx = 1;
		gbc_lblEstilo.gridy = 5;
		panel_1.add(lblEstilo, gbc_lblEstilo);

		JComboBox comboBoxEstilo = new JComboBox();
		comboBoxEstilo.setModel(new DefaultComboBoxModel(
				new String[] { "", "CLASICA", "FLAMENCO", "OPERA", "POP", "ROCK", "ROMANTICA" }));
		GridBagConstraints gbc_comboBoxEstilo = new GridBagConstraints();
		gbc_comboBoxEstilo.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxEstilo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEstilo.gridx = 2;
		gbc_comboBoxEstilo.gridy = 5;
		panel_1.add(comboBoxEstilo, gbc_comboBoxEstilo);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setMinimumSize(new Dimension(0, 0));
		scrollPane.setMaximumSize(new Dimension(200, 200));
		panel_2.add(scrollPane);

		table = new JTable();
		table.setMinimumSize(new Dimension(100, 50));
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "T\u00EDtulo", "Int\u00E9rprete", "Estilo", "Seleccionada" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(2).setPreferredWidth(77);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 20, 89, 89, 89, 89, 89, 0, 20, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
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

		JButton btnPausa = new JButton("");
		btnPausa.addActionListener(new ActionListener() {
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

		btnPausa.setIcon(new ImageIcon(PanelBuscar.class.getResource("/umu/tds/imagenes/pausa.png")));
		GridBagConstraints gbc_btnPausa = new GridBagConstraints();
		gbc_btnPausa.insets = new Insets(0, 0, 0, 5);
		gbc_btnPausa.gridx = 4;
		gbc_btnPausa.gridy = 0;
		panel.add(btnPausa, gbc_btnPausa);

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
					// TODO Auto-generated catch block
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

		JButton btnAdd = new JButton("Añadir a lista");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Cancion> seleccionadas = new LinkedList<Cancion>();
				for (int i = 0; i < table.getRowCount(); i++) {
					boolean seleccionada = (boolean) table.getValueAt(i, 3);
					if (seleccionada) {
						seleccionadas.add(canciones.get(i));
					}
				}

				if (seleccionadas.isEmpty()) {
					JOptionPane.showMessageDialog(PanelBuscar.this,
							"Seleccione al menos una canción para añadir a la playlist", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					DialogAnadir dialog = new DialogAnadir((Frame) SwingUtilities.getWindowAncestor(PanelBuscar.this));
					dialog.setCancionesSeleccionadas(seleccionadas);
					dialog.setVisible(true);
				} catch (DAOException | BDException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.gridx = 6;
		gbc_btnAdd.gridy = 0;
		panel.add(btnAdd, gbc_btnAdd);

		btnBuscar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            canciones = AppMusic.getUnicaInstancia().getTodasCanciones();

		            if (!comboBoxEstilo.getSelectedItem().toString().isEmpty()) {
		                canciones = AppMusic.getUnicaInstancia().filtroEstilo(comboBoxEstilo.getSelectedItem().toString(), canciones);
		            }

		            if (!textFieldTitulo.getText().isBlank()) {
		                canciones = AppMusic.getUnicaInstancia().filtroTitulo(textFieldTitulo.getText(), canciones);
		            }

		            if (!textFieldInterprete.getText().isBlank()) {
		                canciones = AppMusic.getUnicaInstancia().filtroInterprete(textFieldInterprete.getText(), canciones);
		            }

		            if (chckbxFav.isSelected()) {
		                canciones = AppMusic.getUnicaInstancia().filtroFavoritas(canciones);
		            }

		            DefaultTableModel modeloTabla = (DefaultTableModel) table.getModel();
		            modeloTabla.setRowCount(0);

		            for (Cancion c : canciones) {
		                modeloTabla.addRow(new Object[] { c.getTitulo(), c.getInterprete(), c.getEstilo(), false });
		            }

		        } catch (DAOException | BDException ex) {
		            ex.printStackTrace();
		            
		        }
		    }
		});
		

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting()) {
		            // Obtener las filas actualmente seleccionadas
		            int[] selectedRows = table.getSelectedRows();
		            Set<Cancion> newCurrentlySelected = new HashSet<>();

		            for (int row : selectedRows) {
		                newCurrentlySelected.add(canciones.get(row));
		            }

		            // Añadir nuevas selecciones
		            for (Cancion cancion : newCurrentlySelected) {
		                if (!currentlySelected.contains(cancion)) {
		                    currentlySelected.add(cancion);
		                }
		            }

		            // Eliminar deselecciones
		            currentlySelected.removeIf(cancion -> !newCurrentlySelected.contains(cancion));

		            // Actualizar la lista en AppMusic
		            try {
		                AppMusic.getUnicaInstancia().setListaCancionesSeleccionadas(new ArrayList<>(currentlySelected));
		            } catch (DAOException | BDException e1) {
		                e1.printStackTrace();
		            }
		        }
		    }
		});




	}

}
