package protocol;

import obj.ConfigContact;
import obj.ConfigSignal;
import obj.Deserializable;

public class ConfigResponse extends ConfigRequest {
    public final ConfigContact[] contacts;
    public final ConfigSignal[] signals;
    public final String[] tracks;
    public final String[] points;


    public ConfigResponse(ConfigContact[] contacts, ConfigSignal[] signals, String[] tracks, String[] points) {
        super();
        this.contacts = contacts;
        this.signals = signals;
        this.tracks = tracks;
        this.points = points;
    }
    
}
