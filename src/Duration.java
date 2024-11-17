import java.util.ArrayList;

public class Duration implements Comparable<Duration> {
  private int hours;
  private int minutes;
  private int seconds;

  public Duration() {
    this.hours = 0;
    this.minutes = 0;
    this.seconds = 0;
  }

  public Duration(int hours, int minutes, int seconds) {
    this.hours = hours;
    this.minutes = minutes;
    this.seconds = seconds;
  }

  public String toString() {
    return String.format("%02d:%02d:%02d", hours, minutes, seconds);
  }


  /* converting the duration of the songs to the second for easier compare */
  private int totalSeconds() {
    return (hours * 3600) +(minutes * 60) + seconds;
  }
 /* comparing one duration to another */
  public boolean compareTo(Duration other) {
    return totalSeconds() < other.totalSeconds();
  }


  /* time setters */
  public void setSeconds(int seconds) {
    Math.clamp(seconds, 0, 59);
  }

  public void setMinutes(int minutes) {
    Math.clamp(minutes, 0, 59);
  }

  public void setHours(int hours) {
    Math.clamp(hours, 0, hours);
  }

  /* getters */
  public int getSeconds() {
    return seconds;
  }

  public int getMinutes() {
    return minutes;
  }

  public int getHours() {
    return hours;
  }

  public static void main(String[] args) {
    var passed = true;

    if (!testCompareTo()) passed = false;

    if (!passed) System.err.println("Error: Test Duration::main failed");
  }

  private static boolean testCompareTo() {
    var passed = true;

    var durations = new ArrayList<Duration>();
    durations.add(new Duration(0, 0, 0));
    durations.add(new Duration(0, 0, 1));
    durations.add(new Duration(0, 1, 0));
    durations.add(new Duration(0, 1, 1));
    durations.add(new Duration(1, 0, 0));
    durations.add(new Duration(1, 0, 1));
    durations.add(new Duration(1, 1, 0));
    durations.add(new Duration(1, 1, 1));

    for (var i = 0; i < durations.size(); ++i) {
      for (var j = 0; j < durations.size(); ++j) {
        var di = durations.get(i);
        var dj = durations.get(j);

        var indexDiffBound = Math.clamp(i - j, -1, 1);

        var valueDiff = di.compareTo(dj);
        var valueDiffBound = Math.clamp(valueDiff, -1, 1);

        if (indexDiffBound != valueDiffBound) {
          System.err.printf(
              "Error: Test Duration::testCompareTo failed: Duration{%s}.compareTo(Duration{%s}) == %d\n",
              di.toString(), dj.toString(), valueDiff);
          passed = false;
        }
      }
    }

    return passed;
  }
}
