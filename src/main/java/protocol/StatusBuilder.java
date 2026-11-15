package protocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import obj.Signal;
import obj.Point;
import obj.Track;
import protocol.StatusRequest;

public class StatusBuilder {
    private HashMap<String, Point> points = new HashMap<>();
    private HashMap<String, Point> lights = new HashMap<>();
    private HashMap<String, Point> gates = new HashMap<>();
    private HashMap<String, Signal[]> signals = new HashMap<>();
    private HashMap<String, Track> tracks = new HashMap<>();

    public StatusRequest toStatusRequest() {
        ArrayList<Point> points = new ArrayList<>();
        points.addAll(this.points.values());
        points.addAll(this.lights.values());
        points.addAll(this.gates.values());

        ArrayList<Signal> signals = new ArrayList<>();
        for (Signal[] signalGroup : this.signals.values()) {
            signals.addAll(List.of(signalGroup));
        }

        return new StatusRequest(
                signals.toArray(Signal[]::new),
                this.tracks.values().toArray(Track[]::new),
                points.toArray(Point[]::new));
    }
}
