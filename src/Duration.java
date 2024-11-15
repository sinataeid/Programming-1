import java.util.ArrayList;

public class Duration {
  private int hours;
  private int minutes;
  private int seconds;

  public Duration(int hours, int minutes, int seconds) {
    this.hours = hours;
    this.minutes = minutes;
    this.seconds = seconds;
  }

  public String toString() {
    return String.format("");
  }

  public static void main(String[] args) {
    var passed = true;

    if (!testCompareTo()) passed = false;

    if (!passed) System.err.printf("Error: Test Duration::main failed");
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
