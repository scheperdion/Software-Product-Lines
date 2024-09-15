import crypto.*;
import messages.Message;
import network.ChatSocket;
import Server.PreprocessorChain;


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
    PreprocessorChain _preMessageProcessor;


//    Add variability by inserting various Preprocessors in the PreprocessorChain where the order of execution is the order of insertion
    final IEncryptionRoutine encryption = new VigenereEncryption(new Rot13Encryption(new EncryptionRoutine()), "key");
    
    public Server(int port) {
        _logger = new Logging("Server"+port);

        Config config = new Config();
        String configInfo = config.getConfigInfo();
        _logger.logInfo("Initialized Config: " + configInfo);

        this.port = port;
        _preMessageProcessor = chain;
    }

    public void listen() {
        running = true;
        try (ServerSocket srvr = new ServerSocket(this.port)) {
            while(running) {
                Socket skt = srvr.accept();
                ChatSocket chatSkt = new ChatSocket(sockets.size(), skt, messages, encryption);
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
                _preMessageProcessor.process(m);
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
