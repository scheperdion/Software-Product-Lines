import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List; 
import java.io.*;

public class Client implements Runnable{
	
	private ArrayList<String> data;
	
	public Client() {
		this.data = new ArrayList<String>();
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
	        }
	        
		} catch(IOException e) {
			e.printStackTrace();
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
