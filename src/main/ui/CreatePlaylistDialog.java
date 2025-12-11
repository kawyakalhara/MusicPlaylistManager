package main.ui;

import main.model.PlaylistManager;

import javax.swing.*;
import java.awt.*;

    public class CreatePlaylistDialog extends JDialog {

        private PlaylistManager manager;
        private JTextField playlistNameField;
        private JButton createButton;
        private JButton cancelButton;

        public CreatePlaylistDialog(JFrame parent, PlaylistManager manager) {
            super(parent, "Create New Playlist", true);
            this.manager = manager;

            setSize(300, 150);
            setLocationRelativeTo(parent);
            setLayout(new BorderLayout());

            initComponents();
        }

        private void initComponents() {
            // Input panel
            JPanel inputPanel = new JPanel(new GridLayout(2, 1));
            inputPanel.add(new JLabel("Playlist Name:"));
            playlistNameField = new JTextField();
            inputPanel.add(playlistNameField);

            add(inputPanel, BorderLayout.CENTER);

            // Buttons panel
            JPanel buttonPanel = new JPanel();
            createButton = new JButton("Create");
            cancelButton = new JButton("Cancel");

            buttonPanel.add(createButton);
            buttonPanel.add(cancelButton);
            add(buttonPanel, BorderLayout.SOUTH);

            // Button actions
            createButton.addActionListener(e -> createPlaylist());
            cancelButton.addActionListener(e -> dispose());
        }

        private void createPlaylist() {
            String name = playlistNameField.getText().trim();
            if (!name.isEmpty()) {
                if (manager.getPlaylist(name) == null) {
                    manager.createPlaylist(name);
                    JOptionPane.showMessageDialog(this, "Playlist created: " + name);
                    dispose(); // close dialog
                } else {
                    JOptionPane.showMessageDialog(this, "Playlist already exists!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Playlist name cannot be empty!");
            }
        }

        // Launch dialog
        public void launch() {
            SwingUtilities.invokeLater(() -> setVisible(true));
        }
    }


