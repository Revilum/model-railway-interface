package obj;

public final class Track {
    public final String name;
    public final short direction;
    public final short pwm;

    public Track(String name, short direction, short pwm) {
        this.name = name;
        this.direction = direction;
        this.pwm = pwm;
    }
}
