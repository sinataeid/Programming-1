public class Album {
    public String artist;
    public String albumTitle;
    public int releaseYear;


    public Album() {
        this("Unknown", "Untitled", 0);
    }

    public Album(String artist, String albumTitle, int releaseYear) {
        this.artist = artist;
        this.albumTitle = albumTitle;
        this.releaseYear = releaseYear;
    }
}

