package messages;

public abstract class MessageProcessor {

    public final void processMessage(Message message) {
        String formattedMessage = formatMessage(message);
        displayMessage(formattedMessage);
    }

    protected abstract String formatMessage(Message message);
    protected abstract void displayMessage(String message);
}