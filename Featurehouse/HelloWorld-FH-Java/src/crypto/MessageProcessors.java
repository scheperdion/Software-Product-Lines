package crypto; 

import interfaces.IMessageProcessor; 
import messages.Message; 

import java.util.ArrayList; 
import java.util.List; 

public  class  MessageProcessors  implements IMessageProcessor {
	
    private static MessageProcessors instance;

	
    private final ArrayList<IMessageProcessor> processors;

	

    private MessageProcessors() {
        this.processors = new ArrayList<IMessageProcessor>();
    }

	

    public static MessageProcessors getInstance() {
        if(instance == null) {
            instance = new MessageProcessors();
        }
        return instance;
    }

	

    public void addMessageProcessor(IMessageProcessor processor) {
        this.processors.add(processor);
    }

	

    @Override
    public String processIncomingMessage(String message) {
        String result = message;
        for (int i = processors.size() - 1; i >= 0; i--) {
            IMessageProcessor p = processors.get(i);
            result = p.processIncomingMessage(result);
            if (result == null) { return null; }
        }
        return result;
    }

	

    @Override
    public String processOutgoingMessage(String message) {
        String result = message;
        for(IMessageProcessor p : processors) {
            result = p.processOutgoingMessage(result);
            if (result == null) { return null; }
        }
        return result;
    }

	

    @Override
    public String processMessageOnServer(String message) {
        String result = message;
        for(IMessageProcessor p : processors) {
            result = p.processMessageOnServer(result);
            if (result == null) { return null; }
        }
        return result;
    }


}
