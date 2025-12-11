package main.ui;

import main.model.Playlist;
import main.model.PlaylistManager;
import main.model.Song;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private PlaylistManager manager;

    private JList<String> playlistList;
    private DefaultListModel<String> playlistListModel;

    private JList<String> songList;
    private DefaultListModel<String> songListModel;

    private JButton addSongButton;
    private JButton removeSongButton;
    private JButton playButton;
    private JButton createPlaylistButton;
    private JButton openPlaylistButton;

    public MainWindow(PlaylistManager manager) {
        this.manager = manager;
        setTitle("Music Playlist Manager");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        // Left panel: Playlists
        playlistListModel = new DefaultListModel<>();
        refreshPlaylistList();
        playlistList = new JList<>(playlistListModel);
        playlistList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playlistList.addListSelectionListener(e -> updateSongList());

        panel.add(new JScrollPane(playlistList), BorderLayout.WEST);

        // Center panel: Songs
        songListModel = new DefaultListModel<>();
        songList = new JList<>(songListModel);
        panel.add(new JScrollPane(songList), BorderLayout.CENTER);

        // Bottom panel: Buttons
        JPanel buttonPanel = new JPanel();
        addSongButton = new JButton("Add Song");
        removeSongButton = new JButton("Remove Song");
        playButton = new JButton("Play Playlist");
        createPlaylistButton = new JButton("Create Playlist");
        openPlaylistButton = new JButton("Open Playlist");

        buttonPanel.add(addSongButton);
        buttonPanel.add(removeSongButton);
        buttonPanel.add(playButton);
        buttonPanel.add(openPlaylistButton);
        buttonPanel.add(createPlaylistButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        addSongButton.addActionListener(e -> addSong());
        removeSongButton.addActionListener(e -> removeSong());
        playButton.addActionListener(e -> playPlaylist());
        createPlaylistButton.addActionListener(e -> openCreatePlaylistDialog());
        openPlaylistButton.addActionListener(e -> openPlaylistView());
    }

    // Refresh playlist list from manager
    private void refreshPlaylistList() {
        if (playlistListModel != null) {
            playlistListModel.clear();
            for (String name : manager.getAllPlaylistNames()) {
                playlistListModel.addElement(name);
            }
        }
    }

    private void updateSongList() {
        songListModel.clear();
        String selectedPlaylistName = playlistList.getSelectedValue();
        if (selectedPlaylistName != null) {
            Playlist playlist = manager.getPlaylist(selectedPlaylistName);
            for (Song song : playlist.getSongs()) {
                songListModel.addElement(song.toString());
            }
        }
    }

    private void addSong() {
        String selectedPlaylistName = playlistList.getSelectedValue();
        if (selectedPlaylistName != null) {
            String title = JOptionPane.showInputDialog(this, "Enter song title:");
            String artist = JOptionPane.showInputDialog(this, "Enter artist name:");
            String duration = JOptionPane.showInputDialog(this, "Enter duration (e.g., 3:45):");

            if (title != null && artist != null && duration != null) {
                Song newSong = new Song(title, artist, duration);
                manager.getPlaylist(selectedPlaylistName).addSong(newSong);
                updateSongList();
            }
        }
    }

    private void removeSong() {
        String selectedPlaylistName = playlistList.getSelectedValue();
        int selectedSongIndex = songList.getSelectedIndex();
        if (selectedPlaylistName != null && selectedSongIndex >= 0) {
            Playlist playlist = manager.getPlaylist(selectedPlaylistName);
            playlist.getSongs().remove(selectedSongIndex);
            updateSongList();
        }
    }

    private void playPlaylist() {
        String selectedPlaylistName = playlistList.getSelectedValue();
        if (selectedPlaylistName != null) {
            Playlist playlist = manager.getPlaylist(selectedPlaylistName);
            System.out.println("Playing playlist: " + playlist.getName());
            for (Song song : playlist.getSongs()) {
                System.out.println("Now playing: " + song);
            }
        }
    }

    private void openPlaylistView() {
        String selectedPlaylistName = playlistList.getSelectedValue();
        if (selectedPlaylistName != null) {
            Playlist p = manager.getPlaylist(selectedPlaylistName);
            PlaylistView view = new PlaylistView(p);
            view.launch();
        }
    }

    private void openCreatePlaylistDialog() {
        CreatePlaylistDialog dialog = new CreatePlaylistDialog(this, manager);
        dialog.launch();

        // Refresh playlist list after creating new playlist
        refreshPlaylistList();
    }

    // Launch window
    public static void launch(PlaylistManager manager) {
        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow(manager);
            window.setVisible(true);
        });
    }
}
