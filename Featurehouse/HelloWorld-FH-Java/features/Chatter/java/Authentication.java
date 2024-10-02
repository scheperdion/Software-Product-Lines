import interfaces.IMessageProcessor;

public class Authentication implements IMessageProcessor {

    private boolean authenticated = false;

    private String key = "0000";

    @Override
    public String processIncomingMessage(String message) {
        if (authenticated) {return message;}
        return null;
    }

    @Override
    public String processOutgoingMessage(String message) {
        if (authenticated) {return message;}
        if (message.contains(key)) {authenticated = true;}
        return null;
    }

    @Override
    public String processMessageOnServer(String message) {
        return message;
    }
}
