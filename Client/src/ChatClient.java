import messages.Message;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ChatClient {
    public static void main(String[] args) throws InterruptedException {
        Client c = new Client(0);
        Thread t = new Thread(c);
        c.connect(6554);
        t.start();

//        c.authenticate();
        final Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.printf("> \n");
            c.send(new Message(scanner.nextLine(), null));
        }
    }
}
