

import java.net.Socket; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.concurrent.ArrayBlockingQueue; 


public  class  Client  implements Runnable, IMessageSender {
	
    private ChatSocket socket;

	
    boolean running = false;

	
    ArrayBlockingQueue<Message> messages = new ArrayBlockingQueue<Message>(50);

	
//    private final Logging _logger;
//    final Encryption encryption = new Encryption();
    final List<IMessageReceiver> messageObservers;

	
    final MessageProcessor messageProcessors = new MessageProcessor();

	

    public Client(int id) {
        this.messageObservers = new ArrayList<IMessageReceiver>();
    }

	

    public void addObserver(IMessageReceiver o) {
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

	

    @Override
    public void run() {
        running = true;
        while(running) {
            try {
                Message m = messages.take();
                m.setString(messageProcessors.processIncomingMessage(m.getString()));
                for (IMessageReceiver o : this.messageObservers) {
                    o.receive(m.getString());
                }
            } catch (InterruptedException e) {
                System.out.println("Exception occurred: " + e.getMessage());
                throw new RuntimeException(e);
            }

        }
    }

	

    @Override
    public void send(String s) {
        Message m = new Message(s,socket);
//        _logger.logInfo("Message send: " + m.getString());
        socket.send(m);
    }


}
