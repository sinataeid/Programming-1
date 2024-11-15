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

    static public void main(String[] args) {
        var d = new Duration(1, 2, 3);
        System.out.println('\n' + d.toString());
        // final var d0 = new Duration(0, 0, 0);
        // final var d1 = new Duration(0, 0, 1);
        // final var d2 = new Duration(0, 1, 0);
        // final var d3 = new Duration(0, 1, 1);
        // final var d4 = new Duration(1, 0, 0);
        // final var d5 = new Duration(1, 0, 1);
        // final var d6 = new Duration(1, 1, 0);
        // final var d7 = new Duration(1, 1, 1);

        // todo: compare equal durations

        // assert d0.compareTo(d1) < 0;
        // assert d0.compareTo(d1) < 0;
        // assert d0.compareTo(d2) < 0;
        // assert d0.compareTo(d3) < 0;
        // assert d0.compareTo(d2) < 0;
        // assert d0.compareTo(d2) < 0;
    }
}