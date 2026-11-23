package obj;

import com.google.gson.Gson;

import protocol.Deserializer;

public class Config {
    public final Railway railway;
    public final Railway testTrack;
    public final int sleepMs;
    private static final Config INSTANCE = Deserializer.fromJsonPath("/config.json", Config.class);
    public final transient Gson gson = new Gson();

    private Config() {
        railway = null;
        testTrack = null;
        sleepMs = 0;
    }

    public static Config getConfig() {
        return Config.INSTANCE;
    }

}
