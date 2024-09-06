import crypto.Authentication;
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

    public Server(int port) {
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
            // TODO: log the exception
        }
    }

    @Override
    public void run() {
        while(running) {
            try {
                Message m = messages.take();
                if(!m.getSocket().isAuthenticated() && Authentication.checkAuthenticationToken(m.getString())) {
                    m.getSocket().authenticate();
                }
                for(ChatSocket skt : this.sockets) {
                    if(skt.isConnected() && skt.isAuthenticated()) {
                        skt.send(m);
                    }
                }
            } catch (InterruptedException e) {
                // TODO: log exception and graceful exit?
                throw new RuntimeException(e);
            }

        }
    }
}
