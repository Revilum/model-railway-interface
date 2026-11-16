package obj;

import com.google.gson.Gson;

import protocol.Deserializer;

class Addresses {
    public final Hostnames simulation;
    public final Hostnames hardware;

    private Addresses(Hostnames simulation, Hostnames hardware) {
        this.simulation = simulation;
        this.hardware = hardware;
    }
}

class Hostnames {
    public final String[] railway;
    public final String[] testTrack;

    public Hostnames(String[] railway, String[] testTrack) {
        this.railway = railway;
        this.testTrack = testTrack;
    }
}

public class Config {
    public final Addresses addresses;
    private static final Config INSTANCE = Deserializer.fromJsonPath("/config.json", Config.class);
    public final Gson gson = new Gson();

    private Config() {
        addresses = null;
    }

    public static Config getConfig() {
        return Config.INSTANCE;
    }

}
