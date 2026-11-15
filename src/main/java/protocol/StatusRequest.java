package protocol;

import obj.Point;
import obj.Signal;
import obj.Track;

public final class StatusRequest extends Request {
    public final Signal[] signals;
    public final Track[] tracks;
    public final Point[] points;

    public StatusRequest(Signal[] signals, Track[] tracks, Point[] points) {
        super("status");
        this.signals = signals;
        this.tracks = tracks;
        this.points = points;
    }
}
