import java.io.File;
import java.io.FileReader;


public class AlbumDatabase {

    
    
    public static void main (String[] args) {
        try {
            FileReader albumFile = new FileReader("./albums.txt");
            System.out.println();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
