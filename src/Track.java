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
}
