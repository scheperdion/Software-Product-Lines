import interfaces.IMessageProcessor;

public class Rot13 implements IMessageProcessor {
    @Override
    public String processIncomingMessage(String s) {
        return rot13(s);
    }

    @Override
    public String processOutgoingMessage(String message) {
        return rot13(message);
    }

    @Override
    public String processMessageOnServer(String message) {
        return message;
    }

    private String rot13(String s) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            result.append(c);
        }
        return result.toString();
    }
}
