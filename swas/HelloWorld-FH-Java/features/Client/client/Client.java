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
import Base.IClientCallback;

public  class  Client  implements Runnable {
	

	private BlockingQueue<AbstractEvent> data;

	
	private ArrayList<IClientCallback> callbacks;

	
	private EventDeserializer eventDeserializer;

	

	public Client() {
		this.data = new ArrayBlockingQueue<AbstractEvent>(10);
		this.callbacks = new ArrayList<IClientCallback>();
		this.eventDeserializer = new EventDeserializer();
	}

	

	public void addCallback(IClientCallback callback) {
		this.callbacks.add(callback);
	}

	

	public AbstractEvent getEvent() {
		return new NoEvent("") {
		}; // this.data.poll();
	}

	

	public void receiveEvent(IClientCallback c) {
//		event = c.receivedEvent(new NoEvent("") {});
//		ui.eventToUI(new NoEvent("test"), false);
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
				this.data.add(gson.fromJson(serverResponse, AbstractEvent.class));
				System.out.println("test: " + serverResponse);
				for (IClientCallback c : this.callbacks) {
					receiveEvent(c);
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

	

	public void printData() {
		while (true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			for (AbstractEvent s : this.data) {
				System.out.println(s);
			}
		}
	}

	

	public void updateUI(UI ui) {
		System.out.println("some message");
		while (true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			for (AbstractEvent s : this.data) {
//				System.out.println(s);
				ui.eventToUI(s, false);
//				ui.createMessageBubble(s.type,false);
			}
			this.data.clear();
		}
	}


}
