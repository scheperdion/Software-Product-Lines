public class Message {
    private final String str;
    private final ChatSocket skt;

    public Message(String str, ChatSocket skt) {
        this.str = str;
        this.skt = skt;
    }

    public String getString() {
        return str;
    }

    public ChatSocket getSocket() {
        return skt;
    }
}