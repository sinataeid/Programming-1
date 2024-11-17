
import java.util.ArrayList;

public class Album {
    public String artist;
    public String albumTitle;
    public int releaseYear;
    private ArrayList<Track> tracks;

    public Album() {
        this("Unknown", "Untitled", 0);
    }

    public Album(String artist, String albumTitle, int releaseYear) {
        this.artist = artist;
        this.albumTitle = albumTitle;
        this.releaseYear = releaseYear;
    }

    public static void main(String[] args) {
        
    }

    //tests here
}

