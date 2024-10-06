import java.io.IOException; 
import java.net.ServerSocket; 
import java.net.Socket; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.concurrent.ArrayBlockingQueue; 


public  class  Server  implements Runnable {
	

    int port;

	
    boolean running;

	
    List<ChatSocket> sockets = new ArrayList<ChatSocket>();

	
    ArrayBlockingQueue<Message> messages = new ArrayBlockingQueue<Message>(50);

	
    final MessageProcessor messageProcessor = new MessageProcessor();

	

    public Server(int port) {
        System.out.println("Server"+port);
        this.port = port;
    }

	
    public void listen() {
        running = true;
        try {
        	ServerSocket srvr = new ServerSocket(this.port);
            while(running) {
                Socket skt = srvr.accept();
                ChatSocket chatSkt = new ChatSocket(sockets.size(), skt, messages);
                Thread client = new Thread(chatSkt);
                client.start();
                sockets.add(chatSkt);
            }
        } catch (IOException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

	

    @Override
    public void run() {
        while(running) {
            try {
                Message m = messages.take();
                m.setString(messageProcessor.processMessageOnServer(m.getString()));
                for(ChatSocket skt : this.sockets) {
                    if(skt.isConnected()) {
                        skt.send(m);
                    }
                }
            } catch (InterruptedException e) {
                // TODO: log exception and graceful exit?
                System.out.println("Exception occurred: " + e.getMessage());
                throw new RuntimeException(e);
            }

        }
    }


}
