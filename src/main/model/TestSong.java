package main.model;

public class TestSong {
    public static void main(String[] args) {
        Playlist p = new Playlist("Favourites");

        Song s1 = new Song("Believer", "Imagine Dragons", "250");
        Song s2 = new Song("Fear of the Dark", "Iron Maiden", "420");

        p.addSong(s1);
        p.addSong(s2);

        p.printPlaylist();
    }
}



