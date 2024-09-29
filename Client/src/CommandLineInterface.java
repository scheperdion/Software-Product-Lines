import messages.Message;
import ui.MessageObserver;
import ui.UserInterface;

import java.util.Scanner;

public class CommandLineInterface implements MessageObserver, UserInterface {
    private final Client c;
    public CommandLineInterface(Client c) {
        this.c = c;
        c.addObserver(this);
    }
    @Override
    public void notify(Message message) {
        System.out.println(message.getString());
    }

    @Override
    public void mainLoop() {
        final Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.printf("> \n");
            this.sendMessage(scanner.nextLine());
        }
    }

    @Override
    public void sendMessage(String message) {
        c.send(new Message(message, null));
    }
}
