package interfaces;

public interface IUserInterface extends IMessageReceiver {
    void mainLoop();
    void addMessageSender(IMessageSender ms);
    void addMessageColor(IMessageColor mc);
}
