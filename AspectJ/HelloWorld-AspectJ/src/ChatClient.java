import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ChatClient {
    public static void main(String[] args) throws InterruptedException {
    	int random = ThreadLocalRandom.current().nextInt(1, 9999);
        Client c = new Client(random);
        Thread t = new Thread(c);
        c.connect(6554);
        t.start();

        final Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.printf("> \n");
            c.send(new Message(random + ": " + scanner.nextLine(), null));
        }
    }
}