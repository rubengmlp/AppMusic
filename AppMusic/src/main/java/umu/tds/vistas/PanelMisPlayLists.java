package umu.tds.vistas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import umu.tds.controlador.AppMusic;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.PlayList;
import umu.tds.dominio.repositorios.BDException;
import umu.tds.persistencia.DAOException;

public class PanelMisPlayLists extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultListModel<String> listModel;
    private List<PlayList> playLists;
    private List<Cancion> canciones;

    /**
     * Create the panel.
     * 
     * @throws BDException
     * @throws DAOException
     */
    public PanelMisPlayLists() throws DAOException, BDException {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 0, 0 };
        gridBagLayout.rowHeights = new int[] { 394, 25, 0 };
        gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
        setLayout(gridBagLayout);

        JPanel panel_1 = new JPanel();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.insets = new Insets(0, 0, 5, 0);
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 0;
        add(panel_1, gbc_panel_1);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[] { 269, 0, 0 };
        gbl_panel_1.rowHeights = new int[] { 0, 0 };
        gbl_panel_1.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
        gbl_panel_1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
        panel_1.setLayout(gbl_panel_1);

        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 0;
        panel_1.add(scrollPane, gbc_scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Título", "Intérprete", "Estilo" }));
        scrollPane.setViewportView(table);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBorder(new TitledBorder(null, "PlayLists", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
        gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_1.gridx = 1;
        gbc_scrollPane_1.gridy = 0;
        panel_1.add(scrollPane_1, gbc_scrollPane_1);

        listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        scrollPane_1.setViewportView(list);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = list.getSelectedIndex();
                    if (selectedIndex != -1) {
                        PlayList selectedPlaylist = playLists.get(selectedIndex);
                        actualizarTabla(selectedPlaylist);
                    }
                }
            }
        });

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 1;
        add(panel, gbc_panel);

        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 20, 89, 89, 89, 89, 89, 0, 20, 0 };
        gbl_panel.rowHeights = new int[] { 0, 0 };
        gbl_panel.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);

        JButton btnAtras = new JButton("");
        btnAtras.setIcon(new ImageIcon(PanelBuscar.class.getResource("/umu/tds/imagenes/atras.png")));
        btnAtras.addActionListener(new ActionListener() {
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

        GridBagConstraints gbc_btnAtras = new GridBagConstraints();
        gbc_btnAtras.insets = new Insets(0, 0, 0, 5);
        gbc_btnAtras.gridx = 1;
        gbc_btnAtras.gridy = 0;
        panel.add(btnAtras, gbc_btnAtras);

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

        actualizar();
    }

    public void actualizar() throws DAOException, BDException {
        playLists = AppMusic.getUnicaInstancia().getPlayListsUsuario();
        
        // Limpiar el modelo antes de agregar las nuevas playlists
        listModel.clear();
        
        // Agregar las playlists al modelo de la lista
        for (PlayList playlist : playLists) {
            listModel.addElement(playlist.getNombre());
        }
    }

    private void actualizarTabla(PlayList selectedPlaylist) {
        DefaultTableModel modeloTabla = (DefaultTableModel) table.getModel();
        //Se limpia la tabla
        modeloTabla.setRowCount(0);
        
        this.canciones = selectedPlaylist.getCanciones();
        
        for (Cancion cancion : selectedPlaylist.getCanciones()) {
            modeloTabla.addRow(new Object[] { cancion.getTitulo(), cancion.getInterprete(), cancion.getEstilo() });
        }
    }

}
