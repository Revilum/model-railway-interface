package protocol;

import java.util.ArrayList;

import obj.Signal;
import obj.Point;
import obj.Track;

public class StatusBuilder {
    private final ArrayList<Point> points = new ArrayList<>();
    private final ArrayList<Signal> signals = new ArrayList<>();
    private final ArrayList<Track> tracks = new ArrayList<>();

    public void addPoint(String id, boolean branching) {
        this.points.add(new Point(id, branching ? 1 : 0));
    }

    public void addLight(String id, boolean on) {
        this.points.add(new Point(id, on ? 1 : 0));
    }

    public void addGate(String id, boolean down) {
        this.points.add(new Point(id, down ? 1 : 0));
    }

    public void addBell(String id, boolean on) {
        this.points.add(new Point(id, on ? 1 : 0));
    }

    public void addSignal(String trackId, int index, int color) {
        this.signals.add(new Signal(trackId, index, color));
    }

    public void addTrack(String id, int direction, int speed) {
        this.tracks.add(new Track(id, direction, speed));
    }

    public void clear() {
        this.points.clear();
        this.signals.clear();
        this.tracks.clear();
    }

    public StatusRequest toStatusRequest() {
        return new StatusRequest(
                this.signals.toArray(Signal[]::new),
                this.tracks.toArray(Track[]::new),
                this.points.toArray(Point[]::new));
    }
}
