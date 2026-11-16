import java.io.IOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.naming.NameNotFoundException;

import protocol.ConfigRequest;
import protocol.ConfigResponse;
import protocol.StatusBuilder;
import socket.SocketClient;
import socket.TcpClient;
import obj.ConfigSignal;
import obj.Deserializable;

public class RailwayStateUpdater {
    private final StatusBuilder[] builders;
    private final HashMap<String, Integer> pointMap = new HashMap<>();
    // since the socket protol thinks points, lights, gates and bells are the same, I'm gonna be lazy and also mix them together for the railway object to rpi mapping.
    //private final HashMap<String, Integer> lightMap = new HashMap<>();
    //private final HashMap<String, Integer> gateMap = new HashMap<>();
    //private final HashMap<String, Integer> bellMap = new HashMap<>();
    private final HashMap<String, Integer> trackMap = new HashMap<>();
    private final HashMap<String, Integer[]> signalMap = new HashMap<>();
    private final SocketClient[] socketClients;

    public RailwayStateUpdater(String[] addresses) throws UnknownHostException, IOException, URISyntaxException {
        this.builders = new StatusBuilder[addresses.length];
        socketClients = new SocketClient[addresses.length];

        // create mapping which track, point, etc. is connected to which rpi.
        for (int i = 0; i < addresses.length; ++i) {
            socketClients[i] = new TcpClient(addresses[i]);
            String res = socketClients[i].request((new ConfigRequest()).toJson());
            ConfigResponse confRes = Deserializable.fromJson(res, ConfigResponse.class);

            for (int j = 0; j < confRes.points.length; ++j) {
                this.pointMap.put(confRes.points[j], j);
            }

            for (int j = 0; j < confRes.tracks.length; ++j) {
                this.trackMap.put(confRes.tracks[j], j);
            }

            for (int j = 0; j < confRes.signals.length; ++j) {
                ConfigSignal cSig = confRes.signals[j];
                Integer[] sigs = this.signalMap.getOrDefault(cSig.name, new Integer[]{null, null});

                sigs[cSig.number] = j;
                this.signalMap.put(cSig.name, sigs);
            }
        }
    }

    public void setPoint(String id, boolean branching) throws NameNotFoundException {
        Integer i = this.pointMap.get(id);
        if (i == null) {
            throw new NameNotFoundException("point not found on railway");
        }

        this.builders[i].addPoint(id, branching);
    }

    public void setLight(String id, boolean on) throws NameNotFoundException {
        Integer i = this.pointMap.get(id);
        if (i == null) {
            throw new NameNotFoundException("light not found on railway");
        }

        this.builders[i].addLight(id, on);
    }

    public void setGate(String id, boolean down) throws NameNotFoundException {
        Integer i = this.pointMap.get(id);
        if (i == null) {
            throw new NameNotFoundException("gate not found on railway");
        }

        this.builders[i].addGate(id, down);
    }

    public void setBell(String id, boolean on) throws NameNotFoundException {
        Integer i = this.pointMap.get(id);
        if (i == null) {
            throw new NameNotFoundException("bell not found on railway");
        }

        this.builders[i].addBell(id, on);
    }

    public void setSignal(String trackId, int index, int color) throws NameNotFoundException {
        Integer[] sigs = this.signalMap.get(trackId);
        if (sigs == null || sigs[index] == null) {
            throw new NameNotFoundException("signal not found on railway");
        }

        this.builders[sigs[index]].addSignal(trackId, index, color);
    }

    public void setTrack(String id, int direction, int speed) throws NameNotFoundException {
        Integer i = this.trackMap.get(id);
        if (i == null) {
            throw new NameNotFoundException("gate not found on railway");
        }

        this.builders[i].addTrack(id, direction, speed);
    }

    public void sendStatus(boolean blocking) throws IOException {
        for (int i = 0; i < this.builders.length; ++i) {
            String json = this.builders[i].toStatusRequest().toJson();
            this.socketClients[i].send(json, blocking);
            this.builders[i].clear();
        } 
    }
}
