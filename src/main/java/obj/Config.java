package obj;

import com.google.gson.Gson;

import protocol.Deserializer;

public class Config {
    public final Addresses addresses;
    private static final Config INSTANCE = Deserializer.fromJsonPath("/config.json", Config.class);
    public final transient Gson gson = new Gson();

    private Config() {
        addresses = null;
    }

    public static Config getConfig() {
        return Config.INSTANCE;
    }

}
