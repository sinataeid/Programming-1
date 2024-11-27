import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AlbumDatabase {
  public static void main(String[] args) {
    try {
      var albumCollection = new AlbumCollection();
      var albumsFile = new File("./albums.txt");
      var scanner = new Scanner(albumsFile);
      var albumDeclarationPattern = Pattern.compile("(^.+) : (.+) \\((\\d+)\\)$", Pattern.MULTILINE);
      var trackDeclarationPattern = Pattern.compile("^(\\d{1,2}):(\\d{1,2}):(\\d{1,2}) - (.+)$", Pattern.MULTILINE);

        var line = scanner.nextLine();
      while (scanner.hasNextLine()) {

        var albumMatcher = albumDeclarationPattern.matcher(line);
        if (albumMatcher.find()) {
            var artist = albumMatcher.group(1);
            var title = albumMatcher.group(2);
            var releaseYear = Integer.parseInt(albumMatcher.group(3));
            var album = new Album(artist, title, releaseYear);

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();

                var trackMatcher = trackDeclarationPattern.matcher(line);
                if (trackMatcher.find()) {
                    title = trackMatcher.group(4);
                    var hours = Integer.parseInt(trackMatcher.group(1));
                    var minutes = Integer.parseInt(trackMatcher.group(2));
                    var seconds = Integer.parseInt(trackMatcher.group(3));
                    var duration = new Duration(hours, minutes, seconds);
                    var track = new Track(title, duration);
                    album.addTrack(track);
                }
                else
                break;
            }

            albumCollection.addAlbum(album);
        }
        else {
            System.err.printf("Unrecognised line \"%s\". Skipping.\n", line);
            line = scanner.nextLine();
        }
      }

      System.out.println(albumCollection);
    } catch (IOException e) {
      System.err.println("An IO exception has occurred.");
    }
  }
}
