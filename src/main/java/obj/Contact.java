package obj;

public final class Contact extends  ConfigContact {
    public final int type;
    public final int count;

    public Contact(String name, int type, int count, int number) {
        super(name, number);
        this.type = type;
        this.count = count;
    }

    @Override 
    public String toString() {
        String direction = this.type == 0 ? "NONE" : this.type == 1 ? "FORWARD" : this.type == 2 ? "BACKWARD" : "UNKNOWN";
        return this.name + "[" + this.number + "] " + direction + " " + this.count; 
    }
}
