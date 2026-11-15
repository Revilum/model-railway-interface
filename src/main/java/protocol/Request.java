package protocol;

//TODO: implement toJson
public abstract class Request {
    public final String command;

    Request(String command) {
        this.command = command;
    }
}
