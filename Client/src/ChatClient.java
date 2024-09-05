import messages.Message;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ChatClient {
    public static void main(String[] args) throws InterruptedException {
        Client c = new Client();
        Thread t = new Thread(c);
        c.connect(6554);
        t.start();
        c.send(new Message("Client message!" + new Random().nextInt(1024) + "\n", 0));
    }
}