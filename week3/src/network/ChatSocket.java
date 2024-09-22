package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Queue;

import crypto.Encryption;
import messages.Message;

public class ChatSocket implements Runnable {
    private int id;
    private boolean authenticated = false;
    private Socket socket;
    private Queue<Message> messageQueue;
    private boolean connected = false;

    private final Encryption encryption = new Encryption();

    public ChatSocket(int id, Socket socket, Queue<Message> messageQueue) {
        this.id = id;
        this.socket = socket;
        this.messageQueue = messageQueue;
    }

    public void authenticate() {
        this.authenticated = true;
    }
    public boolean isAuthenticated() {
        return authenticated;
    }

    public boolean isConnected() {
        return connected;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public int getId() {
        return id;
    }

    public void send(Message m) {
        try {
            socket.getOutputStream().write(encryption.encrypt(m.getString()).getBytes(StandardCharsets.UTF_8));
            socket.getOutputStream().write(new byte[] { '\n' });
            //System.out.println("WRITTEN MESSAGE:" + m.getString());
        } catch (IOException e) {
            // TODO: log exception?
            e.printStackTrace();
            connected = false;
        }
    }

    @Override
    public void run() {
        connected = true;
        try (BufferedReader in = new BufferedReader(new
                InputStreamReader(socket.getInputStream()))) {
            while(connected) {
                Message m = new Message(in.readLine(), this);
                if(m.getString() == null) {
                    connected = false;
                    break;
                }
                //System.out.println("GOT MESSAGE:" + m.getString());
                messageQueue.add(m);
            }

        } catch (IOException e) {
            // TODO: log exception?
            connected = false;
        }

    }
}
