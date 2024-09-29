import crypto.MessageProcessors;
import messages.Message;
import network.ChatSocket;
import ui.MessageObserver;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class Client implements Runnable{
    private ChatSocket socket;
    boolean running = false;
    ArrayBlockingQueue<Message> messages = new ArrayBlockingQueue<Message>(50);
    final MessageProcessors messageProcessors = MessageProcessors.getInstance();
    final List<MessageObserver> messageObservers;

    public Client(int id) {
        this.messageObservers = new ArrayList<>();
    }

    public void addObserver(MessageObserver o) {
        this.messageObservers.add(o);
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
            System.out.println("Exception occurred during connecting: " + e.getMessage());
        }
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
                m.setString(messageProcessors.processIncomingMessage(m.getString()));
                for (MessageObserver o : this.messageObservers) {
                    o.notify(m);
                }
            } catch (InterruptedException e) {
                System.out.println("Exception occurred: " + e.getMessage());
                throw new RuntimeException(e);
            }

        }
    }
}
