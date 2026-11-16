package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import com.google.gson.reflect.TypeToken;

import obj.Config;
import protocol.Request;

public class TcpClient implements SocketClient {
    private final Socket socket;
    private final BufferedWriter out;
    private final BufferedReader in;

    public TcpClient(String address) throws UnknownHostException, IOException, URISyntaxException {
        URI url = new URI(address);
        this.socket = new Socket(url.getHost(), url.getPort());
        this.out = new BufferedWriter(new OutputStreamWriter(
                this.socket.getOutputStream(), StandardCharsets.UTF_8));
        this.in = new BufferedReader(new InputStreamReader(
                this.socket.getInputStream(), StandardCharsets.UTF_8));
    }

    @Override
    public void close() throws IOException {
        this.in.close();
        this.out.close();
    }

    @Override
    public void send(Request req, boolean blocking) throws IOException {
        this.out.write(req.toJson());
        this.out.newLine();
        if (blocking) {
            this.out.flush();
        }
    }

    @Override
    public <T extends Request> T receive(boolean blocking) throws IOException {
        if (!blocking && !this.in.ready()) {
            return null;
        }

        return Config.getConfig().gson.fromJson(this.in.readLine(), new TypeToken<T>() { });
    }

    @Override
    public <T extends Request> T request(Request req) throws IOException {
        this.send(req, true);
        return this.receive(true);
    }
}
