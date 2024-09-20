import ui.UserInterface;

public class ChatClient {
    public static void main(String[] args) throws InterruptedException {
        Client c = new Client(0);
        UserInterface ui = new GraphicalUserInterface(c); //new CommandLineInterface(c);
        Thread t = new Thread(c);
        c.connect(6554);
        t.start();

        c.authenticate();
        ui.mainLoop();

    }
}