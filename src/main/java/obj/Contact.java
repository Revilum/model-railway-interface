package obj;

public final class Contact extends  ConfigContact {
    public final int type;
    public final int count;

    public Contact(String name, int type, int count, int number) {
        super(name, number);
        this.type = type;
        this.count = count;
    }
}
