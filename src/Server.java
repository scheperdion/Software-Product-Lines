import crypto.Authentication;
import crypto.Encryption;
import messages.Message;
import network.ChatSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class Server implements Runnable {

    int port;
    boolean running;
    List<ChatSocket> sockets = new ArrayList<>();
    ArrayBlockingQueue<Message> messages = new ArrayBlockingQueue<>(50);
    Logging _logger;
    final Encryption encryption = new Encryption();
    
    public Server(int port) {
        _logger = new Logging("Server"+port);
        this.port = port;
    }
    public void listen() {
        running = true;
        try (ServerSocket srvr = new ServerSocket(this.port)) {
            while(running) {
                Socket skt = srvr.accept();
                ChatSocket chatSkt = new ChatSocket(sockets.size(), skt, messages);
                Thread client = new Thread(chatSkt);
                client.start();
                sockets.add(chatSkt);
            }
        } catch (IOException e) {
            _logger.logSevere("Exception occurred: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        while(running) {
            try {
                Message m = encryption.decrypt(messages.take());
                if(!m.getSocket().isAuthenticated() && Authentication.checkAuthenticationToken(m.getString())) {
                    m.getSocket().authenticate();
                }
                _logger.logInfo("Distribute message: " + m.getString());
                for(ChatSocket skt : this.sockets) {
                    if(skt.isConnected() && skt.isAuthenticated()) {
                        skt.send(m);
                    }
                }
            } catch (InterruptedException e) {
                // TODO: log exception and graceful exit?
                _logger.logSevere("Exception occurred: " + e.getMessage());
                throw new RuntimeException(e);
            }

        }
    }
}
