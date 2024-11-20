public class Track {
  private String title;
  public Duration duration;

  public Track() {
    this("Untitled", new Duration());
  }

  public Track(String title, Duration duration) {
    this.title = title;
    this.duration = duration;
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

  public String toString() {
    return String.format("%s (%s)", title, duration);
  }

  public static void main(String[] args) {
    var passed = true;

    if(!testSetTitle()) passed = false;

    if (!passed) System.err.println("Error: Test Duration::main failed");

    
  }
  public static boolean testSetTitle() {
    var passed = true;

    var t = new Track();
    if(!t.title.equals("Untitled")) {
      System.err.printf("Error: {%s}", t.title);
      passed = false;
    }
    

    t = new Track("abc", new Duration());
    if(!t.title.equals("abc")) {
      System.err.printf("Error: {%s}", t.title);
      passed = false;
    }


    return passed;
  }

}
