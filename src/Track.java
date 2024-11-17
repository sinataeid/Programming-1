public class Track {
  public String title;
  public Duration duration;

  public Track() {
    title = "Untitled";
    duration = new Duration();
  }

  public Track(String title, Duration duration) {
    this.title = title;
    this.duration = duration;
  }

  public String toString() {
    return String.format("%s (%s)", title, duration);
  }
}
