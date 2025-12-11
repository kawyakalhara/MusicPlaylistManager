package main.model;

public class TestPlaylistManager {

        public static void main(String[] args) {
            PlaylistManager pm = new PlaylistManager();

            pm.createPlaylist("Rock");
            pm.createPlaylist("Chill");

            Playlist rock = pm.getPlaylist("Rock");

            rock.addSong(new Song("Numb", "Linkin Park", "185"));

            pm.listPlaylists();
            rock.printPlaylist();
        }
    }


