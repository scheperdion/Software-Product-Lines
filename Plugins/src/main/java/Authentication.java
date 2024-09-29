public class Authentication implements interfaces.IMessageProcessor {

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
}
