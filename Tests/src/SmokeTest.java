import crypto.Encryption;
import messages.Message;

import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SmokeTest {
    public static void main(String[] args) {

        Server s = new Server(6554);
        Thread t1 = new Thread(s);
        t1.start();
        Thread t3 = new Thread(s::listen);
        t3.start();
        System.out.println("Server started");

        Client c = new Client(0);
        Thread t2 = new Thread(c);
        c.connect(6554);
        t2.start();
        System.out.println("Client started");

        c.authenticate();
        c.send(new Message("Client message!" + new Random().nextInt(1024), null));
        System.out.println("Test");
    }
}