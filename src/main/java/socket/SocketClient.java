package socket;

import java.io.IOException;

import protocol.Request;

public interface SocketClient {
    void close() throws IOException;
    void send(Request req, boolean blocking) throws IOException;
    <T extends Request> T receive(boolean blocking, Class<T> responseType) throws IOException;
    <T extends Request> T request(Request req, Class<T> responseType) throws IOException;
}
