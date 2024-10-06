
public class MessageProcessor {
    
	public String processOutgoingMessage(String message) {
        return message;
    }

	

    public String processIncomingMessage(String message) {
    	return ColorMessage.getTextWithColor(message);
    }

	

    public String processMessageOnServer(String message) {
    	original(message);
        return message;
    }
}
