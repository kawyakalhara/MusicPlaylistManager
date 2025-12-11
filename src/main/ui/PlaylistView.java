package main.ui;

import main.model.Playlist;
import main.model.Song;

import javax.swing.*;
import java.awt.*;

    public class PlaylistView extends JFrame {

        private Playlist playlist;
        private DefaultListModel<String> songListModel;
        private JList<String> songList;
        private JButton playButton;
        private JButton shuffleButton;

        public PlaylistView(Playlist playlist) {
            this.playlist = playlist;

            setTitle("Playlist: " + playlist.getName());
            setSize(400, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // only closes this window

            initComponents();
        }

        private void initComponents() {
            setLayout(new BorderLayout());

            // Song list
            songListModel = new DefaultListModel<>();
            for (Song song : playlist.getSongs()) {
                songListModel.addElement(song.toString());
            }

            songList = new JList<>(songListModel);
            add(new JScrollPane(songList), BorderLayout.CENTER);

            // Buttons at bottom
            JPanel buttonPanel = new JPanel();
            playButton = new JButton("Play Sequentially");
            shuffleButton = new JButton("Shuffle Play");

            buttonPanel.add(playButton);
            buttonPanel.add(shuffleButton);

            add(buttonPanel, BorderLayout.SOUTH);

            playButton.addActionListener(e -> playSequential());
            shuffleButton.addActionListener(e -> playShuffled());
        }

        private void playSequential() {
            System.out.println("Playing playlist sequentially: " + playlist.getName());
            for (Song song : playlist.getSongs()) {
                System.out.println("Now playing: " + song);
            }
        }

        private void playShuffled() {
            System.out.println("Playing playlist shuffled: " + playlist.getName());
            java.util.List<Song> shuffled = new java.util.ArrayList<>(playlist.getSongs());
            java.util.Collections.shuffle(shuffled);
            for (Song song : shuffled) {
                System.out.println("Now playing: " + song);
            }
        }

        public void launch() {
            SwingUtilities.invokeLater(() -> setVisible(true));
        }
    }


