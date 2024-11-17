public class Track {
  public String title;
  public Duration duration;

  public Track() {
    this("Untitled", new Duration());
  }

  public Track(String title, Duration duration) {
    this.title = title;
    this.duration = duration;
  }

  public String toString() {
    return String.format("%s (%s)", title, duration);
  }
}
