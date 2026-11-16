package obj;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;


public class Deserializable {

    public static <T> T fromJsonPath(String jsonPath, Class<T> type) {
        InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(
                type.getResourceAsStream(jsonPath),
                jsonPath + " not found"),
                StandardCharsets.UTF_8);

        return Deserializable.fromJson(reader, type);
    }

    public static <T> T fromJson(String json, Class<T> type) {
        StringReader reader = new StringReader(json);
        return Deserializable.fromJson(reader, type);
    }

    public static <T> T fromJson(Reader json, Class<T> type) {
        return Config.getConfig().gson.fromJson(json, type);
    }

    private Deserializable() {};
}