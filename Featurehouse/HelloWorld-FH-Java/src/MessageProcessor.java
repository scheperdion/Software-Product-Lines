
public   class  MessageProcessor {
	
    
	public String processOutgoingMessage  (String message) {
        return message;
    }

	

	

    public String processIncomingMessage  (String message) {
    	return ColorMessage.getTextWithColor(message);
    }

	

     private String  processMessageOnServer__wrappee__Chatter  (String message) {
        return message;
    }

	

	

    public String processMessageOnServer(String message) {
    	processMessageOnServer__wrappee__Chatter(message);
        return message;
    }


}
