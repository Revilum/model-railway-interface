package obj;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import com.google.gson.Gson;

public abstract class Deserializable {
    private static final Gson gson = new Gson();

    protected static <T> T loadJson(Class<T> type, String jsonPath) {
        try (var reader = new InputStreamReader(
                Objects.requireNonNull(
                        type.getResourceAsStream(jsonPath),
                        jsonPath + " not found"),
                StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, type);
        } catch (IOException exception) {
            throw new UncheckedIOException("Could not read JSON resource", exception);
        }
    }
}