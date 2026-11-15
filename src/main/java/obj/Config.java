package obj;

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

public class Config extends Deserializable {
    public final Addresses addresses;
    private static final Config INSTANCE = loadJson(Config.class, "/config.json");

    private Config() {
        addresses = null;
    }

    public static Config getConfig() {
        return Config.INSTANCE;
    }

}
