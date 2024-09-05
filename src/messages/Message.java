package messages;

public class Message {
    private final String str;
    private final int idFrom;

    public Message(String str, int idFrom) {
        this.str = str;
        this.idFrom = idFrom;
    }

    public String getString() {
        return str;
    }

    public int getId() {
        return this.idFrom;
    }
}
