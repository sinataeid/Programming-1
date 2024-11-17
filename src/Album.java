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
    this.artist = artist;
    this.title = title;
    this.releaseYear = releaseYear;
    this.tracks = tracks;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    if (artist.isEmpty()) {
      this.artist = "Unknown";
    } else {
      this.artist = artist;
    }
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    if (title.isEmpty()) {
      this.title = "Untitled";
    } else {
      this.title = title;
    }
  }

  public String getReleaseYear() {
    return title;
  }

  public void setReleaseYear(int releaseYear) {
    this.releaseYear = Math.clamp(releaseYear, 0, Integer.MAX_VALUE);
  }

  public ArrayList<Track> getTracks() {
    return tracks;
  }

  public void setTracks(ArrayList<Track> tracks) {
    this.tracks = tracks;
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
