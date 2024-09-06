import crypto.Authentication;
import messages.Message;
import network.ChatSocket;

import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

public class Client implements Runnable{
    private ChatSocket socket;
    boolean running = false;
    ArrayBlockingQueue<Message> messages = new ArrayBlockingQueue<Message>(50);


    public Client() {

    }

    public void connect(int port) {
        try {
            socket = new ChatSocket(
                    0,
                    new Socket("localhost", port),
                    messages
            );
            Thread t = new Thread(socket);
            t.start();
        }
        catch(Exception e) {
            System.out.print("Whoops! It didn't work!\n");
        }
    }

    public void authenticate() {
        send(new Message(Authentication.getAuthenticationToken(), null));
    }

    public void send(Message m) {
        socket.send(m);
    }

    @Override
    public void run() {
        running = true;
        while(running) {
            try {
                Message m = messages.take();
                System.out.println(m.getString());
                // TODO: print message on screen
            } catch (InterruptedException e) {
                // TODO: log exception and graceful exit?
                throw new RuntimeException(e);
            }

        }
    }
}
