import messages.Message;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ChatClient {
    public static void main(String[] args) throws InterruptedException {
        Client c = new Client(0);
        Thread t = new Thread(c);
        c.connect(6554);
        t.start();

        c.authenticate();
        c.send(new Message("Client message!" + new Random().nextInt(1024), null));
    }
}