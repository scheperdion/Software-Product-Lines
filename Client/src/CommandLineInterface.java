import messages.Message;
import messages.MessageColor;
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
        System.out.println(MessageColor.getWithColor(message.getString()));
    }

    @Override
    public void mainLoop() {
        final Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.printf("> \n");
            c.send(new Message(scanner.nextLine(), null));
        }
    }
}
