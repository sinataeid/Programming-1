import java.util.ArrayList;

public class AlbumCollection {
  private ArrayList<Album> _albums;

  public AlbumCollection() {
    _albums = new ArrayList<Album>();
  }

  public AlbumCollection(ArrayList<Album> albums) {
    _albums = albums;
  }

  public AlbumCollection(AlbumCollection other) {
    _albums = other._albums;
  }

  public ArrayList<Album> getAlbums() {
    return _albums;
  }

  public void addAlbum(Album album) {
    _albums.add(album);
  }

  public void deleteAlbum(int index) {
    if (index < 0 || index >= _albums.size()) return;
    _albums.remove(index);
  }

  @Override
  public String toString() {
    var sb = new StringBuilder();
    sb.append("[\n");

    for (var album : _albums) {
      sb.append(album.toString().indent(2));
    }

    sb.append("]");
    return sb.toString();
  }

  public static void main(String[] args) {
    var passed = true;

    if (!testConstructorsAndGets()) passed = false;
    if (!testAddAlbum()) passed = false;
    if (!testDeleteAlbum()) passed = false;

    if (passed) System.out.println("Test AlbumCollection::main passed");
    else System.err.println("Error: Test AlbumCollection::main failed");
  }

  public static boolean testConstructorsAndGets() {
    var passed = true;

    {
      var ac = new AlbumCollection();
      if (!ac._albums.isEmpty()) {
        System.err.printf(
            "Error: Test AlbumCollection::testConstructorsAndGets failed: new AlbumCollection() is AlbumCollection{%s}\n",
            ac._albums);
        passed = false;
      }

      var albums = ac.getAlbums();
      if (!albums.isEmpty()) {
        System.err.printf(
            "Error: Test AlbumCollection::testConstructorsAndGets failed: AlbumCollection{%s}.getAlbums() == %s\n",
            ac._albums, albums);
        passed = false;
      }
    }

    {
      var ts = new ArrayList<Track>();
      ts.add(new Track("d", new Duration(0, 5, 21)));
      ts.add(new Track("e", new Duration(0, 4, 4)));
      ts.add(new Track("f", new Duration(0, 3, 38)));

      var as = new ArrayList<Album>();
      as.add(new Album("abc", "def", 2022, ts));

      var ac = new AlbumCollection(as);

      if (!ac._albums.equals(as)) {
        System.err.printf(
            "Error: Test AlbumCollection::testConstructorsAndGets failed: new AlbumCollection(%s) is AlbumCollection{%s}\n",
            as, ac._albums);
        passed = false;
      }

      var albums = ac.getAlbums();
      if (!albums.equals(as)) {
        System.err.printf(
            "Error: Test AlbumCollection::testConstructorsAndGets failed: AlbumCollection{%s}.getAlbums() == %s\n",
            ac._albums, albums);
        passed = false;
      }
    }

    return passed;
  }

  public static boolean testAddAlbum() {
    var passed = true;

    var ts = new ArrayList<Track>();
    ts.add(new Track("j", new Duration(0, 5, 21)));
    ts.add(new Track("k", new Duration(0, 4, 4)));
    ts.add(new Track("l", new Duration(0, 3, 38)));

    var a1 = new Album("abc", "def", 2022);
    var a2 = new Album("ghi", "jkl", 2023, ts);

    var as = new ArrayList<Album>();
    as.add(a1);
    as.add(a2);

    var ac = new AlbumCollection();
    ac.addAlbum(a1);
    ac.addAlbum(a2);

    if (!ac._albums.equals(as)) {
      System.err.printf(
          "Error: Test AlbumCollection::testAddAlbum failed: albumCollection = new AlbumCollection(); albumCollection.addAlbum(Album{\"%s\", \"%s\", %d, %s}); albumCollection.addAlbum(Album{\"%s\", \"%s\", %d, %s}); albumCollection is AlbumCollection{%s}\n",
          a1.getArtist(), a1.getTitle(), a1.getReleaseYear(), a1.getTracks(), a1.getArtist(), a1.getTitle(), a1.getReleaseYear(), a1.getTracks(), ac._albums);
      passed = false;
    }

    return passed;
  }

  public static boolean testDeleteAlbum() {
    var passed = true;

    var ts = new ArrayList<Track>();
    ts.add(new Track("j", new Duration(0, 5, 21)));
    ts.add(new Track("k", new Duration(0, 4, 4)));
    ts.add(new Track("l", new Duration(0, 3, 38)));

    var a1 = new Album("abc", "def", 2022);
    var a2 = new Album("ghi", "jkl", 2023, ts);

    var as = new ArrayList<Album>();
    as.add(a1);
    as.add(a2);

    var ac = new AlbumCollection(as);
    var acCopy = new AlbumCollection(ac);

    as.remove(0);
    ac.deleteAlbum(0);

    if (!ac._albums.equals(as)) {
      System.err.printf(
          "Error: Test AlbumCollection::testDeleteAlbum failed: albumCollection{%s}.deleteAlbum(0); albumCollection is AlbumCollection{%s}\n",
          acCopy._albums, ac._albums);
      passed = false;
    }

    acCopy = new AlbumCollection(ac);
    var size = ac._albums.size();
    ac.deleteAlbum(size);

    if (!ac._albums.equals(as)) {
      System.err.printf(
          "Error: Test AlbumCollection::testDeleteAlbum failed: albumCollection{%s}.deleteAlbum(%d); albumCollection is AlbumCollection{%s}\n",
          acCopy._albums, size, ac._albums);
      passed = false;
    }

    return passed;
  }
}
