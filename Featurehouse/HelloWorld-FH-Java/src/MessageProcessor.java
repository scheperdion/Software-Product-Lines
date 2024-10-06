
public   class  MessageProcessor {
	
	 private String  processOutgoingMessage__wrappee__Chatter  (String message) {
        return message;
    }

	
    
	public String processOutgoingMessage(String message) {
//		processOutgoingMessage__wrappee__Chatter(message);
        return message;
    }

	

     private String  processIncomingMessage__wrappee__Chatter  (String message) {
        return message;
    }

	

	

    public String processIncomingMessage(String message) {
//    	processIncomingMessage__wrappee__Chatter(message);
    	return ColorMessage.getTextWithColor(message);
    }

	

     private String  processMessageOnServer__wrappee__Chatter  (String message) {
        return message;
    }

	

	

    public String processMessageOnServer(String message) {
//    	processMessageOnServer__wrappee__Chatter(message);
        return message;
    }


}
