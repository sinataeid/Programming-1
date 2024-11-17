import java.util.ArrayList;

public class Duration implements Comparable<Duration> {
  private int hours;
  private int minutes;
  private int seconds;

  public Duration() {
    this(0, 0, 0);
  }

  public Duration(int hours, int minutes, int seconds) {
    setHours(hours);
    setMinutes(minutes);
    setSeconds(seconds);
    }

  public String toString() {
    return String.format("%02d:%02d:%02d", hours, minutes, seconds);
  }


  /* converting the duration of the songs to the second for easier compare */
  private int totalSeconds() {
    return (hours * 3600) +(minutes * 60) + seconds;
  }
 /* comparing one duration to another */
  public int compareTo(Duration other) {
    return Integer.compare(totalSeconds(), other.totalSeconds());
  }

  /* time setters */
  public void setSeconds(int seconds) {
    this.seconds = Math.clamp(seconds, 0, 59); 
  }

  public void setMinutes(int minutes) {
    this.minutes = Math.clamp(minutes, 0, 59);
  }

  public void setHours(int hours) {
    this.hours = Math.clamp(hours, 0, Integer.MAX_VALUE);
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

    if (!testConstructors()) passed = false;
    if (!testGetSet()) passed = false;
    if (!testCompareTo()) passed = false;

    if (!passed) System.err.println("Error: Test Duration::main failed");
  }

  private static boolean testConstructors() {
    var passed = true;

    var d = new Duration();
    if (d.hours != 0 || d.minutes != 0 || d.seconds != 0) {
      System.err.printf(
          "Error: Test Duration::testConstructors failed: new Duration() is Duration{%d, %d, %d}",
          d.hours, d.minutes, d.seconds);
      passed = false;
    }

    d = new Duration(0, 0, 0);
    if (d.hours != 0 || d.minutes != 0 || d.seconds != 0) {
      System.err.printf(
          "Error: Test Duration::testConstructors failed: new Duration(0, 0, 0) is Duration{%d, %d, %d}",
          d.hours, d.minutes, d.seconds);
      passed = false;
    }

    d = new Duration(1, 2, 3);
    if (d.hours != 1 || d.minutes != 2 || d.seconds != 3) {
      System.err.printf(
          "Error: Test Duration::testConstructors failed: new Duration(1, 2, 3) is Duration{%d, %d, %d}",
          d.hours, d.minutes, d.seconds);
      passed = false;
    }

    d = new Duration(-1, -2, -3);
    if (d.hours != 0 || d.minutes != 0 || d.seconds != 0) {
      System.err.printf(
          "Error: Test Duration::testConstructors failed: new Duration(-1, -2, -3) is Duration{%d, %d, %d}",
          d.hours, d.minutes, d.seconds);
      passed = false;
    }

    d = new Duration(100, 200, 300);
    if (d.hours != 100 || d.minutes != 59 || d.seconds != 59) {
      System.err.printf(
          "Error: Test Duration::testConstructors failed: new Duration(100, 200, 300) is Duration{%d, %d, %d}",
          d.hours, d.minutes, d.seconds);
      passed = false;
    }

    return passed;
  }

  private static boolean testGetSet() {
    var passed = true;

    var d = new Duration();

    for (var i = 0; i < 60; ++i) {
      d.setHours(i);
      var result = d.getHours();
      if (result != i) {
        System.err.printf(
            "Error: Test Duration::testGetSet failed: duration.setHours(%d); duration.getHours() == %d\n",
            i, result);
        passed = false;
      }

      d.setMinutes(i);
      result = d.getMinutes();
      if (result != i) {
        System.err.printf(
            "Error: Test Duration::testGetSet failed: duration.setMinutes(%d); duration.getMinutes() == %d\n",
            i, result);
        passed = false;
      }

      d.setSeconds(i);
      result = d.getSeconds();
      if (result != i) {
        System.err.printf(
            "Error: Test Duration::testGetSet failed: duration.setSeconds(%d); duration.getSeconds() == %d\n",
            i, result);
        passed = false;
      }
    }

    d.setHours(-1);
    var result = d.getHours();
    if (result != 0) {
      System.err.printf(
          "Error: Test Duration::testGetSet failed: duration.setHours(%d); duration.getHours() == %d\n",
          -1, result);
      passed = false;
    }

    d.setMinutes(-2);
    result = d.getMinutes();
    if (result != 0) {
      System.err.printf(
          "Error: Test Duration::testGetSet failed: duration.setMinutes(%d); duration.getMinutes() == %d\n",
          -2, result);
      passed = false;
    }

    d.setSeconds(-3);
    result = d.getSeconds();
    if (result != 0) {
      System.err.printf(
          "Error: Test Duration::testGetSet failed: duration.setSeconds(%d); duration.getSeconds() == %d\n",
          -3, result);
      passed = false;
    }

    d.setHours(100);
    result = d.getHours();
    if (result != 100) {
      System.err.printf(
          "Error: Test Duration::testGetSet failed: duration.setHours(%d); duration.getHours() == %d\n",
          100, result);
      passed = false;
    }

    d.setMinutes(200);
    result = d.getMinutes();
    if (result != 59) {
      System.err.printf(
          "Error: Test Duration::testGetSet failed: duration.setMinutes(%d); duration.getMinutes() == %d\n",
          200, result);
      passed = false;
    }

    d.setSeconds(300);
    result = d.getSeconds();
    if (result != 59) {
      System.err.printf(
          "Error: Test Duration::testGetSet failed: duration.setSeconds(%d); duration.getSeconds() == %d\n",
          300, result);
      passed = false;
    }

    return passed;
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
