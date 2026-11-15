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

import protocol.DisconnectRequest;

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
    public void disconnect() throws IOException {
        //not sure if we should send the disconnect request here or we do that at a higher abstraction level.
        this.send((new DisconnectRequest()).toJson(), true);
        this.in.close();
        this.out.close();
    }

    @Override
    public void send(String msg, boolean blocking) throws IOException {
        this.out.write(msg);
        this.out.newLine();
        if (blocking) {
            this.out.flush();
        }
    }

    @Override
    public String receive(boolean blocking) throws IOException {
        if (!blocking && !this.in.ready()) {
            return null;
        }
        return this.in.readLine();
    }

    @Override
    public String request(String msg) throws IOException {
        this.send(msg, true);
        return this.receive(true);
    }
}
