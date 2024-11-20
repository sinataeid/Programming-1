public class Track {
  private String _title;
  private Duration _duration;

  public Track(String title, Duration duration) {
    _title = title;
    _duration = duration;
  }

  public String getTitle() {
    return _title;
  }

  public Duration getDuration() {
    return _duration;
  }

  @Override
  public String toString() {
    return String.format("%s (%s)", _title, _duration);
  }

  public static void main(String[] args) {
    var passed = true;

    if (!testConstructorsAndGets()) passed = false;

    if (!passed)
      System.err.println("Error: Test Track::main failed");
    else
      System.err.println("Test Track::main passed");
  }

  public static boolean testConstructorsAndGets() {
    var passed = true;

    {
      var d = new Duration(0);
      var t = new Track("", d);
      if (!t._title.equals("") || t._duration.compareTo(new Duration(0)) != 0) {
        System.err.printf(
            "Error: Test Track::testConstructorsAndGets failed: new Track(\"\", Duration{\"%s\"}) is Track{\"%s\", Duration{\"%s\"}}\n",
            d, t._title, t._duration);
        passed = false;
      }

      var title = t.getTitle();
      if (!title.equals("")) {
        System.err.printf(
            "Error: Test Track::testConstructorsAndGets failed: Track{\"%s\", Duration{\"%s\"}}.getTitle() == \"%s\"\n",
            t._title, t._duration, title);
        passed = false;
      }

      var duration = t.getDuration();
      if (duration.compareTo(new Duration(0)) != 0) {
        System.err.printf(
            "Error: Test Track::testConstructorsAndGets failed: Track{\"%s\", Duration{\"%s\"}}.getDuration() == Duration{\"%s\"}\n",
            t._title, t._duration, duration);
        passed = false;
      }
    }

    {
      var d = new Duration(3661);
      var t = new Track("Abc", d);
      if (!t._title.equals("Abc") || t._duration.compareTo(new Duration(3661)) != 0) {
        System.err.printf(
            "Error: Test Track::testConstructorsAndGets failed: new Track(\"\", Duration{\"%s\"}) is Track{\"%s\", Duration{\"%s\"}}\n",
            d, t._title, t._duration);
        passed = false;
      }

      var title = t.getTitle();
      if (!title.equals("Abc")) {
        System.err.printf(
            "Error: Test Track::testConstructorsAndGets failed: Track{\"%s\", Duration{\"%s\"}}.getTitle() == \"%s\"\n",
            t._title, t._duration, title);
        passed = false;
      }

      var duration = t.getDuration();
      if (duration.compareTo(new Duration(3661)) != 0) {
        System.err.printf(
            "Error: Test Track::testConstructorsAndGets failed: Track{\"%s\", Duration{\"%s\"}}.getDuration() == Duration{\"%s\"}\n",
            t._title, t._duration, duration);
        passed = false;
      }
    }

    return passed;
  }
}
