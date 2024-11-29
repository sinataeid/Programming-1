import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class AlbumDatabase {
  private static AlbumCollection readAlbumCollectionFile(String fileName) throws Exception {
    var result = new AlbumCollection();

    var scanner = new Scanner(new File(fileName));
    var albumDeclarationPattern = Pattern.compile("^(.+) : (.+) \\((\\d+)\\)$", Pattern.MULTILINE);
    var trackDeclarationPattern = Pattern.compile("^(\\d{1,2}):(\\d{1,2}):(\\d{1,2}) - (.+)$", Pattern.MULTILINE);

    var line = "";
    var readNext = true;
    while (scanner.hasNextLine()) {
      if (readNext) {
        line = scanner.nextLine();
      }

      readNext = true;

      var albumMatcher = albumDeclarationPattern.matcher(line);
      if (!albumMatcher.find()) {
        throw new UnsupportedOperationException(String.format("Unrecognised line \"%s\"", line));
      }

      var albumArtist = albumMatcher.group(1);
      var albumTitle = albumMatcher.group(2);
      var albumReleaseYear = Integer.parseInt(albumMatcher.group(3));
      var album = new Album(albumArtist, albumTitle, albumReleaseYear);

      while (scanner.hasNextLine()) {
        line = scanner.nextLine();

        var trackMatcher = trackDeclarationPattern.matcher(line);
        if (!trackMatcher.find()) {
          readNext = false;
          break;
        }

        var trackTitle = trackMatcher.group(4);
        var trackDurationHours = Integer.parseInt(trackMatcher.group(1));
        var trackDurationMinutes = Integer.parseInt(trackMatcher.group(2));
        var trackDurationSeconds = Integer.parseInt(trackMatcher.group(3));
        var trackDuration = new Duration(trackDurationHours, trackDurationMinutes, trackDurationSeconds);
        var track = new Track(trackTitle, trackDuration);
        album.addTrack(track);
      }
      
      result.addAlbum(album);
    }

    return result;
  }

  public static void main(String[] args) {
    try {
      var albumCollection = readAlbumCollectionFile("./albums.txt");


      // task 2
      albumCollection.getAlbums().sort(Comparator.comparing(Album::getArtist).thenComparing(Album::getReleaseYear));
      System.out.println(albumCollection);



      // task 4
      Album albumShortestTitle = albumCollection.getAlbums().getFirst();
      for (var album : albumCollection.getAlbums()) {
        if (album.getTitle().length() < albumShortestTitle.getTitle().length()) {
          albumShortestTitle = album;
        }
      }
      System.out.println(albumShortestTitle);

    } catch (Exception e) {
      System.err.printf("Error: %s\n", e.getMessage());
    }
  }
}
