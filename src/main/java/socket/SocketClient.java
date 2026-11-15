package socket;

import java.io.IOException;

public interface SocketClient {
    void disconnect() throws IOException;
    void send(String msg, boolean blocking) throws IOException;
    String receive(boolean blocking) throws IOException;
    String request(String msg) throws IOException;
}
