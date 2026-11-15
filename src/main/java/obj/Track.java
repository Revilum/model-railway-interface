package obj;

public final class Track {
    public final String name;
    public final int direction;
    public final int pwm;

    public Track(String name, int direction, int pwm) {
        this.name = name;
        this.direction = direction;
        this.pwm = pwm;
    }
}
