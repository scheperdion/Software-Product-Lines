import crypto.Authentication;
import crypto.Encryption;
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
    private final Logging _logger;
    final Encryption encryption = new Encryption();
    final List<MessageObserver> messageObservers;

    public Client(int id) {
        _logger = new Logging("Client" + id);
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
            _logger.logSevere("Exception occurred during connecting: " + e.getMessage());
        }
    }

    public void authenticate() {
        send(new Message(Authentication.getAuthenticationToken(), null));
    }

    public void send(Message m) {
        _logger.logInfo("Message send: " + m.getString());
        socket.send(m);
    }
    @Override
    public void run() {
        running = true;
        while(running) {
            try {
                Message m = encryption.decrypt(messages.take());
                _logger.logInfo("Message received: " + m.getString());
                for (MessageObserver o : this.messageObservers) {
                    o.notify(m);
                }
            } catch (InterruptedException e) {
                _logger.logSevere("Exception occurred: " + e.getMessage());
                throw new RuntimeException(e);
            }

        }
    }
}
