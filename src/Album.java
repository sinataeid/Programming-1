import java.util.ArrayList;

public class Album {
  private String artist;
  private String title;
  private int releaseYear;
  public ArrayList<Track> tracks;

  public Album() {
    this("Unknown", "Untitled", 0);
  }

  public Album(String artist, String title, int releaseYear) {
    this(artist, title, releaseYear, new ArrayList<Track>());
  }

  public Album(String artist, String title, int releaseYear, ArrayList<Track> tracks) {

    if (artist.isEmpty()) {
      this.artist = "Unknown";
    } else {
      this.artist = artist;
    }

    if (title.isEmpty()) {
      this.title = "Untitled";
    } else {
      this.title = title;
    }

    this.releaseYear = Math.clamp(releaseYear, 0, Integer.MAX_VALUE);
    this.tracks = tracks;

  }

  public String getArtist() {
    return artist;
  }

  public String getTitle() {
    return title;
  }

  public String getReleaseYear() {
    return title;
  }

  public ArrayList<Track> getTracks() {
    return tracks;
  }

  public void addTrack(Track track) {
    tracks.add(track);
  }

  public String toString() {
    var totalDuration = new Duration(42, 0, 0);
    return String.format("%s - %s <%d> (%s)", artist, title, releaseYear, totalDuration);
  }

  public static void main(String[] args) {
    // tests
  }

  // tests here

}
