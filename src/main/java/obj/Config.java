package obj;

import com.google.gson.Gson;

import protocol.Deserializer;

public class Config {
    public final Addresses addresses;
    public final String[] tracks;
    public final int sleep;
    private static final Config INSTANCE = Deserializer.fromJsonPath("/config.json", Config.class);
    public final transient Gson gson = new Gson();

    private Config() {
        addresses = null;
        tracks = null;
        sleep = 0;
    }

    public static Config getConfig() {
        return Config.INSTANCE;
    }

}
