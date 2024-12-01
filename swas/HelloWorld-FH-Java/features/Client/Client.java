import java.net.ServerSocket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List; 
import java.io.*;

public class Client implements Runnable{
	
	private BlockingQueue<String> data;
	private ArrayList<IClientCallback> callbacks;
	
	public Client() {
		this.data = new ArrayBlockingQueue<String>(10);
	}
	
	public void addCallback(IClientCallback callback) {
		this.callbacks.add(callback);
	}
	
	public IEvent getEvent() {
		return new IEvent() { }; // this.data.poll(); 
	}
	
	@Override
	public void run() {
		try {
			Socket socket = new Socket("localhost", 6665);
	        //BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
	        //String message = userInput.readLine();
			//PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
	        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        while(true) {
	            String serverResponse = input.readLine();
	            this.data.add(serverResponse);	
	            for(IClientCallback c : this.callbacks ) {
	            	c.receivedEvent(new IEvent() {});
	            }
	        }
	        
		} catch(IOException e) {
			e.printStackTrace();
		} catch(IllegalStateException e) {
			e.printStackTrace();
			// it was impossible to add the event to the queue, this means that something is wrong because items dont get 'used'
		}

	}
	
	public void printData() {
		while(true) {
			try {
			    Thread.sleep(5000);
			} catch (InterruptedException e) {
			  Thread.currentThread().interrupt();
			}
			for(String s : this.data) {
				System.out.println(s);
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		System.out.println("Running Client");
		Client c = new Client();
		Thread t = new Thread(c);
		t.start();
		c.printData();
		t.join();
	}
}
