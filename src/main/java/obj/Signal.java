package obj;

public final class Signal extends ConfigSignal{
    // bitmap
    public final short value;

    public Signal(String name, short number, short value) {
        super(name, number);
        this.value = value;
    }
}
