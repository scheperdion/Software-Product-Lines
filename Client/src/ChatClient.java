import messages.Message;
import ui.UserInterface;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ChatClient {
    public static void main(String[] args) throws InterruptedException {
        Client c = new Client(0);
        UserInterface ui = new CommandLineInterface(c);
        Thread t = new Thread(c);
        c.connect(6554);
        t.start();

        c.authenticate();
        ui.mainLoop();

    }
}