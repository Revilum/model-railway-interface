package obj;

public final class Signal extends ConfigSignal{
    // bitmap
    public final int value;

    public Signal(String name, int number, int value) {
        super(name, number);
        this.value = value;
    }
}
