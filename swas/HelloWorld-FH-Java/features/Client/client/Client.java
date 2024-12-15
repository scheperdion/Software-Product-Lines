package client;

import java.net.ServerSocket; 
import java.util.concurrent.BlockingQueue; 

import com.google.gson.Gson; 

import java.util.concurrent.ArrayBlockingQueue; 
import java.net.Socket; 
import java.util.ArrayList; 
import java.util.List; 
import java.io.*; 

import ui.UI; 
import event.*; 
import client.IClientCallback;

public  class  Client  implements Runnable {
	
	private ArrayList<IClientCallback> callbacks;

	
	private EventDeserializer eventDeserializer;

	

	public Client() {
		this.callbacks = new ArrayList<IClientCallback>();
		this.eventDeserializer = new EventDeserializer();
	}

	

	public void addCallback(IClientCallback callback) {
		EventDeserializer ed = new EventDeserializer();
		Gson gson = ed.getGSON();
		this.callbacks.add(callback);
	}

	@Override
	public void run() {
		try {
			Socket socket = new Socket("localhost", 6665);
			// BufferedReader userInput = new BufferedReader(new
			// InputStreamReader(System.in));
			// String message = userInput.readLine();
			// PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Gson gson = this.eventDeserializer.getGSON();
			while (true) {
				String serverResponse = input.readLine();
				AbstractEvent e = gson.fromJson(serverResponse, AbstractEvent.class);
				System.out.println("test: " + serverResponse);
				for (IClientCallback c : this.callbacks) {
					c.receivedEvent(e);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			// it was impossible to add the event to the queue, this means that something is
			// wrong because items dont get 'used'
		}
	}
}
