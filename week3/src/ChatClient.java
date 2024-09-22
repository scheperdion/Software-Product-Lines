import ui.UserInterface;

public class ChatClient {
    public static void main(String[] args) throws InterruptedException {
        Client c = new Client(0);
     // #if GUI
        UserInterface ui = new GraphicalUserInterface(c);
     // #else
//@        UserInterface ui = new CommandLineInterface(c);
     // #endif
        
        Thread t = new Thread(c);
        c.connect(6554);
        t.start();

        ui.mainLoop();
    }
}
