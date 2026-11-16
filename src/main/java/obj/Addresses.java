package obj;

public class Addresses {
    public final Hostnames simulation;
    public final Hostnames hardware;

    private Addresses(Hostnames simulation, Hostnames hardware) {
        this.simulation = simulation;
        this.hardware = hardware;
    }
}