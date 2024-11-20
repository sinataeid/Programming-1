import java.util.ArrayList;

public class Duration implements Comparable<Duration> {
  private int _hours;
  private int _minutes;
  private int _seconds;

  public Duration(int seconds) {
    _hours = 0;
    _minutes = 0;
    _seconds = Math.max(0, seconds);
    normalise();
  }

  public Duration(int hours, int minutes, int seconds) {
    _hours = Math.max(0, hours);
    _minutes = Math.max(0, minutes);
    _seconds = Math.max(0, seconds);
    normalise();
  }

  public int getHours() {
    return _hours;
  }

  public int getMinutes() {
    return _minutes;
  }

  public int getSeconds() {
    return _seconds;
  }

  @Override
  public String toString() {
    return String.format("%02d:%02d:%02d", _hours, _minutes, _seconds);
  }

  @Override
  public int compareTo(Duration other) {
    return Integer.compare(totalSeconds(), other.totalSeconds());
  }

  private void normalise() {
    _minutes += _seconds / 60;
    _seconds %= 60;

    _hours += _minutes / 60;
    _minutes %= 60;
  }

  private int totalSeconds() {
    return _hours * 3600 + _minutes * 60 + _seconds;
  }

  public static void main(String[] args) {
    var passed = true;

    if (!testConstructorsAndGets()) passed = false;
    if (!testCompareTo()) passed = false;

    if (!passed) System.err.println("Error: Test Duration::main failed");
  }

  private static boolean testConstructorsAndGets() {
    var passed = true;

    var constructorArguments = new ArrayList<int[]>();
    var expectedComponents = new ArrayList<int[]>();

    constructorArguments.add(new int[]{-1});
    expectedComponents.add(new int[]{0, 0, 0});
    
    constructorArguments.add(new int[]{0});
    expectedComponents.add(new int[]{0, 0, 0});

    constructorArguments.add(new int[]{1});
    expectedComponents.add(new int[]{0, 0, 1});

    constructorArguments.add(new int[]{60});
    expectedComponents.add(new int[]{0, 1, 0});

    constructorArguments.add(new int[]{3600});
    expectedComponents.add(new int[]{1, 0, 0});

    constructorArguments.add(new int[]{3661});
    expectedComponents.add(new int[]{1, 1, 1});

    constructorArguments.add(new int[]{-1, -1, -1});
    expectedComponents.add(new int[]{0, 0, 0});

    constructorArguments.add(new int[]{0, 0, 0});
    expectedComponents.add(new int[]{0, 0, 0});

    constructorArguments.add(new int[]{1, 2, 3});
    expectedComponents.add(new int[]{1, 2, 3});

    constructorArguments.add(new int[]{60, 60, 60});
    expectedComponents.add(new int[]{61, 1, 0});

    constructorArguments.add(new int[]{100, 200, 300});
    expectedComponents.add(new int[]{103, 25, 0});

    for (var i = 0; i < constructorArguments.size(); ++i) {
      var arguments = constructorArguments.get(i);

      Duration d;
      String allocationString;
      if (arguments.length == 1) {
        d = new Duration(arguments[0]);
        allocationString = String.format("new Duration(%d)", arguments[0]);
      } else {
        d = new Duration(arguments[0], arguments[1], arguments[2]);
        allocationString = String.format("new Duration(%d, %d, %d)", arguments[0], arguments[1], arguments[2]);
      }

      var expectedHours = expectedComponents.get(i)[0];
      var expectedMinutes = expectedComponents.get(i)[1];
      var expectedSeconds = expectedComponents.get(i)[2];

      if (d._hours != expectedHours || d._minutes != expectedMinutes || d._seconds != expectedSeconds) {
        System.err.printf(
            "Error: Test Duration::testConstructorsAndGets failed: %s is Duration{%d, %d, %d}\n",
            allocationString, d._hours, d._minutes, d._seconds);
        passed = false;
      }

      var hours = d.getHours();
      if (hours != expectedHours) {
        System.err.printf(
            "Error: Test Duration::testConstructorsAndGets failed: Duration{%d, %d, %d}.getHours() == %d\n",
            d._hours, d._minutes, d._seconds, hours);
        passed = false;
      }

      var minutes = d.getMinutes();
      if (minutes != expectedMinutes) {
        System.err.printf(
            "Error: Test Duration::testConstructorsAndGets failed: Duration{%d, %d, %d}.getMinutes() == %d\n",
            d._hours, d._minutes, d._seconds, minutes);
        passed = false;
      }

      var seconds = d.getSeconds();
      if (seconds != expectedSeconds) {
        System.err.printf(
            "Error: Test Duration::testConstructorsAndGets failed: Duration{%d, %d, %d}.getSeconds() == %d\n",
            d._hours, d._minutes, d._seconds, seconds);
        passed = false;
      }
    }

    return  passed;
  }

  private static boolean testCompareTo() {
    var passed = true;

    var durations = new ArrayList<Duration>();
    durations.add(new Duration(0));
    durations.add(new Duration(0, 0, 1));
    durations.add(new Duration(60));
    durations.add(new Duration(0, 1, 1));
    durations.add(new Duration(3600));
    durations.add(new Duration(1, 0, 1));
    durations.add(new Duration(3660));
    durations.add(new Duration(1, 1, 1));
    durations.add(new Duration(3662));

    for (var i = 0; i < durations.size(); ++i) {
      for (var j = 0; j < durations.size(); ++j) {
        var di = durations.get(i);
        var dj = durations.get(j);

        var indexDiffBound = Math.clamp(i - j, -1, 1);

        var valueDiff = di.compareTo(dj);
        var valueDiffBound = Math.clamp(valueDiff, -1, 1);

        if (indexDiffBound != valueDiffBound) {
          System.err.printf(
              "Error: Test Duration::testCompareTo failed: Duration{%d, %d, %d}.compareTo(Duration{%d, %d, %d}) == %d\n",
              di._hours, di._minutes, di._seconds, dj._hours, dj._minutes, dj._seconds, valueDiff);
          passed = false;
        }
      }
    }

    return passed;
  }
}
