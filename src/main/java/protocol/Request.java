package protocol;
import com.google.gson.Gson;


public abstract class Request extends Deserializer {
    public final String command;
    private static Gson gson = new Gson();

    Request(String command) {
        this.command = command;
    }

    public String toJson() {
        return Request.gson.toJson(this);
    }
}
