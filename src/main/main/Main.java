package main.main;

import main.model.Song;
import main.model.Playlist;
import main.model.PlaylistManager;
import main.ui.MainWindow;



    public class Main {
        public static void main(String[] args) {

            PlaylistManager manager = new PlaylistManager();

            Song s1 = new Song("Shape of You", "Ed Sheeran", "3:53");
            Song s2 = new Song("Blinding Lights", "The Weeknd", "3:20");
            Song s3 = new Song("Bad Habits", "Ed Sheeran", "3:51");
            Song s4 = new Song("Levitating", "Dua Lipa", "3:23");
            Song s5 = new Song("Watermelon Sugar", "Harry Styles", "2:54");
            Song s6 = new Song("Peaches", "Justin Bieber", "3:18");
            Song s7 = new Song("Drivers License", "Olivia Rodrigo", "4:02");
            Song s8 = new Song("Save Your Tears", "The Weeknd", "3:35");
            Song s9 = new Song("Stay", "The Kid LAROI", "2:21");
            Song s10 = new Song("Shivers", "Ed Sheeran", "3:27");

            Playlist chillVibes = new Playlist("Chill Vibes");
            chillVibes.addSong(s1);
            chillVibes.addSong(s4);
            chillVibes.addSong(s5);
            chillVibes.addSong(s6);

            Playlist popHits = new Playlist("Pop Hits");
            popHits.addSong(s2);
            popHits.addSong(s3);
            popHits.addSong(s9);
            popHits.addSong(s10);

            Playlist sadSongs = new Playlist("Sad Songs");
            sadSongs.addSong(s7);
            sadSongs.addSong(s8);

            manager.createPlaylist(chillVibes.getName());
            manager.getPlaylist(chillVibes.getName()).getSongs().addAll(chillVibes.getSongs());

            manager.createPlaylist(popHits.getName());
            manager.getPlaylist(popHits.getName()).getSongs().addAll(popHits.getSongs());

            manager.createPlaylist(sadSongs.getName());
            manager.getPlaylist(sadSongs.getName()).getSongs().addAll(sadSongs.getSongs());

            System.out.println("=== Preloaded Playlists ===");
            for (String playlistName : manager.getAllPlaylistNames()) {
                System.out.println("Playlist: " + playlistName);
                Playlist p = manager.getPlaylist(playlistName);
                for (Song song : p.getSongs()) {
                    System.out.println("  " + song);
                }
            }




            MainWindow.launch(manager);
        }
    }


