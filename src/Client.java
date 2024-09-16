import config.Config;
import crypto.*;
import messages.Message;
import messages.MessageColorProcessor;
import messages.MessageProcessor;
import network.ChatSocket;

import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

public class Client implements Runnable{
    private ChatSocket socket;
    boolean running = false;
    ArrayBlockingQueue<Message> messages = new ArrayBlockingQueue<Message>(50);
    private final Logging _logger;
    final IEncryptionRoutine encryption = new VigenereEncryption(new Rot13Encryption(new EncryptionRoutine()), "key");

    public Client(int id) {
        _logger = new Logging("Client" + id);
    }

    public void connect(int port) {
        try {
            socket = new ChatSocket(
                    0,
                    new Socket("localhost", port),
                    messages,
                    encryption
            );
            Thread t = new Thread(socket);
            t.start();
            Config config = Config.getConfig();
            _logger.logInfo(config.getProperty("COLOR_ENABLED"));
            _logger.logInfo(config.getProperty("AUTHENTICATION_ENABLED"));

        }
        catch(Exception e) {
            _logger.logSevere("Exception occurred during connecting: " + e.getMessage());
            System.out.print("Whoops! It didn't work!\n");
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

                MessageProcessor processor = new MessageColorProcessor();
                processor.processMessage(m);

                // TODO: print message on screen
            } catch (InterruptedException e) {
                // TODO: log exception and graceful exit?
                _logger.logSevere("Exception occurred: " + e.getMessage());
                throw new RuntimeException(e);
            }

        }
    }
}
