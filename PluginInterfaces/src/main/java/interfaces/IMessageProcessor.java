package interfaces;

public interface IMessageProcessor {
    String processIncomingMessage(String message);

    String processOutgoingMessage(String message);

    String processMessageOnServer(String message);
}