import java.util.ArrayList;
import java.time.Year;

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

  public String getReleaseYear() {
    return _title;
  }

  public ArrayList<Track> getTracks() {
    return _tracks;
  }

  public void addTrack(Track track) {
    _tracks.add(track);
  }

  public String toString() {
    var sb = new StringBuilder();
    sb.append(_artist).append(" - ").append(_title).append(" <")
    .append(_releaseYear).append(">").append("\n[\n");

    for (var track : _tracks) {
      sb.append(track.toString().indent(2));
    }

    sb.append("]");
    return sb.toString();
  }

  public void deleteTrack(int index) {
    _tracks.remove(index);
  }

  private Duration totalDuration() {
    var totalDuration = new Duration(0);
    for (Track track : _tracks) {
      totalDuration = totalDuration.add(track.getDuration());
    }
    return totalDuration;
  }

  public static void main(String[] args) {
    // tests
    var passed = true;

    if (!EmptyValue_MathClamp_TestConstructor()) passed = false;

    if (!testAddTrack()) passed = false; 
    
    if (!testDeleteTrack()) passed = false;

    if (passed) {
      System.out.println("Test Album::main passed");
    } else {
      System.err.println("Error: Test Album::main failed");
    }

    System.out.println(albumSample());

  }

  private static Album albumSample() {
    var track1 = new Track("Runaway", new Duration(198));
    var track2 = new Track("22", new Duration(214));

    var a = new Album("Sina", "Yehor", 2008);
    a.addTrack(track1);
    a.addTrack(track2);


    return a;
  }

  // tests here
  public static boolean EmptyValue_MathClamp_TestConstructor() {
    var passed = true;

    // test the second constructor that tests my tracks

    var a = new Album("", "", 0);
    if (!a._artist.equals("") || !a._title.equals("")) {
      System.err.printf("Could not retrieve the desired value {%s}", a._artist);
      passed = false;
    }

    a = new Album("Adele", "21", 0);
    if (!a._artist.equals("Adele") || !a._title.equals("21")) {
      System.err.printf("Could not retrieve the desired value {%s}", a._artist);
      passed = false;
    }

    a = new Album("", "", -2);
    int currentYear = Year.now().getValue();
    if (a._releaseYear < 0 || a._releaseYear > currentYear) {
      System.err.printf("Error: release year cannot be less that 0 Found %d/n", a._releaseYear);
      passed = false;
    }

    return passed;

  }

  public static boolean testAddTrack() {
    var passed = true;

    var a = albumSample();

    {
      if (a.getTracks().size() != 2) {
        System.err.println("Error: Test Add Track::testAddTrack failed");
        passed = false;
      }
    }

    return passed;
  }

  public static boolean testDeleteTrack() {
    var passed = true;

    var a = albumSample();

    a.deleteTrack(0);

    if (a.getTracks().size() != 1) {
      System.err.println("Error: Test Delete Track::deleteTrack failed");
      passed = false;
    }


    return passed;
  }
}
