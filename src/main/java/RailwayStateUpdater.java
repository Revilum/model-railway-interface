import java.io.IOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.HashMap;

import obj.Point;
import obj.Signal;
import protocol.ConfigRequest;
import protocol.StatusBuilder;
import socket.SocketClient;
import socket.TcpClient;

public class RailwayStateUpdater {
    private final StatusBuilder[] builders;
    private final String[] addresses;
    private final HashMap<String, Integer> pointMap = new HashMap<>();
    private final HashMap<String, Integer> lightMap = new HashMap<>();
    private final HashMap<String, Integer> gateMap = new HashMap<>();
    private final HashMap<String, Integer> bellMap = new HashMap<>();
    private final HashMap<String, Integer> trackMap = new HashMap<>();
    private final HashMap<String, Integer[]> signalMap = new HashMap<>();
    private final SocketClient[] socketClients;

    public RailwayStateUpdater(String[] addresses) throws UnknownHostException, IOException, URISyntaxException {
        this.addresses = addresses;
        this.builders = new StatusBuilder[addresses.length];
        socketClients = new SocketClient[addresses.length];

        for (int i = 0; i < addresses.length; ++i) {
            socketClients[i] = new TcpClient(addresses[i]);
            socketClients[i].request((new ConfigRequest()).toJson());
        }
    }

    public void addPoint(String id, boolean branching) {
    }

    public void addLight(String id, boolean on) {
        
    }

    public void setGate(String id, boolean down) {
        
    }

    public void setBell(String id, boolean on) {
        
    }

    public void addSignal(String trackId, int index, int color) {
        
    }

    public void addTrack(String id, int direction, int speed) {
        
    }

    public void sendStatus() {
        
    }
}
