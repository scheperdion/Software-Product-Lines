package interfaces;

import java.awt.Color;

public interface IUserInterface extends IMessageReceiver {
    void mainLoop();
    void addMessageSender(IMessageSender ms);
    void setColor(Color color);
}
