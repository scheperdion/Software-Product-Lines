package ui;

public interface UserInterface extends MessageObserver{
    void mainLoop();
    void sendMessage(String message);
}
