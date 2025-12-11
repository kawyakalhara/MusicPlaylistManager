package main.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PlaylistManager {

    private Map<String, Playlist> playlists;

    public PlaylistManager() {
        playlists = new HashMap<>();
    }

    public void createPlaylist(String name) {
        if (!playlists.containsKey(name)) {
            playlists.put(name, new Playlist(name));
        } else {
            System.out.println("Playlist already exists: " + name);
        }
    }

    public Playlist getPlaylist(String name) {
        return playlists.get(name);
    }

    public void deletePlaylist(String name) {
        if (playlists.containsKey(name)) {
            playlists.remove(name);
        } else {
            System.out.println("Playlist not found: " + name);
        }
    }

    public void listPlaylists() {
        for (String name : playlists.keySet()) {
            System.out.println(name);
        }
    }

    public Set<String> getAllPlaylistNames() {
        return playlists.keySet();
    }
}

