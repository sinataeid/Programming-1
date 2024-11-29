import java.util.ArrayList;

public class Album {
  private String _artist;
  private String _title;
  private int _releaseYear;
  private ArrayList<Track> _tracks;

  public Album(String artist, String title, int releaseYear) {
    this(artist, title, releaseYear, new ArrayList<Track>());
  }

  public Album(String artist, String title, int releaseYear, ArrayList<Track> tracks) {
    _artist = artist;
    _title = title;
    _releaseYear = Math.max(0, releaseYear);
    _tracks = tracks;
  }

  public Album(Album other) {
    _artist = other._artist;
    _title = other._title;
    _releaseYear = other._releaseYear;
    _tracks = other._tracks;
  }

  public String getArtist() {
    return _artist;
  }

  public String getTitle() {
    return _title;
  }

  public int getReleaseYear() {
    return _releaseYear;
  }

  public ArrayList<Track> getTracks() {
    return _tracks;
  }

  public Duration getTotalDuration() {
    // TODO: test it
    var totalDuration = new Duration(0);
    for (Track track : _tracks) {
      totalDuration = totalDuration.add(track.getDuration());
    }
    return totalDuration;
  }

  public void addTrack(Track track) {
    _tracks.add(track);
  }

  public void deleteTrack(int index) {
    _tracks.remove(index);
  }

  public String toString() {
    var sb = new StringBuilder();
    sb.append(_artist).append(" - ").append(_title).append(" <")
        .append(_releaseYear).append("> (").append(getTotalDuration()).append(")").append("\n[\n");

    for (var track : _tracks) {
      sb.append(track.toString().indent(2));
    }

    sb.append("]");
    return sb.toString();
  }

  public static void main(String[] args) {
    // tests
    var passed = true;

    if (!testConstructorsAndGets())
      passed = false;
    if (!testAddTrack())
      passed = false;
    if (!testDeleteTrack())
      passed = false;

    if (passed) {
      System.out.println("Test Album::main passed");
    } else {
      System.err.println("Error: Test Album::main failed");
    }
  }

  public static boolean testConstructorsAndGets() {
    var passed = true;

    ArrayList<Track> trackArray = new ArrayList<>();
    var track1 = new Track("Redbull", new Duration(120));
    trackArray.add(track1);
    var a = new Album("Adele", "21", 2015, trackArray);

    if (!a._artist.equals("Adele") || !a._title.equals("21") || a._releaseYear != 2015) {
      System.err.println("Error: Test Album::testConstructorsAndGets failed: testConstructor");
      passed = false;
    }

    var artist = a.getArtist();
    if (!artist.equals("Adele")) {
      System.err.println(
          "2 Error: Test Album::testConstructorsAndGets failed: getArtist");
      passed = false;
    }

    var title = a.getTitle();
    if (!title.equals("21")) {
      System.err.println(
          "3 Error: Test Album::testConstructorsAndGets failed: getTitle");
      passed = false;
    }

    var releaseYear = a.getReleaseYear();
    if (releaseYear != 2015) {
      System.err.println(
          "4 Error: Test Album::testConstructorsAndGets failed: getReleaseYear failed: getReleaseYear");
      passed = false;
    }

    var tracks = a.getTracks();
    if (!trackArray.equals(tracks)) {
      System.err.println(
          "Error: Test Album::testConstructorsAndGets failed: getTracks");
      passed = false;
    }

    return passed;
  }

  public static boolean testAddTrack() {
    var passed = true;

    var track1 = new Track("Runaway", new Duration(198));
    var track2 = new Track("22", new Duration(214));

    var a = new Album("Sina", "Yehor", 2008);
    a.addTrack(track1);
    a.addTrack(track2);

    if (a.getTracks().size() != 2) {
      System.err.println("Error: Test Add Track::testAddTrack failed");
      passed = false;
    }

    return passed;
  }

  public static boolean testDeleteTrack() {
    var passed = true;

    var track1 = new Track("Runaway", new Duration(198));
    var track2 = new Track("22", new Duration(214));

    var a = new Album("Sina", "Yehor", 2008);
    a.addTrack(track1);
    a.addTrack(track2);

    a.deleteTrack(0);

    if (a.getTracks().size() != 1) {
      System.err.println("Error: Test Delete Track::deleteTrack failed");
      passed = false;
    }

    return passed;
  }

  // test getters and rewrite my tests in album which is consistent
}
